package com.bank.manage.controller;

import com.bank.auth.base.BaseController;
import com.bank.core.entity.PageQueryModel;
import com.bank.manage.dos.DcStatuslogDO;
import com.bank.manage.service.DcStatuslogService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dclog")
public class DcStatuslogController extends BaseController {
    @Autowired
    private DcStatuslogService ds;

    @PostMapping("/page")
    public IPage<DcStatuslogDO> queryPage(@RequestBody PageQueryModel pageQueryModel) {
        return ds.queryList(pageQueryModel);
    }
}
