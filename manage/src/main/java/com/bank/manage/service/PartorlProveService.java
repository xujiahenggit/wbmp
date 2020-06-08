package com.bank.manage.service;

import com.bank.core.entity.FileDo;
import com.bank.manage.dos.PartorlProveDO;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @Author: Andy
 * @Date: 2020/5/12 15:09
 */
public interface PartorlProveService extends IService<PartorlProveDO> {
    /**
     * 证明文件上传
     * @param file 文件
     * @return
     */
    FileDo proveFileUpload(MultipartFile file);

    /**
     * 用 巡查记录内容ID 批量删除 证明文件
     * @param listRecordItemIds 巡查内容ID
     */
    void deleteProve(List<Integer> listRecordItemIds);

    /**
     * 用ID 查找证明文件路径列表
     * @param partorlRecordId 巡查记录ID
     * @return
     */
    List<String> selectFilePathByRecordId(Integer partorlRecordId);
}
