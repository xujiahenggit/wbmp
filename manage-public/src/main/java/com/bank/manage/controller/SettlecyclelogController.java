package com.bank.manage.controller;

import com.bank.auth.base.BaseController;
import com.bank.manage.dos.SettlecyclelogDO;
import com.bank.manage.service.SettlecyclelogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/settlecyclelog")
public class SettlecyclelogController extends BaseController {
    @Autowired
    private SettlecyclelogService ss;

    @PostMapping("/noclean/{termNum}")
    public SettlecyclelogDO getOne(@PathVariable String termNum) {
        SettlecyclelogDO one = ss.getOne(termNum);
        return one ==null?new SettlecyclelogDO():one;
    }
}
