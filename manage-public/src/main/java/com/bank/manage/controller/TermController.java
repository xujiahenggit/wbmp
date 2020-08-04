package com.bank.manage.controller;

import com.bank.auth.base.BaseController;
import com.bank.manage.dos.TermDO;
import com.bank.manage.service.TermService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/term")
public class TermController extends BaseController {
    @Autowired
    private TermService ts;

    @PostMapping("/list")
    public List<TermDO> queryList() {
        return ts.queryList();
    }

}
