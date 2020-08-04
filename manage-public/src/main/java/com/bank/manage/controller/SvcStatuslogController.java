package com.bank.manage.controller;

import com.bank.auth.base.BaseController;
import com.bank.manage.dos.SvcStatuslogDO;
import com.bank.manage.service.SvcStatuslogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/svclog")
public class SvcStatuslogController extends BaseController {
    @Autowired
    private SvcStatuslogService ss;

    @PostMapping("/page/{termNum}")
    public List<SvcStatuslogDO> queryPage(@PathVariable String termNum) {
        return ss.queryList(termNum);
    }
}
