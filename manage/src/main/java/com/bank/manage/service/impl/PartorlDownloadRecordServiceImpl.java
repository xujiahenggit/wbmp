package com.bank.manage.service.impl;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.hutool.core.util.StrUtil;
import com.bank.core.entity.BizException;
import com.bank.core.utils.ConfigFileReader;
import com.bank.core.utils.FileToZip;
import com.bank.manage.dao.PartorlDownloadRecordDao;
import com.bank.manage.dos.PartorlDownloadRecordDO;
import com.bank.manage.dos.PartorlRecordDO;
import com.bank.manage.excel.partorl.PartorlContentExcelEntity;
import com.bank.manage.excel.partorl.PartorlExcelEntity;
import com.bank.manage.excel.partorl.PartorlModualExcelEntity;
import com.bank.manage.excel.partorl.PartorlRecordExcelEntity;
import com.bank.manage.service.PartorlDownloadRecordService;
import com.bank.manage.service.PartorlProveService;
import com.bank.manage.service.PartorlRecordItemService;
import com.bank.manage.service.PartorlRecordService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Andy
 * @Date: 2020/5/14 9:34
 */
@Service
@Slf4j
public class PartorlDownloadRecordServiceImpl extends ServiceImpl<PartorlDownloadRecordDao, PartorlDownloadRecordDO> implements PartorlDownloadRecordService {

    @Resource
    private ConfigFileReader configFileReader;

    @Resource
    private PartorlDownloadRecordDao partorlDownloadRecordDao;

    @Resource
    private PartorlRecordItemService partorlRecordItemService;

    @Resource
    private PartorlRecordService partorlRecordService;

    @Resource
    private PartorlProveService partorlProveService;

    /**
     * 下载巡查记录
     * 1.现在文件中查找是否已经 存在文件
     * 2.如果不存在 则重新生成
     * 2.1 生成excel
     * 2.2 查询 证明文件
     * 2.3 打包证明文件
     * 2.4 打包 excel+证明文件
     * 3.保存打包记录
     *
     * @param partorlIds 巡查记录ID 列表
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public String getZpiFile(List<Integer> partorlIds) {
        try {
            //所有打包zip文件列表
            List<String> listFile=new ArrayList<>();
            for(int i=0;i<partorlIds.size();i++){
                Integer partorlRecordId=partorlIds.get(i);
                //1.查找文件记录是否存在
                QueryWrapper<PartorlDownloadRecordDO> queryWrapperexist = new QueryWrapper<>();
                queryWrapperexist.eq("PARTORL_RECORD_ID", partorlRecordId);
                PartorlDownloadRecordDO partorlDownloadRecord = partorlDownloadRecordDao.selectOne(queryWrapperexist);
                if(partorlDownloadRecord==null){
                    String zipfile=null;
                    //2.1 生成excel
                    List<PartorlRecordExcelEntity> list = new ArrayList<>();
                    //查询所有的模块
                    List<PartorlModualExcelEntity> partorlModualExcelEntitys = partorlRecordItemService.getListModual();
                    //生成excel
                    String excelFile=createExcelFileByRecordId(partorlModualExcelEntitys, partorlRecordId, list);
                    //将excel 添加到 zip 打包列表
                    listFile.add(excelFile);
                    //2.2 查询 证明文件
                    List<String> prveFileList = partorlProveService.selectFilePathByRecordId(partorlRecordId);
                    if(prveFileList.size()>0){
                        PartorlRecordDO partorlRecordDO=partorlRecordService.getById(partorlIds.get(i));
                        String zipFileName="证明文件";
                        if(partorlRecordDO!=null){
                            zipFileName=partorlRecordDO.getOrgName()+"_"+partorlRecordDO.getPartorlDate()+"_"+partorlRecordDO.getPartorlRecordId()+"_"+"证明文件";
                        }
                        //打包文件
                        zipfile=FileToZip.toZip(prveFileList,configFileReader.getRECORD_PROVE_ZIP_PATH(),zipFileName);
                        listFile.add(zipfile);
                    }
                    //保存下载记录
                    PartorlDownloadRecordDO partorlDownloadRecordDO=getDownModual(partorlIds.get(i),excelFile,zipfile);
                    partorlDownloadRecordDao.insert(partorlDownloadRecordDO);
                }else{
                    log.info("========================");
                    //如果已经存在，则直接打包
                    listFile.add(partorlDownloadRecord.getPartorlDownloadExcelpath());
                    //如果存在证明文件 则 直接放置到打包列表中
                    if(StrUtil.isNotBlank(partorlDownloadRecord.getPartorlDownloadProviepath())){
                        listFile.add(partorlDownloadRecord.getPartorlDownloadProviepath());
                    }
                    log.info(partorlDownloadRecord.getPartorlDownloadProviepath());
                    //每次下载 下载数+1
                    PartorlDownloadRecordDO partorlDownloadRecordDO=new PartorlDownloadRecordDO();
                    partorlDownloadRecordDO.setPartorlDownloadRedordId(partorlDownloadRecord.getPartorlDownloadRedordId());
                    partorlDownloadRecordDO.setPartorlDownloadNum(partorlDownloadRecord.getPartorlDownloadNum()+1);
                    partorlDownloadRecordDao.updateById(partorlDownloadRecordDO);
                }
            }
            log.info("文件列表："+listFile.size());
            //打包整体zip 文件
            String partorlZipFile=FileToZip.toZip(listFile,configFileReader.getRECORD_DOWNLOAD_PATH(),"巡查内容文件");
            return partorlZipFile;
        } catch (Exception e) {
            if (e instanceof BizException) {
                throw new BizException(((BizException) e).getErrorMsg());
            }
            throw new BizException("下载失败");
        }
    }


    /**
     * 构建 文件下载记录模型 每条巡查记录 都生成下载记录
     * @param partorlRecordId 巡查记录编号
     * @param excelFilePath excel存放路径
     * @param proveFilePath 证明文件存放路径
     * @return
     */
    private PartorlDownloadRecordDO getDownModual(Integer partorlRecordId,String excelFilePath,String proveFilePath){
        PartorlDownloadRecordDO partorlDownloadRecordDO=new PartorlDownloadRecordDO();
        //设置下载巡查记录ID
        partorlDownloadRecordDO.setPartorlRecordId(partorlRecordId);
        //设置下载次数 第一次下载 为 1
        partorlDownloadRecordDO.setPartorlDownloadNum(1);
        //设置excel存放路径
        partorlDownloadRecordDO.setPartorlDownloadExcelpath(excelFilePath);
        //设置证明文件 zip 打包后的路径
        partorlDownloadRecordDO.setPartorlDownloadProviepath(proveFilePath);
        return partorlDownloadRecordDO;
    }

    /**
     * 生成需查内容 记录表excel
     * @param partorlModualExcelEntitys 巡查主体
     * @param partorId 巡查记录ID
     * @param list 巡查内容
     */
    private String createExcelFileByRecordId(List<PartorlModualExcelEntity> partorlModualExcelEntitys, Integer partorId, List<PartorlRecordExcelEntity> list) {
        PartorlExcelEntity partorlExcelEntity = partorlRecordService.getRecordById(partorId);
        for (PartorlModualExcelEntity itemModual : partorlModualExcelEntitys) {
            PartorlRecordExcelEntity partorlRecordExcelEntity = new PartorlRecordExcelEntity();
            partorlRecordExcelEntity.setPartorlModualExcelEntity(itemModual);
            //查找 巡查内容
            List<PartorlContentExcelEntity> listRecordItems = partorlRecordItemService.getListByRecordIds(partorId, Integer.parseInt(itemModual.getId()));
            //设置内容
            partorlRecordExcelEntity.setPartorlContentExcelEntity(listRecordItems);
            list.add(partorlRecordExcelEntity);
        }
        String title = "大堂经理巡查_" + partorlExcelEntity.getSubBranchName() + "_" + partorlExcelEntity.getOutlets() + "_" + partorlExcelEntity.getPartorlDate()+"_"+partorlExcelEntity.getRecordId();
        String sheetName = partorlExcelEntity.getPartorlDate() + "_巡查记录";
        List<PartorlRecordExcelEntity> listRecord = list;
        String fileName=configFileReader.getRECORD_EXCEL()+title+".xls";
        //String fileName = "D:/excel/excelFile/" + title + ".xls";
        createExcelFile(title, sheetName, fileName, listRecord);
        return  fileName;
    }

    /**
     * 生成 Excel
     * @param title      标题
     * @param sheetName  sheet名称
     * @param fileName   文件名称
     * @param listRecord 列表
     */
    private void createExcelFile(String title, String sheetName, String fileName, List<PartorlRecordExcelEntity> listRecord) {
        try {
            FileOutputStream fos = null;
            Workbook workbook = null;
            ExportParams exportParams = new ExportParams(title, sheetName);
            workbook = ExcelExportUtil.exportExcel(exportParams, PartorlRecordExcelEntity.class, listRecord);
            fos = new FileOutputStream(fileName);
            workbook.write(fos);
            workbook.close();
            fos.close();
        } catch (Exception e) {
            throw new BizException("生成Excel失败！");
        }
    }
}
