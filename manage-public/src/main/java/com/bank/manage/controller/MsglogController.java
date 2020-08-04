package com.bank.manage.controller;

import com.bank.auth.base.BaseController;
import com.bank.core.entity.PageQueryModel;
import com.bank.manage.dos.MsglogDO;
import com.bank.manage.service.MsglogService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/msglog")
public class MsglogController extends BaseController {
    @Autowired
    private MsglogService ms;

    @PostMapping("/page")
    public IPage<MsglogDO> queryPage(@RequestBody PageQueryModel pageQueryModel) {
        return ms.queryList(pageQueryModel);
    }
}
