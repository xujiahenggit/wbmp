package com.bank.manage.service.impl;

import com.bank.manage.dao.TransfilterDao;
import com.bank.manage.dos.TransfilterDO;
import com.bank.manage.service.TransfilterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 帐号信息
 *
 * @author
 * @date 2020-7-4
 */
@Service
@Slf4j
public class TransfilterServiceImpl extends ServiceImpl<TransfilterDao, TransfilterDO> implements TransfilterService {

    @Autowired(required = false)
    private TransfilterDao TransfilterDao;

}
