package com.bank.manage.controller;

import com.bank.auth.base.BaseController;
import com.bank.manage.dos.TransfilterDO;
import com.bank.manage.service.TransfilterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/transfilter")
public class TransfilterController extends BaseController {

    @Autowired
    private TransfilterService tfs;

    @PostMapping("/list")
    public List<TransfilterDO> queryList() {
        return tfs.list();
    }

    @PostMapping("/save")
    public boolean save(@RequestBody TransfilterDO transfilterDO) {
        return tfs.saveOrUpdate(transfilterDO);
    }

    @PostMapping("/del/{id}")
    public boolean del(@PathVariable Integer id) {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(id);
        return tfs.removeByIds(list);
    }
}
