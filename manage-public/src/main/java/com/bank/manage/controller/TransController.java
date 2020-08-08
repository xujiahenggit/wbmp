package com.bank.manage.controller;

import com.bank.auth.base.BaseController;
import com.bank.core.entity.PageQueryModel;
import com.bank.manage.dos.TransDO;
import com.bank.manage.service.BankService;
import com.bank.manage.service.TransService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/trans")
public class TransController extends BaseController {
    @Autowired
    private TransService ts;

    @Autowired
    private BankService bs;

    @PostMapping("/list")
    public List<TransDO> queryList() {
        return ts.list();
    }

    @PostMapping("/page")
    public IPage<TransDO> queryPage(@RequestBody PageQueryModel pageQueryModel, HttpServletRequest request) {
        String powerNum = bs.selectByOrgcode(getCurrentUserInfo(request).getOrgId());
        return ts.queryPage(pageQueryModel,powerNum);
    }

    @PostMapping("/export")
    public void export(@RequestBody PageQueryModel pageQueryModel, HttpServletResponse response) {
        ts.export(pageQueryModel, response);
    }

    @PostMapping("/count/{termNum}")
    public Map queryCount(@PathVariable String termNum) {
        return ts.queryCount(termNum);
    }
}
