package com.bank.manage.controller;

import com.bank.auth.base.BaseController;
import com.bank.manage.dos.TermDO;
import com.bank.manage.service.BankService;
import com.bank.manage.service.TermService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/term")
public class TermController extends BaseController {
    @Autowired
    private TermService ts;

    @Autowired
    private BankService bs;

    @PostMapping("/list")
    public List<TermDO> queryList(HttpServletRequest request) {
        String powerNum = bs.selectByOrgcode(getCurrentUserInfo(request).getOrgId());
        return ts.queryList(powerNum);
    }

}
