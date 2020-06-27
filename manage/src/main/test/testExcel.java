import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.bank.core.utils.FileToZip;
import com.bank.manage.excel.partorl.PartorlContentExcelEntity;
import com.bank.manage.excel.partorl.PartorlModualExcelEntity;
import com.bank.manage.excel.partorl.PartorlRecordExcelEntity;
import com.bank.manage.excel.partorl.Person;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @Author: Andy
 * @Date: 2020/5/14 10:42
 */

public class testExcel {
   //@Test
    public void testExportExcel() {
        try {
            List<PartorlRecordExcelEntity> list = new ArrayList<>();
            PartorlRecordExcelEntity partorlRecordExcelEntity1 = new PartorlRecordExcelEntity();

            PartorlModualExcelEntity partorlModualExcelEntity = new PartorlModualExcelEntity();
            partorlModualExcelEntity.setId("1");
            partorlModualExcelEntity.setPartorlModual("厅堂外部");
            partorlRecordExcelEntity1.setPartorlModualExcelEntity(partorlModualExcelEntity);
            List<PartorlContentExcelEntity> list1 = new ArrayList<>();
            PartorlContentExcelEntity partorlContentExcelEntity1 = new PartorlContentExcelEntity();
            partorlContentExcelEntity1.setId("1");
            partorlContentExcelEntity1.setPartorlContent("外部台接、墙壁干净整洁、无杂物摆放。");
            partorlContentExcelEntity1.setIsNomal("是");
            list1.add(partorlContentExcelEntity1);
            PartorlContentExcelEntity partorlContentExcelEntity2 = new PartorlContentExcelEntity();
            partorlContentExcelEntity2.setId("2");
            partorlContentExcelEntity2.setPartorlContent("广告灯箱营业时间正常显示。");
            partorlContentExcelEntity2.setIsNomal("是");
            list1.add(partorlContentExcelEntity2);
            partorlRecordExcelEntity1.setPartorlContentExcelEntity(list1);
            list.add(partorlRecordExcelEntity1);


            PartorlRecordExcelEntity partorlRecordExcelEntity2 = new PartorlRecordExcelEntity();
            PartorlModualExcelEntity partorlModualExcelEntity1 = new PartorlModualExcelEntity();

            partorlModualExcelEntity1.setId("2");
            partorlModualExcelEntity1.setPartorlModual("营业大厅");
            partorlRecordExcelEntity2.setPartorlModualExcelEntity(partorlModualExcelEntity1);

            List<PartorlContentExcelEntity> list2 = new ArrayList<>();

            PartorlContentExcelEntity partorlContentExcelEntity12 = new PartorlContentExcelEntity();
            partorlContentExcelEntity12.setId("3");
            partorlContentExcelEntity12.setPartorlContent("地面、门窗无污渍、无乱张贴。");
            partorlContentExcelEntity12.setIsNomal("否");
            list2.add(partorlContentExcelEntity12);
            PartorlContentExcelEntity partorlContentExcelEntity22 = new PartorlContentExcelEntity();
            partorlContentExcelEntity22.setId("4");
            partorlContentExcelEntity22.setPartorlContent("光线明亮、温度适宜。");
            partorlContentExcelEntity22.setIsNomal("否");
            list2.add(partorlContentExcelEntity22);
            partorlRecordExcelEntity2.setPartorlContentExcelEntity(list2);
            list.add(partorlRecordExcelEntity2);

            Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("大唐经理巡查表", "巡查记录"),
                    PartorlRecordExcelEntity.class, list);
            FileOutputStream fos = new FileOutputStream("D:/excel/大堂经理巡查.xls");
            workbook.write(fos);
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //@Test
    public void testListToString() {
        List list = new ArrayList();
        list.add("浙江");
        list.add("江苏");
        list.add("福建");
        System.out.println("测试开始:List转字符串");
        String result = StringUtils.join(list, ";");
        System.out.println(result);
    }

    //@Test
    public void testPersion() throws IOException {
        List<List<Person>> listPerson = new ArrayList<>();

        List<Person> list1 = new ArrayList<>();
        List<Person> list2 = new ArrayList<>();
        List<Person> list3 = new ArrayList<>();
        Person person1 = new Person();
        person1.setName("小王");
        person1.setSex("1");
        person1.setBirthday(new Date());
        list1.add(person1);

        Person person2 = new Person();
        person2.setName("小李");
        person2.setSex("1");
        person2.setBirthday(new Date());
        list2.add(person2);


        Person person3 = new Person();
        person3.setName("小王");
        person3.setSex("1");
        person3.setBirthday(new Date());
        list3.add(person3);

        listPerson.add(list1);
        listPerson.add(list2);
        listPerson.add(list3);

        for (int i = 0; i < listPerson.size(); i++) {
            FileOutputStream fos = null;
            Workbook workbook = null;
            //String name=configFileReader.getRECORD_EXCEL()+title+".xls";
            ExportParams exportParams = new ExportParams("abc", "sheetName");
            synchronized (this) {
                workbook = ExcelExportUtil.exportExcel(exportParams, Person.class, listPerson.get(i));
            }
            String fileName = "D:/excel/abc" + i + ".xls";
            fos = new FileOutputStream(fileName);
            workbook.write(fos);
            workbook.close();
            fos.close();
        }
    }

   // @Test
    public void testZip() throws IOException {

        HashMap<String,String> hashMap=new HashMap<>();
        hashMap.put("1","1");
        List<String> fileList=new ArrayList<>();
        //待压缩文件
        fileList.add("D:/excel/大堂经理巡查_总行行领导_总行行领导_2020-05-15.xls");
        fileList.add("D:/excel/大堂经理巡查_总行行领导_总行行领导_2020-05-14.xls");
        fileList.add("D:/excel/大堂经理巡查_总行行领导_总行行领导_2020-05-15.xls");
        fileList.add("D:/excel/大堂经理巡查_总行行领导_总行行领导_2020-05-15.xls");
        //zip 文件名
        String zipName = "bbb";
        //zip 文件名
        String zipFilePath = "D:/excel/";
        FileToZip.toZip(fileList, zipFilePath, zipName);
    }

    public static void main(String[] args) {
        String date="2020-05";

    }
}
