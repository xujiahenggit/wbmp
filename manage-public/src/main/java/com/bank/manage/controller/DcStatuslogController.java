package com.bank.manage.controller;

import com.bank.auth.base.BaseController;
import com.bank.manage.dos.DcStatuslogDO;
import com.bank.manage.service.DcStatuslogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/dclog")
public class DcStatuslogController extends BaseController {
    @Autowired
    private DcStatuslogService ds;

    @PostMapping("/page/{termNum}")
    public List<DcStatuslogDO> queryPage(@PathVariable String termNum) {
        return ds.queryList(termNum);
    }
}
