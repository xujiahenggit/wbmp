package com.bank.manage.controller;

import com.alibaba.excel.EasyExcel;
import com.bank.auth.base.BaseController;
import com.bank.core.entity.BizException;
import com.bank.core.entity.PageQueryModel;
import com.bank.log.annotation.SystemLog;
import com.bank.manage.dto.TacctInfoDTO;
import com.bank.manage.excel.AccountingModelExcel;
import com.bank.manage.excel.ImportExcelResponse;
import com.bank.manage.listener.AccountingModelExcelListener;
import com.bank.manage.service.TacctInfoService;
import com.bank.manage.vo.TacctInfoVO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * 帐号及帐号参数
 *
 * @author
 * @date 2020-07-03
 */
@Api(tags = "帐号信息接口")
@RestController
@RequestMapping("/tacctInfo")
public class TacctInfoController extends BaseController {

    @Autowired
    private TacctInfoService tacctInfoService;

    @PostMapping("/list")
    @ApiOperation(value = "帐号信息查询")
    @ApiImplicitParam(name = "pageQueryModel", value = "帐号指定查询", required = true, paramType = "body", dataType = "PageQueryModel")
    public IPage<TacctInfoVO> queryList(@RequestBody PageQueryModel pageQueryModel) {
    	return this.tacctInfoService.queryList(pageQueryModel);
    }
    
    
    @PostMapping("/save")
    @ApiOperation(value = "新增帐号信息")
    @ApiImplicitParam(name = "tacctInfoDTO", value = "帐号及帐号参数", required = true, paramType = "body", dataType = "TacctInfoDTO")
    @SystemLog(logModul = "帐号及帐号参数", logType = "新增", logDesc = "新增帐号信息")
    public Boolean save(@RequestBody TacctInfoDTO tacctInfoDTO, HttpServletRequest request) {
        //TokenUserInfo tokenUserInfo = getCurrentUserInfo(request);
        return this.tacctInfoService.save(tacctInfoDTO);
    }

    @DeleteMapping("/delete/{acctnos}")
    @ApiOperation(value = "删除帐号信息")
    @ApiImplicitParam(name = "acctnos", value = "帐号", required = true, paramType = "path")
    @SystemLog(logModul = "帐号及帐号参数", logType = "删除", logDesc = "删除帐号信息")
    public Boolean delete(@PathVariable("acctnos") List<String> acctnos) {
        return this.tacctInfoService.delete(acctnos);
    }
    
    @PostMapping("/select/{acctno}")
    @ApiOperation(value = "根据帐号查询帐号信息")
    @ApiImplicitParam(name = "acctno", value = "帐号查询", required = true, paramType = "path")
    public String selectByAcctno(@PathVariable String acctno) {
    	return this.tacctInfoService.selectByAcctNo(acctno);
    }

    @PostMapping("/update")
    @ApiOperation(value = "修改账号信息")
    @ApiImplicitParam(name = "tacctInfoDTO", value = "修改账号信息", required = true, paramType = "body", dataType = "TacctInfoDTO")
    @SystemLog(logModul = "帐号及帐号参数", logType = "修改", logDesc = "修改账号信息")
    public Boolean updateTacctInfo(@RequestBody TacctInfoDTO tacctInfoDTO, HttpServletRequest request) {
        //TokenUserInfo tokenUserInfo = getCurrentUserInfo(request);
        return this.tacctInfoService.updateTacctInfo(tacctInfoDTO);
    }

    @PostMapping("/importTaccountingOrder")
    @ApiOperation(value = "对账模式管理Excel表格导入")
    public ImportExcelResponse importDevice(@RequestParam(value = "excelFile") @ApiParam(value = "Excel文件") MultipartFile excelFile,
                                            HttpServletRequest request){
        ImportExcelResponse response = new ImportExcelResponse();
        response.setStatus(true);
        response.setErrorRows(new ArrayList<>());
        if (excelFile == null) {
            throw new BizException("请上传对账模式管理Excel文件进行数据导入操作！");
        }
        //String userId = getCurrentUserId(request);
        try {
           EasyExcel.read(excelFile.getInputStream(), AccountingModelExcel.class, new AccountingModelExcelListener(this.tacctInfoService, response)).sheet().doRead();
        }
        catch (Exception e) {
            throw new BizException("对账模式管理Excel数据导入失败");
        }
        return response;
    }

    @ApiOperation(value = "对账模式管理Excel导入模板下载")
    @GetMapping("/downloadModel")
    public void downloadModel(HttpServletResponse response) throws IOException {

        Resource resource = new ClassPathResource("file/AccountingModelExcel.xlsx");
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
}
