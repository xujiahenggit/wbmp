package com.bank.manage.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataValidation;
import org.apache.poi.ss.usermodel.DataValidationConstraint;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Name;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.apache.poi.xssf.usermodel.XSSFDataValidation;
import org.apache.poi.xssf.usermodel.XSSFDataValidationConstraint;
import org.apache.poi.xssf.usermodel.XSSFDataValidationHelper;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.write.metadata.style.WriteCellStyle;
import com.alibaba.excel.write.metadata.style.WriteFont;
import com.alibaba.excel.write.style.HorizontalCellStyleStrategy;
import com.bank.auth.base.BaseController;
import com.bank.core.entity.BizException;
import com.bank.core.entity.FileDo;
import com.bank.core.entity.PageQueryModel;
import com.bank.core.entity.TokenUserInfo;
import com.bank.core.utils.ConfigFileReader;
import com.bank.core.utils.FileUploadUtils;
import com.bank.core.utils.NetUtil;
import com.bank.manage.dto.ExcelDataDTO;
import com.bank.manage.handler.SpinnerWriteHandler;
import com.bank.manage.service.CountModuleService;
import com.bank.manage.service.DictionaryService;
import com.bank.manage.service.ExcelDataService;
import com.baomidou.mybatisplus.core.metadata.IPage;

import cn.hutool.core.util.IdUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

/**
 * Excel数据导入记录表  控制器
 */
@RestController
@RequestMapping("/excelData")
@Api(value = "Excel数据导入记录表", tags = "快乐服务-Excel数据记录表接口")
public class ExcelDataController extends BaseController {

    @Autowired
    private ConfigFileReader configFileReader;

    @Autowired
    private ExcelDataService excelDataService;

    @Autowired
    private DictionaryService dictionaryService;

    @Autowired
    private CountModuleService countModuleService;

    @javax.annotation.Resource
    NetUtil netUtil;

    @PostMapping("/delExcelData/{id}/{dataType}")
    @ApiOperation(value = "快乐服务--报表列表删除")
    public Boolean delExcelData(@PathVariable("id") String id, @PathVariable("dataType") String dataType) {
        return excelDataService.delExcelData(id, dataType);
    }

    @PostMapping("/queryExcelData")
    @ApiOperation(value = "快乐服务--报表列表查询")
    @ApiImplicitParam(name = "pageQueryModel", value = "报表列表信息分页查询", required = true, paramType = "body", dataType = "PageQueryModel")
    public IPage<ExcelDataDTO> queryExcelData(@RequestBody PageQueryModel pageQueryModel) {
        return excelDataService.queryExcelData(pageQueryModel);
    }

    @PostMapping("/importStarData")
    @ApiOperation(value = "星级标准化网点数据导入")
    public Boolean importStarData(@RequestBody ExcelDataDTO excelDataDTO, HttpServletRequest request) {
        TokenUserInfo tokenUserInfo = getCurrentUserInfo(request);
        return excelDataService.importStarData(excelDataDTO, tokenUserInfo);
    }

    @PostMapping("/importExampleBranch")
    @ApiOperation(value = "全国标杆网点统计数据导入")
    public Boolean importExampleBranch(@RequestBody ExcelDataDTO excelDataDTO, HttpServletRequest request) {
        TokenUserInfo tokenUserInfo = getCurrentUserInfo(request);
        return excelDataService.importExampleBranch(excelDataDTO, tokenUserInfo);
    }

    @PostMapping("/importExamineTempData")
    @ApiOperation(value = "季度考核数据导入")
    public Boolean importExamineTempData(@RequestBody ExcelDataDTO excelDataDTO, HttpServletRequest request) {
        TokenUserInfo tokenUserInfo = getCurrentUserInfo(request);
        return excelDataService.importExamineTempData(excelDataDTO, tokenUserInfo);
    }

    @ApiOperation(value = "星级标准化网点数据-Excel导入模板下载")
    @GetMapping("/downloadStarData")
    public void downloadStarData(HttpServletResponse response) throws IOException {

        Resource resource = new ClassPathResource("file/StarTemp.xlsx");
        InputStream inputStream = resource.getInputStream();

        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("UTF-8");
        String fileName = URLEncoder.encode("StarTemp", "UTF-8");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");

        OutputStream out = response.getOutputStream();
        int b = 0;
        byte[] buffer = new byte[1000000];
        while (b != -1) {
            b = inputStream.read(buffer);
            if (b != -1) {
                out.write(buffer, 0, b);
            }
        }
        inputStream.close();
        out.close();
        out.flush();
    }

    @ApiOperation(value = "全国标杆网点统计数据-Excel导入模板下载")
    @GetMapping("/downloadExampleBranch")
    public void downloadExampleBranch(HttpServletResponse response) throws IOException {

        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("UTF-8");
        String fileName = URLEncoder.encode("ExampleBranch", "UTF-8");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");

        // 头的策略
        WriteCellStyle headWriteCellStyle = new WriteCellStyle();
        // 背景色
        headWriteCellStyle.setFillForegroundColor(IndexedColors.WHITE.getIndex());
        WriteFont headWriteFont = new WriteFont();
        headWriteFont.setFontHeightInPoints((short) 13);
        headWriteCellStyle.setWriteFont(headWriteFont);
        headWriteCellStyle.setShrinkToFit(true);

        // 内容的策略
        WriteCellStyle contentWriteCellStyle = new WriteCellStyle();
        // 这里需要指定 FillPatternType 为FillPatternType.SOLID_FOREGROUND 不然无法显示背景颜色.头默认了 FillPatternType所以可以不指定
        contentWriteCellStyle.setFillPatternType(FillPatternType.SOLID_FOREGROUND);
        // 背景色
        contentWriteCellStyle.setFillForegroundColor(IndexedColors.WHITE.getIndex());
        WriteFont contentWriteFont = new WriteFont();
        // 字体大小
        contentWriteFont.setFontHeightInPoints((short) 13);
        contentWriteCellStyle.setWriteFont(contentWriteFont);
        // 这个策略是 头是头的样式 内容是内容的样式 其他的策略可以自己实现
        HorizontalCellStyleStrategy horizontalCellStyleStrategy = new HorizontalCellStyleStrategy(headWriteCellStyle, contentWriteCellStyle);

        EasyExcelFactory.write(response.getOutputStream())
                .registerWriteHandler(horizontalCellStyleStrategy)
                .registerWriteHandler(new SpinnerWriteHandler(dictionaryService)).head(headList()).sheet("模板").doWrite(new ArrayList());

    }

    @GetMapping("/downloadExamineTemp")
    @ApiOperation("考核数据Excel模板下载")
    public void down(HttpServletResponse response) {
        List<Map<String, Object>> countModuleList = countModuleService.queryCountModule();
        List<String> oneModuleList = new ArrayList<>();
        countModuleList.forEach(s -> {
            String key = s.entrySet().iterator().next().getKey();
            oneModuleList.add(key);
        });

        // 创建一个excel
        @SuppressWarnings("resource")
        Workbook book = new XSSFWorkbook();

        // 创建需要用户填写的sheet
        XSSFSheet sheetPro = (XSSFSheet) book.createSheet("服务品质管理问题明细汇总表");
        Row row0 = sheetPro.createRow(0);
        row0.createCell(0).setCellValue("分行名称(相同请合并)");
        row0.createCell(1).setCellValue("分支行机构号(相同请合并)");
        row0.createCell(2).setCellValue("网点名称(相同请合并)");
        row0.createCell(3).setCellValue("网点机构号(相同请合并)");
        row0.createCell(4).setCellValue("支行得分(相同请合并)");
        row0.createCell(5).setCellValue("一级模块");
        row0.createCell(6).setCellValue("二级模块");
        row0.createCell(7).setCellValue("指标名称");
        row0.createCell(8).setCellValue("扣分值");
        row0.createCell(9).setCellValue("扣分描述");
        row0.createCell(10).setCellValue("分行得分(相同请合并)");

        sheetPro.setDefaultColumnWidth(18);
        sheetPro.setDefaultRowHeight((short) 300);

        //得到第一级省名称，放在列表里
        String[] provinceArr = oneModuleList.toArray(new String[oneModuleList.size()]);

        //将有子区域的父区域放到一个数组中
        String[] areaFatherNameArr = oneModuleList.toArray(new String[oneModuleList.size()]);
        //依次列出各省的市
        Map<String, String[]> areaMap = new HashMap<String, String[]>();
        for (int i = 0; i < countModuleList.size(); i++) {
            List<String> o = (List<String>) countModuleList.get(i).get(oneModuleList.get(i));
            areaMap.put(oneModuleList.get(i), o.toArray(new String[o.size()]));
        }

        //创建一个专门用来存放地区信息的隐藏sheet页
        //因此也不能在现实页之前创建，否则无法隐藏。
        Sheet hideSheet = book.createSheet("area");
        //这一行作用是将此sheet隐藏，功能未完成时注释此行,可以查看隐藏sheet中信息是否正确
        book.setSheetHidden(book.getSheetIndex(hideSheet), true);

        int rowId = 0;
        // 设置第一行，存省的信息
        Row provinceRow = hideSheet.createRow(rowId++);
        provinceRow.createCell(5).setCellValue("模块列表");
        for (int i = 0; i < provinceArr.length; i++) {
            Cell provinceCell = provinceRow.createCell(i + 6);
            provinceCell.setCellValue(provinceArr[i]);
        }
        // 将具体的数据写入到每一行中，行开头为父级区域，后面是子区域。
        for (int i = 0; i < areaFatherNameArr.length; i++) {
            String key = areaFatherNameArr[i];
            String[] son = areaMap.get(key);
            Row row = hideSheet.createRow(rowId++);
            row.createCell(5).setCellValue(key);
            for (int j = 0; j < son.length; j++) {
                Cell cell = row.createCell(j + 6);
                cell.setCellValue(son[j]);
            }

            // 添加名称管理器
            String range = getRange(6, rowId, son.length);
            Name name = book.createName();
            //key不可重复
            name.setNameName(key);
            String formula = "area!" + range;
            name.setRefersToFormula(formula);
        }

        XSSFDataValidationHelper dvHelper = new XSSFDataValidationHelper(sheetPro);
        // 省规则
        DataValidationConstraint provConstraint = dvHelper.createExplicitListConstraint(provinceArr);
        // 四个参数分别是：起始行、终止行、起始列、终止列
        CellRangeAddressList provRangeAddressList = new CellRangeAddressList(1, 2000, 5, 5);
        DataValidation provinceDataValidation = dvHelper.createValidation(provConstraint, provRangeAddressList);
        //验证
        provinceDataValidation.createErrorBox("error", "请选择正确的模块");
        provinceDataValidation.setShowErrorBox(true);
        provinceDataValidation.setSuppressDropDownArrow(true);
        sheetPro.addValidationData(provinceDataValidation);

        //对前2000行设置有效性
        for (int i = 2; i < 2000; i++) {
            setDataValidation("F", sheetPro, i, 7);
        }

        OutputStream out = null;
        try {
            response.setContentType("application/vnd.ms-excel");
            response.setCharacterEncoding("UTF-8");
            String fileName = URLEncoder.encode("ExamineTemp", "UTF-8");
            response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
            out = response.getOutputStream();
            book.write(out);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            IOUtils.closeQuietly(out);
        }
    }

    /*@ApiOperation(value = "季度考核数据-Excel导入模板下载")
    @GetMapping("/downloadExamineTemp")
    public void downloadExamineTemp(HttpServletResponse response) throws IOException {
    	Resource resource = new ClassPathResource("file/ExamineTemp.xlsx");
    	InputStream inputStream = resource.getInputStream();

    	response.setContentType("application/vnd.ms-excel");
    	response.setCharacterEncoding("UTF-8");
    	String fileName = URLEncoder.encode("ExamineTemp", "UTF-8");
    	response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");

    	OutputStream out = response.getOutputStream();
    	int b = 0;
    	byte[] buffer = new byte[1000000];
    	while (b != -1) {
    		b = inputStream.read(buffer);
    		if (b != -1) {
    			out.write(buffer, 0, b);
    		}
    	}
    	inputStream.close();
    	out.close();
    	out.flush();
    }*/

    @PostMapping("/uploadFile")
    @ApiOperation(value = "快乐服务-Excel上传")
    public FileDo upFileForShare(@RequestParam(value = "file") MultipartFile file, HttpServletRequest request) {
        FileDo fileDo = new FileDo();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        //第一层目录 按 日期创建
        String fist_tab = sdf.format(new Date());
        //上传路径
        String uploadPath = configFileReader.getHAPPY_FILE_PATH() + "/" + fist_tab;
        //访问路径
        String accessPath = netUtil.getUrlSuffix("") + configFileReader.getHAPPY_FILE_PATH() + "/" + fist_tab;
        //原文件名称
        String filename = file.getOriginalFilename();
        //用UUID
        String c_fileName = IdUtil.randomUUID() + filename.substring(filename.lastIndexOf("."));;
        try {
            fileDo = FileUploadUtils.FileUpload(file, uploadPath, accessPath, c_fileName);
        }
        catch (Exception e) {
            throw new BizException("Excel上传失败！");
        }
        return fileDo;
    }

    private List<List<String>> headList() {
        List<List<String>> headList = new ArrayList<List<String>>();
        // 第 n 行 的表头
        List<String> headTitle0 = new ArrayList<String>();
        List<String> headTitle1 = new ArrayList<String>();
        List<String> headTitle2 = new ArrayList<String>();
        List<String> headTitle3 = new ArrayList<String>();
        List<String> headTitle4 = new ArrayList<String>();
        List<String> headTitle5 = new ArrayList<String>();
        headTitle0.add("标杆网点统计数据");
        headTitle0.add("序号");
        headTitle1.add("标杆网点统计数据");
        headTitle1.add("创建类型");
        headTitle2.add("标杆网点统计数据");
        headTitle2.add("创建年份");
        headTitle3.add("标杆网点统计数据");
        headTitle3.add("网点名称");
        headTitle4.add("标杆网点统计数据");
        headTitle4.add("网点机构号");
        headTitle5.add("标杆网点统计数据");
        headTitle5.add("失效日期('年份'或'永久有效')");

        headList.add(headTitle0);
        headList.add(headTitle1);
        headList.add(headTitle2);
        headList.add(headTitle3);
        headList.add(headTitle4);
        headList.add(headTitle5);
        return headList;
    }

    /**
     * 设置有效性
     * @param offset 主影响单元格所在列，即此单元格由哪个单元格影响联动
     * @param sheet
     * @param rowNum 行数
     * @param colNum 列数
     */
    public static void setDataValidation(String offset, XSSFSheet sheet, int rowNum, int colNum) {
        XSSFDataValidationHelper dvHelper = new XSSFDataValidationHelper(sheet);
        DataValidation data_validation_list;
        data_validation_list = getDataValidationByFormula(
                "INDIRECT($" + offset + (rowNum) + ")", rowNum, colNum, dvHelper);
        sheet.addValidationData(data_validation_list);
    }

    /**
     * 加载下拉列表内容
     * @param formulaString
     * @param naturalRowIndex
     * @param naturalColumnIndex
     * @param dvHelper
     * @return
     */
    private static DataValidation getDataValidationByFormula(String formulaString, int naturalRowIndex, int naturalColumnIndex, XSSFDataValidationHelper dvHelper) {
        // 加载下拉列表内容
        // 举例：若formulaString = "INDIRECT($A$2)" 表示规则数据会从名称管理器中获取key与单元格 A2 值相同的数据，
        //如果A2是江苏省，那么此处就是江苏省下的市信息。
        XSSFDataValidationConstraint dvConstraint = (XSSFDataValidationConstraint) dvHelper.createFormulaListConstraint(formulaString);
        // 设置数据有效性加载在哪个单元格上。
        // 四个参数分别是：起始行、终止行、起始列、终止列
        int firstRow = naturalRowIndex - 1;
        int lastRow = naturalRowIndex - 1;
        int firstCol = naturalColumnIndex - 1;
        int lastCol = naturalColumnIndex - 1;
        CellRangeAddressList regions = new CellRangeAddressList(firstRow,
                lastRow, firstCol, lastCol);
        // 数据有效性对象
        // 绑定
        XSSFDataValidation data_validation_list = (XSSFDataValidation) dvHelper.createValidation(dvConstraint, regions);
        data_validation_list.setEmptyCellAllowed(false);
        if (data_validation_list instanceof XSSFDataValidation) {
            data_validation_list.setSuppressDropDownArrow(true);
            data_validation_list.setShowErrorBox(true);
        }
        else {
            data_validation_list.setSuppressDropDownArrow(false);
        }
        // 设置输入信息提示信息
        data_validation_list.createPromptBox("下拉选择提示", "请使用下拉方式选择合适的值！");
        // 设置输入错误提示信息
        data_validation_list.createErrorBox("选择错误提示", "你输入的值未在备选列表中，请下拉选择合适的值！");
        return data_validation_list;
    }

    /**
     *  计算formula
     * @param offset 偏移量，如果给0，表示从A列开始，1，就是从B列
     * @param rowId 第几行
     * @param colCount 一共多少列
     * @return 如果给入参 1,1,10. 表示从B1-K1。最终返回 $B$1:$K$1
     *
     */
    public static String getRange(int offset, int rowId, int colCount) {
        char start = (char) ('A' + offset);
        if (colCount <= 25) {
            char end = (char) (start + colCount - 1);
            return "$" + start + "$" + rowId + ":$" + end + "$" + rowId;
        }
        else {
            char endPrefix = 'A';
            char endSuffix = 'A';
            if ((colCount - 25) / 26 == 0 || colCount == 51) {// 26-51之间，包括边界（仅两次字母表计算）
                if ((colCount - 25) % 26 == 0) {// 边界值
                    endSuffix = (char) ('A' + 25);
                }
                else {
                    endSuffix = (char) ('A' + (colCount - 25) % 26 - 1);
                }
            }
            else {// 51以上
                if ((colCount - 25) % 26 == 0) {
                    endSuffix = (char) ('A' + 25);
                    endPrefix = (char) (endPrefix + (colCount - 25) / 26 - 1);
                }
                else {
                    endSuffix = (char) ('A' + (colCount - 25) % 26 - 1);
                    endPrefix = (char) (endPrefix + (colCount - 25) / 26);
                }
            }
            return "$" + start + "$" + rowId + ":$" + endPrefix + endSuffix + "$" + rowId;
        }
    }
}
