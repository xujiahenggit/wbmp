package com.bank.manage.service.impl;

import com.bank.manage.dao.ManageWorkOrderAttachmentDao;
import com.bank.manage.dos.ManageWorkOrderAttachmentDO;
import com.bank.manage.service.ManageWorkOrderAttachmentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 处理附件表 服务实现类
 *
 * @author 代码自动生成
 * @since 2020-07-13
 */
        @Service
        public class ManageWorkOrderAttachmentServiceImpl extends ServiceImpl<ManageWorkOrderAttachmentDao, ManageWorkOrderAttachmentDO>implements ManageWorkOrderAttachmentService {

        @Resource
        ManageWorkOrderAttachmentDao manageWorkOrderAttachmentDao;


}
