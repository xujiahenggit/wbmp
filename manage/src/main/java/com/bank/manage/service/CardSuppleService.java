package com.bank.manage.service;

import com.bank.core.entity.FileDo;
import com.bank.core.entity.TokenUserInfo;
import com.bank.manage.dos.CardSuppleDO;
import com.bank.manage.dto.CardSuppleDto;
import com.bank.manage.dto.InfoMessageDto;
import com.bank.manage.vo.CardSuppleDeleteVo;
import com.bank.manage.vo.CardSupplePassRejectVo;
import com.bank.manage.vo.CardSuppleQueryVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Author: Andy
 * @Date: 2020/5/28 10:44
 */
public interface CardSuppleService extends IService<CardSuppleDO> {
    /**
     * 获取待办列表
     * @param cardSuppleQueryVo 查询参数
     * @return
     */
    IPage<CardSuppleDto> getList(CardSuppleQueryVo cardSuppleQueryVo);

    /**
     * 获取消息通知
     * @param cardSuppleQueryVo 查询参数
     * @return
     */
    IPage<InfoMessageDto> getInfomationList(CardSuppleQueryVo cardSuppleQueryVo);

    /**
     * 审核通过
     * @param cardSupplePassRejectVo 审核参数
     * @param tokenUserInfo 当前登录用户
     * @return
     */
    boolean passProcess(CardSupplePassRejectVo cardSupplePassRejectVo, TokenUserInfo tokenUserInfo);

    /**
     * 驳回审批
     * @param cardSupplePassRejectVo 审核参数
     * @param tokenUserInfo 当前登录用户
     * @return
     */
    boolean rejectProcess(CardSupplePassRejectVo cardSupplePassRejectVo, TokenUserInfo tokenUserInfo);

    /**
     * 删除消息通知
     * @param list 待删除项
     * @return
     */
    boolean deleteInfomation(List<CardSuppleDeleteVo> list);

    /**
     * 通过ID 获取详细信息
     * @param cardSuppleId 编号
     * @return
     */
    CardSuppleDto getInfo(Integer cardSuppleId);

    /**
     * 上传 文件
     * @param file 文件
     * @return
     */
    FileDo upLoadFile(MultipartFile file);
}
