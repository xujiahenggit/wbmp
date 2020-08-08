package com.bank.manage.controller;

import com.bank.auth.base.BaseController;
import com.bank.core.entity.PageQueryModel;
import com.bank.manage.dos.CassalertDO;
import com.bank.manage.service.BankService;
import com.bank.manage.service.CassalertService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/cassalert")
public class CassalertController extends BaseController {
    @Autowired
    private CassalertService cs;

    @Autowired
    private BankService bs;

    @PostMapping("/page")
    public IPage<CassalertDO> queryPage(@RequestBody PageQueryModel pageQueryModel, HttpServletRequest request) {
        String powerNum = bs.selectByOrgcode(getCurrentUserInfo(request).getOrgId());
        return cs.queryList(pageQueryModel, powerNum);
    }

    @PostMapping("/bank/{strBankNum}")
    public CassalertDO queryBank(@PathVariable String strBankNum, HttpServletRequest request) {
        String orgId = getCurrentUserInfo(request).getOrgId();
        String powerNum = bs.selectByOrgcode(orgId);
        return cs.queryBank(strBankNum, powerNum);
    }

    @PostMapping("/branch/{strBranchNum}")
    public CassalertDO queryBranch(@PathVariable String strBranchNum, HttpServletRequest request) {
        String powerNum = bs.selectByOrgcode(getCurrentUserInfo(request).getOrgId());
        return cs.queryBranch(strBranchNum, powerNum);
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
