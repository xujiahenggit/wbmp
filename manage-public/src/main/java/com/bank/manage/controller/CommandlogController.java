package com.bank.manage.controller;

import com.bank.auth.base.BaseController;
import com.bank.core.entity.PageQueryModel;
import com.bank.manage.dos.CommandlogDO;
import com.bank.manage.service.CommandlogService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/commandlog")
public class CommandlogController extends BaseController {
    @Autowired
    private CommandlogService cs;

    @PostMapping("/page")
    public IPage<CommandlogDO> queryPage(@RequestBody PageQueryModel pageQueryModel) {
        return cs.queryList(pageQueryModel);
    }
}
