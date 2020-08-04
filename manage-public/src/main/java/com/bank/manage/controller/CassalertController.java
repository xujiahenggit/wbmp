package com.bank.manage.controller;

import com.bank.auth.base.BaseController;
import com.bank.core.entity.PageQueryModel;
import com.bank.manage.dos.CassalertDO;
import com.bank.manage.service.CassalertService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cassalert")
public class CassalertController extends BaseController {
    @Autowired
    private CassalertService cs;

    @PostMapping("/page")
    public IPage<CassalertDO> queryPage(@RequestBody PageQueryModel pageQueryModel) {
        return cs.queryList(pageQueryModel);
    }

    @PostMapping("/bank/{strBankNum}")
    public CassalertDO queryBank(@PathVariable String strBankNum) {
        return cs.queryBank(strBankNum);
    }

    @PostMapping("/branch/{strBranchNum}")
    public CassalertDO queryBranch(@PathVariable String strBranchNum) {
        return cs.queryBranch(strBranchNum);
    }

    @PostMapping("/set")
    public boolean set(@RequestBody CassalertDO CassalertDO) {
        return cs.save(CassalertDO);
    }

    @PostMapping("/bankset")
    public boolean bankSet(@RequestBody CassalertDO CassalertDO) {
        return cs.saveBank(CassalertDO);
    }

    @PostMapping("/branchset")
    public boolean branchSet(@RequestBody CassalertDO CassalertDO) {
        return cs.saveBranch(CassalertDO);
    }

}
