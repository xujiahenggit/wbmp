package com.bank.manage.controller;

import com.bank.auth.base.BaseController;
import com.bank.core.entity.PageQueryModel;
import com.bank.manage.dos.SvcStatuslogDO;
import com.bank.manage.service.SvcStatuslogService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/svclog")
public class SvcStatuslogController extends BaseController {
    @Autowired
    private SvcStatuslogService ss;

    @PostMapping("/page")
    public IPage<SvcStatuslogDO> queryPage(@RequestBody PageQueryModel pageQueryModel) {
        return ss.queryList(pageQueryModel);
    }
}
