package com.bank.manage.controller;

import com.bank.auth.base.BaseController;
import com.bank.core.entity.PageQueryModel;
import com.bank.manage.dos.PersonnelDO;
import com.bank.manage.service.BankService;
import com.bank.manage.service.PersonnelService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonnelController extends BaseController {
    @Autowired
    private PersonnelService ps;

    @Autowired
    private BankService bs;

    @PostMapping("/page")
    public IPage<PersonnelDO> queryPage(@RequestBody PageQueryModel pageQueryModel, HttpServletRequest request) {
        String powerNum = bs.selectByOrgcode(getCurrentUserInfo(request).getOrgId());
        return ps.queryList(pageQueryModel,powerNum);
    }

    @PostMapping("/update")
    public Boolean update(@RequestBody PersonnelDO personnelDO) {
        return ps.update(personnelDO);
    }

    @PostMapping("/save")
    public Boolean save(@RequestBody PersonnelDO personnelDO) {
        return ps.save(personnelDO);
    }

    @PostMapping("/del")
    public Boolean delete(@RequestBody List<String> list) {
        return ps.delete(list);
    }

    @PostMapping("/getone/{strOperatorNum}")
    public PersonnelDO getOne(@PathVariable String strOperatorNum) {
        return ps.getOne(strOperatorNum);
    }
}
