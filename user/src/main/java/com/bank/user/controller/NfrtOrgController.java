package com.bank.user.controller;

import com.bank.core.entity.BizException;
import com.bank.core.utils.BigDataFileReader;
import com.bank.user.dos.NfrtOrgDO;
import com.bank.user.service.NfrtOrgService;
import com.bank.user.utils.GetDataUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.util.List;

/**
 * @Author: Andy
 * @Date: 2020/6/3 16:00
 */
@RestController
@RequestMapping("/nfrtorg")
@Slf4j
public class NfrtOrgController {

    @Autowired
    private NfrtOrgService nfrtOrgService;

    @PostMapping("/save")
    public boolean saveNfrtOrgController(){
        try{
            List<List<Object>> listdataorg = BigDataFileReader.parseNfrtOrgText(new File("D:\\bankdata\\NFRT_ORG_ALL_20200316"), "|");
            List<NfrtOrgDO> list= GetDataUtils.getNfrtOrgList(listdataorg);
            nfrtOrgService.saveBatch(list);
        }catch (Exception e){
            log.info("错误"+e);
            throw new BizException("同步失败");
        }
        return true;
    }
}
