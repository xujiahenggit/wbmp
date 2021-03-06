package com.bank.manage.controller;

import com.bank.auth.base.BaseController;
import com.bank.manage.dos.BankDO;
import com.bank.manage.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/bank")
public class BankController extends BaseController {
    @Autowired
    private BankService bankService;

    @PostMapping("/list")
    public List<BankDO> queryList(HttpServletRequest request) {
        String powerNum = bankService.selectByOrgcode(getCurrentUserInfo(request).getOrgId());
        return bankService.queryList(powerNum);
    }
}
