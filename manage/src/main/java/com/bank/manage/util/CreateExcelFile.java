package com.bank.manage.util;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.bank.core.entity.BizException;
import com.bank.manage.excel.partorl.PartorlRecordExcelEntity;
import lombok.Data;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.FileOutputStream;
import java.util.List;

/**
 * @Author: Andy
 * @Date: 2020/5/14 23:02
 */
@Data
public class CreateExcelFile implements Runnable {

    private String title;

    private String sheetName;

    private String fileName;

    private List<PartorlRecordExcelEntity> listRecord;
    /**
     * When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {
        createExcelFile(this.title,this.sheetName,this.fileName,this.listRecord);
    }

    /**
     * 生成 Excel
     * @param title 标题
     * @param sheetName sheet名称
     * @param fileName 文件名称
     * @param listRecord 列表
     */
    public void createExcelFile(String title, String sheetName, String fileName , List<PartorlRecordExcelEntity> listRecord){
        try{
            FileOutputStream fos = null;
            Workbook workbook = null;
            //String name=configFileReader.getRECORD_EXCEL()+title+".xls";
            ExportParams exportParams=new ExportParams(title,sheetName);
            workbook = ExcelExportUtil.exportExcel(exportParams,PartorlRecordExcelEntity.class, listRecord);
            fos = new FileOutputStream(fileName);
            workbook.write(fos);
            workbook.close();
            fos.close();
        }catch (Exception e){
            throw new BizException("生成Excel失败！");
        }
    }
}
