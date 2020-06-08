package com.bank.manage.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bank.manage.dos.UsherDO;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.excel.EasyExcel;
import com.bank.auth.base.BaseController;
import com.bank.core.entity.BizException;
import com.bank.core.entity.PageQueryModel;
import com.bank.core.utils.PropertyUtil;
import com.bank.manage.dos.UsherPopulationDO;
import com.bank.manage.dto.UsherDTO;
import com.bank.manage.dto.UsherPopulationDTO;
import com.bank.manage.excel.ImportExcelResponse;
import com.bank.manage.excel.UsherExcelData;
import com.bank.manage.excel.UsherPopulationExcelData;
import com.bank.manage.listener.UsherExcelListener;
import com.bank.manage.listener.UsherPopulationExcelListener;
import com.bank.manage.service.UsherService;
import com.baomidou.mybatisplus.core.metadata.IPage;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 *
 * ClassName: UsherController
 *
 * @author Yanwen D. Ding
 *
 * Copyright © 2016 Yusys Technologies Co., Ltd.
 *
 * All Rights Reserved
 *
 * http://www.yusys.com.cn
 *
 * Create Time: 2020/04/23 16:09:01
 */
@Api(tags = "网点引导员管理")
@RestController
@RequestMapping("/usher")
public class UsherController extends BaseController {

    private final UsherService usherService;

    public UsherController(UsherService usherService) {
        this.usherService = usherService;
    }

    @ApiOperation(value = "插入网点引导员信息", notes = "不用传入usherId主键字段，age字段不做存储，sex字段值由身份证号获取进行存储")
    @ApiImplicitParam(name = "usherDTO", value = "网点引导员信息", required = true, paramType = "body", dataType = "UsherDTO")
    @PostMapping
    public Integer insert(UsherDTO usherDTO, HttpServletRequest request) {
        return this.usherService.insert(usherDTO, getCurrentUserId(request));
    }

    @ApiOperation(value = "删除网点引导员信息")
    @ApiImplicitParam(name = "id", value = "网点引导员唯一标识", required = true, paramType = "path")
    @DeleteMapping("/{id}")
    public Integer delete(@PathVariable Integer id, HttpServletRequest request) {
        return this.usherService.deleteById(id, getCurrentUserId(request));
    }

    @ApiOperation(value = "更新网点引导员信息")
    @ApiImplicitParam(name = "usherDTO", value = "网点引导员信息", required = true, paramType = "body", dataType = "UsherDTO")
    @PutMapping
    public Integer updateById(@RequestBody UsherDTO usherDTO, HttpServletRequest request) {
        return this.usherService.updateById(usherDTO, getCurrentUserId(request));
    }

    @ApiOperation(value = "查询网点引导员信息-分页")
    @ApiImplicitParam(name = "pageQueryModel", value = "网点引导员信息分页查询", required = true, paramType = "body", dataType = "PageQueryModel")
    @PostMapping("/selectPage")
    public IPage<UsherDTO> selectPage(@RequestBody PageQueryModel pageQueryModel) {
        return this.usherService.selectPageExt(pageQueryModel);
    }

    @ApiOperation(value = "网点引导员信息-Excel导入模板下载")
    @GetMapping("/downloadUsherExcelTemp")
    public void downloadUsherExcelTemp(HttpServletResponse response) throws IOException {
        Resource resource = new ClassPathResource("file/UsherInfoExcelTemp.xlsx");
        //File file = resource.getFile();
        //InputStream inputStream = new FileInputStream(file);
        InputStream inputStream = resource.getInputStream();

        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("UTF-8");
        String fileName = URLEncoder.encode("引导员信息导入模板", "UTF-8");
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

    @ApiOperation(value = "网点引导员信息-Excel数据导入")
    @PostMapping("/uploadUsherData")
    public ImportExcelResponse uploadUsherData(@RequestParam(value = "excelFile") @ApiParam(value = "Excel文件") MultipartFile excelFile, HttpServletRequest request) {
        ImportExcelResponse response = new ImportExcelResponse();
        response.setStatus(true);
        response.setErrorRows(new ArrayList<>());
        if (excelFile == null) {
            throw new BizException("请上传网点引导员信息Excel文件进行数据导入操作！");
        }
        try {
            EasyExcel.read(excelFile.getInputStream(), UsherExcelData.class, new UsherExcelListener(this.usherService, getCurrentUserId(request), response)).sheet().doRead();
        }
        catch (IOException e) {
            throw new BizException("网点引导员信息-Excel数据导入失败");
        }
        return response;
    }

    @ApiOperation(value = "网点引导员人数控制-Excel数据导入")
    @PostMapping("/uploadUsherPopulationData")
    public ImportExcelResponse uploadUsherPopulationData(@RequestParam(value = "excelFile") @ApiParam(value = "Excel文件") MultipartFile excelFile, HttpServletRequest request) {
        ImportExcelResponse response = new ImportExcelResponse();
        response.setStatus(true);
        response.setErrorRows(new ArrayList<>());
        if (excelFile == null) {
            throw new BizException("请上传网点引导员人数控制Excel文件进行数据导入操作！");
        }
        try {
            EasyExcel.read(excelFile.getInputStream(), UsherPopulationExcelData.class, new UsherPopulationExcelListener(this.usherService, getCurrentUserId(request), response)).sheet().doRead();
        }
        catch (IOException e) {
            throw new BizException("网点引导员人数控制-Excel数据导入失败");
        }
        return response;
    }

    @ApiOperation(value = "网点引导员人数控制-导出Excel数据")
    @GetMapping("/downloadUsherPopulationData")
    public void downloadUsherPopulationData(HttpServletResponse response) throws IOException {
        List<UsherPopulationDO> listData = this.usherService.selectUsherPopulation("");
        List<UsherPopulationExcelData> excelData = new ArrayList<>();
        for (int i = 0; i < listData.size(); i++) {
            UsherPopulationExcelData rowData = new UsherPopulationExcelData();
            PropertyUtil.copyProperties(listData.get(i), rowData);
            excelData.add(rowData);
        }

        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("UTF-8");
        String fileName = URLEncoder.encode("网点引导员人数控制", "UTF-8");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");

        EasyExcel.write(response.getOutputStream(), UsherPopulationExcelData.class).sheet("模板").doWrite(excelData);
    }

    @ApiOperation(value = "查询网点引导员人数控制")
    @ApiImplicitParam(name = "orgName", value = "机构名称", required = false)
    @GetMapping("/selectUsherPopulation")
    public List<UsherPopulationDO> selectUsherPopulation(@RequestParam(value = "orgName", required = false) String orgName, HttpServletRequest request) {
        return this.usherService.selectUsherPopulation(orgName);
    }

    @ApiOperation(value = "保存网点引导员人数控制-多笔数据保存")
    @PostMapping("/saveUsherPopulation")
    public Integer saveUsherPopulation(@RequestBody @ApiParam(value = "网点引导员人数控制数据列表") List<UsherPopulationDTO> usherPopulationDTOList, HttpServletRequest request) {
        return this.usherService.saveLimit(usherPopulationDTOList, getCurrentUserId(request));
    }

    @ApiOperation(value = "批量设置网点引导员人数控制")
    @ApiImplicitParam(name = "limit", value = "人数控制", required = true, paramType = "path")
    @PostMapping("/{limit}")
    public Integer batchSetLimit(@PathVariable Integer limit, HttpServletRequest request) {
        return this.usherService.batchSetLimit(limit, getCurrentUserId(request));
    }

    @GetMapping("/queryUsherByPhone/{phone}")
    @ApiOperation(value = "根据手机号查询引导员信息")
    @ApiImplicitParam(name = "phone", value = "引导员手机号", required = true)
    public List<UsherDO> queryUsherByPhone(@PathVariable("phone") String phone){
        return usherService.queryUsherByPhone(phone);
    }

}
