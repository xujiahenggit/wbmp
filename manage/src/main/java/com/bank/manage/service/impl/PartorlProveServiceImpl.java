package com.bank.manage.service.impl;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.RandomUtil;
import com.bank.core.entity.BizException;
import com.bank.core.entity.FileDo;
import com.bank.core.utils.ConfigFileReader;
import com.bank.core.utils.FileUploadUtils;
import com.bank.manage.dao.PartorlProveDao;
import com.bank.manage.dos.PartorlProveDO;
import com.bank.manage.service.PartorlProveService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @Author: Andy
 * @Date: 2020/5/12 15:12
 */
@Service
public class PartorlProveServiceImpl extends ServiceImpl<PartorlProveDao, PartorlProveDO> implements PartorlProveService {

    @Autowired
    private ConfigFileReader configFileReader;

    @Resource
    private PartorlProveDao partorlProveDao;
    /**
     * 证明文件上传
     * @param file 文件
     * @return
     */
    @Override
    public FileDo proveFileUpload(MultipartFile file) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        //第一层目录 按 日期创建
        String fist_tab=sdf.format(new Date());
        try{
            //上传路径
            String uploadPath=configFileReader.getPROVE_FILE_PATH()+"/"+fist_tab;
            //访问路径
            String accessPath=configFileReader.getHTTP_PATH()+configFileReader.getPROVE_ACCESS_PATH()+"/"+fist_tab;
            //原文件名称
            String filename = file.getOriginalFilename();
            //用UUID
            String c_fileName= IdUtil.randomUUID()+filename.substring(filename.lastIndexOf("."));;
            FileDo fileDo=FileUploadUtils.FileUpload(file,uploadPath,accessPath,c_fileName);
            return fileDo;
        }catch (Exception e){
            throw new BizException("证明文件上传失败！");
        }
    }

    /**
     * 用 巡查记录内容ID 批量删除 证明文件
     * @param listRecordItemIds 巡查内容ID
     */
    @Override
    public void deleteProve(List<Integer> listRecordItemIds) {
        partorlProveDao.deleteProveByRecordItemIds(listRecordItemIds);
    }

    /**
     * 用ID 查找证明文件路径列表
     * @param partorlRecordId 巡查记录ID
     * @return
     */
    @Override
    public List<String> selectFilePathByRecordId(Integer partorlRecordId) {
        return partorlProveDao.selectFilePathByRecordId(partorlRecordId);
    }
}
