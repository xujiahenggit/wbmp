package com.bank.manage.service.impl;

import cn.hutool.core.util.IdUtil;
import com.bank.core.entity.BizException;
import com.bank.core.entity.FileDo;
import com.bank.core.entity.TokenUserInfo;
import com.bank.core.sysConst.ConstFile;
import com.bank.core.sysConst.NewProcessStatusFile;
import com.bank.core.sysConst.SysStatus;
import com.bank.core.utils.ConfigFileReader;
import com.bank.core.utils.FileUploadUtils;
import com.bank.manage.dao.CardSuppleDao;
import com.bank.manage.dos.CardSuppleDO;
import com.bank.manage.dos.UsherDO;
import com.bank.manage.dos.WorkSuppleDO;
import com.bank.manage.dto.CardSuppleDto;
import com.bank.manage.dto.InfoMessageDto;
import com.bank.manage.service.CardSuppleService;
import com.bank.manage.service.UsherService;
import com.bank.manage.service.WorkSuppleService;
import com.bank.manage.vo.CardSuppleDeleteVo;
import com.bank.manage.vo.CardSupplePassRejectVo;
import com.bank.manage.vo.CardSuppleQueryVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * @Author: Andy
 * @Date: 2020/5/28 10:44
 */
@Service
public class CardSuppleServiceImpl extends ServiceImpl<CardSuppleDao, CardSuppleDO> implements CardSuppleService {

    @Autowired
    private ConfigFileReader configFileReader;

    @Resource
    private CardSuppleDao cardSuppleDao;

    @Resource
    private WorkSuppleService workSuppleService;


    @Resource
    private UsherService usherService;

    /**
     * 获取待办列表
     * @param cardSuppleQueryVo 查询参数
     * @return
     */
    @Override
    public IPage<CardSuppleDto> getList(CardSuppleQueryVo cardSuppleQueryVo) {
        // 用手机号码查询 引导员 机构号
        UsherDO usherDO=usherService.selectUsherByPhone(cardSuppleQueryVo.getUserPhone());
        if(usherDO==null){
            throw new BizException("未查到引导员信息");
        }
        Page<CardSuppleDto> page=new Page<>(cardSuppleQueryVo.getPageIndex(),cardSuppleQueryVo.getPageSize());
        return cardSuppleDao.getList(page,usherDO.getOrgId());
    }

    /**
     * 获取通知列表
     * @param cardSuppleQueryVo 查询参数
     * @return
     */
    @Override
    public IPage<InfoMessageDto> getInfomationList(CardSuppleQueryVo cardSuppleQueryVo) {
        Page<CardSuppleDto> page=new Page<>(cardSuppleQueryVo.getPageIndex(),cardSuppleQueryVo.getPageSize());
        UsherDO usherDO=usherService.selectUsherByPhone(cardSuppleQueryVo.getUserPhone());
        if(usherDO==null){
            throw new BizException("没有查到引导员信息！");
        }
        return cardSuppleDao.getInfomationList(page,usherDO.getUsherId());
    }

    /**
     * 审核通过
     * @param cardSupplePassRejectVo 审核参数
     * @param tokenUserInfo 当前登录用户
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean passProcess(CardSupplePassRejectVo cardSupplePassRejectVo, TokenUserInfo tokenUserInfo) {
       try{
           CardSuppleDO cardSuppleDO=getCardUpdateModel(cardSupplePassRejectVo,tokenUserInfo,NewProcessStatusFile.CHECK_TYPE_PASS);
           this.updateById(cardSuppleDO);
           return true;
       }catch (Exception e){
           throw new BizException("审批失败");
       }
    }

    /**
     * 驳回审批
     * @param cardSupplePassRejectVo 审核参数
     * @param tokenUserInfo 当前登录用户
     * @return
     */
    @Override
    public boolean rejectProcess(CardSupplePassRejectVo cardSupplePassRejectVo, TokenUserInfo tokenUserInfo) {
        try{
            CardSuppleDO cardSuppleDO=getCardUpdateModel(cardSupplePassRejectVo,tokenUserInfo,NewProcessStatusFile.CHECK_TYPE_REJECT);
            this.updateById(cardSuppleDO);
            return true;
        }catch (Exception e){
            throw new BizException("审批失败");
        }
    }


    /**
     * 删除消息通知
     * @param list 待删除列表
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteInfomation(List<CardSuppleDeleteVo> list) {
        try{
            if(list.size()>0){
                for (CardSuppleDeleteVo item:list){
                    //补卡申请
                    if(SysStatus.MESSAGE_TYPE_CARD.equals(item.getType())){
                        CardSuppleDO cardSuppleDO=new CardSuppleDO();
                        cardSuppleDO.setCardSuppleId(item.getId());
                        cardSuppleDO.setCardSuppleDeleteFlag(SysStatus.FLAG_DELETE);
                        this.updateById(cardSuppleDO);
                    }else if(SysStatus.MESSAGE_TYPE_WORD.equals(item.getType())){
                        WorkSuppleDO workSuppleDO=new WorkSuppleDO();
                        workSuppleDO.setWorkSuppleId(item.getId());
                        workSuppleDO.setWorkSuppleDeleteFlag(SysStatus.FLAG_DELETE);
                        workSuppleService.updateById(workSuppleDO);
                    }
                }
            }else{
                throw new BizException("请选择删除列表！");
            }
            return true;
        }catch (Exception e){
            if(e instanceof BizException){
                throw new BizException(((BizException) e).getErrorMsg());
            }else {
                throw new BizException("删除失败");
            }
        }
    }

    /**
     * 通过编号 获取详细信息
     * @param cardSuppleId 编号
     * @return
     */
    @Override
    public CardSuppleDto getInfo(Integer cardSuppleId) {
        CardSuppleDto cardSuppleDto=cardSuppleDao.getInfo(cardSuppleId);
        if(cardSuppleDto!=null){
            cardSuppleDto.setCardSuppleImg(configFileReader.getHTTP_PATH()+cardSuppleDto.getCardSuppleImg());
        }
        return cardSuppleDao.getInfo(cardSuppleId);
    }

    /**
     * 上传文件
     * @param file 文件
     * @return
     */
    @Override
    public FileDo upLoadFile(MultipartFile file) {
        FileDo fileDo=null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            //第一层目录 按 日期创建
            String fist_tab=sdf.format(new Date());
            //上传路径
            String uploadPath=configFileReader.getCARD_FILE_PATH()+"/"+fist_tab;
            //访问路径
            String accessPath=configFileReader.getHTTP_PATH()+configFileReader.getCARD_ACCESS_PATH()+"/"+fist_tab;
            //原文件名称
            String filename = file.getOriginalFilename();
            //用UUID
            String c_fileName= IdUtil.randomUUID()+filename.substring(filename.lastIndexOf("."));
            fileDo = FileUploadUtils.FileUpload(file,uploadPath,accessPath,c_fileName);
            return fileDo;
        } catch (Exception e) {
            throw new BizException("图片上传失败！");
        }
    }

    /**
     * 构建模型
     * @param cardSupplePassRejectVo 审批参数
     * @param tokenUserInfo 当前用户
     * @param updateType 审批类型 通过  驳回
     * @return
     */
    private CardSuppleDO getCardUpdateModel(CardSupplePassRejectVo cardSupplePassRejectVo, TokenUserInfo tokenUserInfo,String updateType){
        try{
            CardSuppleDO cardSuppleDO=new CardSuppleDO();
            //设置编号
            cardSuppleDO.setCardSuppleId(cardSupplePassRejectVo.getCardSuppleId());
            if(NewProcessStatusFile.CHECK_TYPE_PASS.equals(updateType)){
                //设置状态为 审核通过
                cardSuppleDO.setCardSuppleState(NewProcessStatusFile.PROCESS_PASS);
            }else if(NewProcessStatusFile.CHECK_TYPE_REJECT.equals(updateType)){
                //设置状态为 驳回
                cardSuppleDO.setCardSuppleState(NewProcessStatusFile.PROCESS_REJECT);
                //设置驳回理由
                cardSuppleDO.setCardSuppleRejectResion(cardSupplePassRejectVo.getCartSuppleRejectResion());
            }
            //设置审核人工号
            cardSuppleDO.setCardSuppleProcessUserId(tokenUserInfo.getUserId());
            //设置审核人姓名
            cardSuppleDO.setCardSuppleProcessUserName(tokenUserInfo.getUserName());
            //设置审批时间
            cardSuppleDO.setCardSuppleProcessTime(LocalDateTime.now());
            return cardSuppleDO;
        }catch (Exception e){
            throw new BizException("操作异常");
        }
    }
}
