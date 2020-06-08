package com.bank.manage.service.impl;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.bank.core.entity.BizException;
import com.bank.core.sysConst.NewProcessStatusFile;
import com.bank.core.utils.ConfigFileReader;
import com.bank.manage.dto.CheckWorkAttendDto;
import com.bank.manage.dto.CheckWorkRejectDto;
import com.bank.manage.excel.CheckWorkAttendExcel;
import com.bank.manage.excel.partorl.PartorlRecordExcelEntity;
import com.bank.manage.service.CheckWorkAttendService;
import com.bank.manage.service.MonthAttendService;
import com.bank.manage.service.SatisfactAttendService;
import com.bank.manage.vo.CheckWorkAttendQueryVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Andy
 * @Date: 2020/6/2 14:54
 */
@Service
public class CheckWorkAttendServiceImpl implements CheckWorkAttendService {

    @Resource
    private SatisfactAttendService satisfactAttendService;

    @Resource
    private MonthAttendService monthAttendService;


    @Resource
    private ConfigFileReader configFileReader;

    /**
     * 获取月度考勤数据
     * @param date 日期
     * @return
     */
    @Override
    public CheckWorkAttendDto getCheckWorkAttendData(String date) {

        CheckWorkAttendDto checkWorkAttendDto=new CheckWorkAttendDto();
        //设置满意度 已完成数
        checkWorkAttendDto.setSatisfactAttendFinshNum(satisfactAttendService.getatisfactAttendNum(date, NewProcessStatusFile.PROCESS_PASS));
        //设置满意度 未完成数
        checkWorkAttendDto.setSatisfactAttendWaitNum(satisfactAttendService.getatisfactAttendNum(date,NewProcessStatusFile.PROCESS_WAIT));
        //设置满意度 驳回数 默认为0
        checkWorkAttendDto.setSatisfactAttendRejectNum(0);

        //设置 月度考核确认 已完成数
        checkWorkAttendDto.setMonthAttendFinshNum(monthAttendService.getMonthAttendNum(date,NewProcessStatusFile.PROCESS_PASS));
        //设置 月度考核确认 未完成数
        checkWorkAttendDto.setMonthAttendWaitNum(monthAttendService.getMonthAttendNum(date,NewProcessStatusFile.PROCESS_WAIT));
        //设置 月度考核确认 已驳回
        checkWorkAttendDto.setMonthAttendRejectNum(monthAttendService.getMonthAttendNum(date,NewProcessStatusFile.PROCESS_REJECT));
        return checkWorkAttendDto;
    }

    /**
     * 下载月度考勤数据
     * @param date 日期
     * @return
     */
    @Override
    public String getDownFile(String date) {
        List<CheckWorkAttendExcel> list=new ArrayList<>();
        CheckWorkAttendExcel finishData=new CheckWorkAttendExcel();
        CheckWorkAttendExcel waitData=new CheckWorkAttendExcel();
        CheckWorkAttendExcel rejectData=new CheckWorkAttendExcel();
        //已完成数
        finishData.setState("已完成");
        finishData.setSatisfactAttentNum(satisfactAttendService.getatisfactAttendNum(date, NewProcessStatusFile.PROCESS_PASS));
        finishData.setMonthAttendNum(monthAttendService.getMonthAttendNum(date,NewProcessStatusFile.PROCESS_PASS));
        //未完成数
        waitData.setState("未完成");
        waitData.setSatisfactAttentNum(satisfactAttendService.getatisfactAttendNum(date, NewProcessStatusFile.PROCESS_WAIT));
        waitData.setMonthAttendNum(monthAttendService.getMonthAttendNum(date,NewProcessStatusFile.PROCESS_WAIT));
        //已驳回数
        rejectData.setState("已驳回");
        rejectData.setSatisfactAttentNum(0);
        rejectData.setMonthAttendNum(monthAttendService.getMonthAttendNum(date,NewProcessStatusFile.PROCESS_REJECT));

        list.add(finishData);
        list.add(waitData);
        list.add(rejectData);
        //String fileName="D:/excel/excelFile/月度考勤数据.xls";
        String fileName=configFileReader.getDOWN_EXCEL_PATH()+date+"月度考勤数据.xls";
        createExcelFile("月度考勤","月度考勤数据",fileName,list);
        return fileName;
    }

    /**
     * 月度考勤 驳回人数
     * @param checkWorkAttendQueryVo 查询参数
     * @param type 类型
     * @return
     */
    @Override
    public IPage<CheckWorkRejectDto> getRejectList(CheckWorkAttendQueryVo checkWorkAttendQueryVo,Integer type) {
        return monthAttendService.getRejectList(checkWorkAttendQueryVo,type);
    }

    /**
     * 月满意度 未完成人数
     * @param checkWorkAttendQueryVo 查询参数
     * @return
     */
    @Override
    public IPage<CheckWorkRejectDto> getSasifactWaitPerple(CheckWorkAttendQueryVo checkWorkAttendQueryVo) {
        return satisfactAttendService.getSasifactWaitPerple(checkWorkAttendQueryVo);
    }

    /**
     * 生成 Excel
     * @param title      标题
     * @param sheetName  sheet名称
     * @param fileName   文件名称
     * @param listRecord 列表
     */
    private void createExcelFile(String title, String sheetName, String fileName, List<CheckWorkAttendExcel> listRecord) {
        try {
            FileOutputStream fos = null;
            Workbook workbook = null;
            ExportParams exportParams = new ExportParams(title, sheetName);
            synchronized (this) {
                workbook = ExcelExportUtil.exportExcel(exportParams, CheckWorkAttendExcel.class, listRecord);
            }
            fos = new FileOutputStream(fileName);
            workbook.write(fos);
            workbook.close();
            fos.close();
        } catch (Exception e) {
            throw new BizException("生成Excel失败！");
        }
    }
}
