package com.bank.manage.controller;

import com.bank.auth.base.BaseController;
import com.bank.manage.dos.DcStatusDO;
import com.bank.manage.service.DcStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/dcstatus")
public class DcStatusController extends BaseController {
    @Autowired
    private DcStatusService ds;

    @PostMapping("/page/{termNum}")
    public List<DcStatusDO> queryPage(@PathVariable String termNum) {
        return ds.queryList(termNum);
    }
}
