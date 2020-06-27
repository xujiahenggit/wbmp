package com.bank.manage.service;

import com.bank.manage.dos.PartorlDownloadRecordDO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @Author: Andy
 * @Date: 2020/5/14 9:33
 */
public interface PartorlDownloadRecordService extends IService<PartorlDownloadRecordDO> {
    /**
     * 下载 巡查记录
     * @param partorlIds 巡查记录ID 列表
     */
    String getZpiFile(List<Integer> partorlIds);
}
