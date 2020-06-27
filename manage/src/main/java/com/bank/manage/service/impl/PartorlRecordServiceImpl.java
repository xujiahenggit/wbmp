package com.bank.manage.service.impl;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import com.bank.core.utils.NetUtil;
import com.bank.core.utils.StringSplitUtil;
import com.bank.manage.dos.*;
import com.bank.manage.dto.PartorlRecordDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bank.core.entity.BizException;
import com.bank.core.entity.TokenUserInfo;
import com.bank.core.sysConst.NewProcessStatusFile;
import com.bank.core.sysConst.PartorlConstFile;
import com.bank.core.utils.DateUtils;
import com.bank.manage.dao.PartorlRecordDao;
import com.bank.manage.dto.PartorlRecordHeadDto;
import com.bank.manage.excel.partorl.PartorlExcelEntity;
import com.bank.manage.service.PartorlProcessService;
import com.bank.manage.service.PartorlProveService;
import com.bank.manage.service.PartorlRecordItemService;
import com.bank.manage.service.PartorlRecordService;
import com.bank.manage.vo.PartorlRecordQueryVo;
import com.bank.manage.vo.partorlRecord.PartorlContentVo;
import com.bank.manage.vo.partorlRecord.PartorlProveVo;
import com.bank.manage.vo.partorlRecord.PartorlRecordVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import cn.hutool.core.util.StrUtil;

/**
 * @Author: Andy
 * @Date: 2020/5/12 15:10
 */
@Service
public class PartorlRecordServiceImpl extends ServiceImpl<PartorlRecordDao, PartorlRecordDO> implements PartorlRecordService {

    @Resource
    private PartorlRecordDao partorlRecordDao;

    @Resource
    private PartorlRecordItemService partorlRecordItemService;

    @Resource
    private PartorlProveService partorlProveService;

    @Resource
    private PartorlProcessService partorlProcessService;

    @Resource
    private NetUtil netUtil;

    /**
     * 分页查询 巡查记录
     *
     * @param partorlRecordQueryVo 查询参数
     * @return
     */
    @Override
    public IPage<PartorlRecordDto> getPageRecord(PartorlRecordQueryVo partorlRecordQueryVo) {
        Page<PartorlRecordDto> page = new Page<>(partorlRecordQueryVo.getPageIndex(), partorlRecordQueryVo.getPageSize());
        return this.partorlRecordDao.selectRecordPage(page, partorlRecordQueryVo);
    }

    /**
     * 保存巡查内容信息
     * 一。先删除之前删除的巡查内容。
     * 二。保存记录
     * 1.保存巡查内容 主表
     * 2.保存巡查记录 从表
     * 2.1 保存巡查记录 证明文件
     *
     * @param partorlRecordVo
     * @param tokenUserInfo   当前用户信息
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean saveRecord(PartorlRecordVo partorlRecordVo, TokenUserInfo tokenUserInfo) {
        try {
            PartorlProcessDO partorlProcessDO = this.partorlProcessService.getById(partorlRecordVo.getProcessId());
            //判断是新增还是更新
            if (partorlProcessDO != null && 0 != partorlProcessDO.getPartorlRecordId()) {
                //删除
                deletePartorlRecord(partorlProcessDO.getPartorlRecordId());
            }
            savePartorlRecord(partorlRecordVo, tokenUserInfo);
            return true;
        }
        catch (Exception e) {
            throw new BizException("保存巡查内容失败！");
        }
    }

    /**
     * 提交 巡查内容
     * 1.保存 巡查内容
     * 2.判断是否超时 更新超时状态
     * 3.判断是否正常 更长正常状态
     * 4.修改 流程状态
     *
     * @param partorlRecordVo 巡查内容
     * @param tokenUserInfo   当前登录用户信息
     * @return
     */
    @Override
    public boolean submitPartorlRecord(PartorlRecordVo partorlRecordVo, TokenUserInfo tokenUserInfo) {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            PartorlProcessDO partorlProcessDO = this.partorlProcessService.getById(partorlRecordVo.getProcessId());
            //判断是新增还是更新
            if (partorlProcessDO != null && 0 != partorlProcessDO.getPartorlRecordId()) {
                //删除
                deletePartorlRecord(partorlProcessDO.getPartorlRecordId());
            }
            //如果为空则保存
            PartorlRecordDO partorlRecordDO = savePartorlRecord(partorlRecordVo, tokenUserInfo);
            //判断是否超时
            String partorlRecordDate = partorlRecordDO.getPartorlDate().toString();
            //当前日期
            String dateNow = sf.format(new Date());
            //判断 填表日期 与 当前时间大小
            boolean isOrverTime = DateUtils.conparDate(partorlRecordDate, dateNow, "yyyy-MM-dd");
            String orverTime = isOrverTime == true ? PartorlConstFile.PARTOR_OVERTIME_TRUE : PartorlConstFile.PARTOR_OVERTIME_FALSE;
            String nomal = PartorlConstFile.PARTOR_NOMAL_TRUE;
            //查询 是否有 巡查内容为 否的数据
            QueryWrapper<PartorlRecordItemDO> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("PARTORL_RECORD_ID", partorlRecordDO.getPartorlRecordId());
            queryWrapper.eq("PARTORL_RESULT", PartorlConstFile.PARTOR_NOMAL_FALSE);
            List<PartorlRecordItemDO> list = this.partorlRecordItemService.list(queryWrapper);
            //巡查内容 如过有一条 是 否 则整个巡查记录状态为否
            if (list.size() > 0) {
                nomal = PartorlConstFile.PARTOR_NOMAL_FALSE;
            }
            //3.修改 超时 正常状态
            PartorlRecordDO updateDo = new PartorlRecordDO();
            updateDo.setPartorlRecordId(partorlRecordDO.getPartorlRecordId());
            updateDo.setPartorlOrvertime(orverTime);
            updateDo.setPartorlNomal(nomal);
            this.partorlRecordDao.updateById(updateDo);
            //4.修改流程 状态为 已审核
            PartorlProcessDO newPartorlProcessDo = new PartorlProcessDO();
            //设置编号
            newPartorlProcessDo.setPartorlProcessId(partorlRecordVo.getProcessId());
            newPartorlProcessDo.setPartorlProcessState(NewProcessStatusFile.PROCESS_PASS);
            this.partorlProcessService.updateById(newPartorlProcessDo);
            return true;
        }
        catch (Exception e) {
            throw new BizException("提交巡查内容");
        }
    }

    /**
     * 用ID 获取巡查记录信息
     * @param recordId 巡查记录ID
     * @return
     */
    @Override
    public PartorlExcelEntity getRecordById(Integer recordId) {
        return this.partorlRecordDao.getRecordById(recordId);
    }

    /**
     * 查找 填报内容头
     * @param processId 待办编号
     * @param tokenUserInfo 当前登录用户
     * @return
     */
    @Override
    public PartorlRecordHeadDto getHeadInfo(Integer processId, TokenUserInfo tokenUserInfo) {
        PartorlProcessDO partorlProcessDO = this.partorlProcessService.getById(processId);
        PartorlRecordHeadDto partorlRecordHeadDto = new PartorlRecordHeadDto();
        partorlRecordHeadDto.setOrgName(partorlProcessDO.getPartorlProcessOrgName());
        partorlRecordHeadDto.setPartorlProcessDate(partorlProcessDO.getPartorlProcessDate());
        partorlRecordHeadDto.setSaveUserName(tokenUserInfo.getUserName());
        //如果是已办理的则需要从数据库读取 如果是新填的则与当前时间对比
        if(partorlProcessDO.getPartorlRecordId()!=0){
            PartorlRecordDO partorlRecordDO=this.getById(partorlProcessDO.getPartorlRecordId());
            if(partorlRecordDO!=null){
                if(partorlRecordDO.getPartorlOrvertime()=="0"){
                    partorlRecordHeadDto.setIsOverTime("已超时");
                }else{
                    partorlRecordHeadDto.setIsOverTime("未超时");
                }
            }
        }else{
            Date dateCurrent = new Date();
            Date processDate = DateUtils.localDate2Date(partorlProcessDO.getPartorlProcessDate());
            if (dateCurrent.getTime() >= processDate.getTime()) {
                partorlRecordHeadDto.setIsOverTime("已超时");
            }
            else {
                partorlRecordHeadDto.setIsOverTime("未超时");
            }
        }

        return partorlRecordHeadDto;
    }

    /**
     * 删除巡查记录
     * 1.删除巡查内容
     * 2.删除巡查证明记录
     *
     * @param partorlRecordId 巡查记录信息
     * @return
     */
    private boolean deletePartorlRecord(Integer partorlRecordId) {
        try {
            QueryWrapper<PartorlRecordItemDO> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("PARTORL_RECORD_ID", partorlRecordId);
            List<PartorlRecordItemDO> list = this.partorlRecordItemService.list(queryWrapper);
            //保存 巡查记录内容ID 方便批量删除
            List<Integer> listRecordItemIds = new ArrayList<>();
            //删除证明文件
            if (list.size() > 0) {
                for (PartorlRecordItemDO itemDO : list) {
                    listRecordItemIds.add(itemDO.getPartorlRecordItemId());
                }
                // 删除巡查内容表
                this.partorlRecordItemService.removeByIds(listRecordItemIds);
                // 删除巡查证明文件
                this.partorlProveService.deleteProve(listRecordItemIds);
            }
            return true;
        }
        catch (Exception e) {
            throw e;
        }
    }

    /**
     * 保存巡查 记录内容
     *
     * @param partorlRecordVo 巡查记录内容
     * @param tokenUserInfo   当前登录用户信息
     * @return
     * @throws Exception
     */
    private PartorlRecordDO savePartorlRecord(PartorlRecordVo partorlRecordVo, TokenUserInfo tokenUserInfo) {
        try {
            PartorlProcessDO newProcessDO = this.partorlProcessService.getById(partorlRecordVo.getProcessId());
            //判断是新增还是更新
            if (newProcessDO != null && 0 != newProcessDO.getPartorlRecordId()) {
                //先查询是否已经存在
                PartorlRecordDO isExist = this.partorlRecordDao.selectById(newProcessDO.getPartorlRecordId());
                if (isExist != null) {
                    //保存子项
                    SaveRecordItem(partorlRecordVo.getListContent(), isExist.getPartorlRecordId());
                    return isExist;
                }
            }
            //1.保存巡查内容信息
            PartorlRecordDO partorlRecordDO = getPartorlRecord(partorlRecordVo.getProcessId(), tokenUserInfo);
            this.partorlRecordDao.insert(partorlRecordDO);
            //1.1 更改流程 关联的业务ID
            PartorlProcessDO partorlProcessDO = new PartorlProcessDO();
            partorlProcessDO.setPartorlProcessId(partorlRecordVo.getProcessId());
            partorlProcessDO.setPartorlRecordId(partorlRecordDO.getPartorlRecordId());
            this.partorlProcessService.updateById(partorlProcessDO);

            //保存子项
            SaveRecordItem(partorlRecordVo.getListContent(), partorlProcessDO.getPartorlRecordId());
            return partorlRecordDO;
        }
        catch (Exception e) {
            throw e;
        }
    }

    /**
     * 保存 巡查记录 子项
     * @param listContent 子项列表
     * @param recordId 寻擦记录编号
     */
    private void SaveRecordItem(List<PartorlContentVo> listContent, Integer recordId) {
        for (PartorlContentVo item : listContent) {
            //2.保存巡查记录 从表
            PartorlRecordItemDO partorlRecordItemDO = new PartorlRecordItemDO();
            partorlRecordItemDO.setPartorlRecordId(recordId);
            //设置巡查内容表
            partorlRecordItemDO.setPartorlContentId(item.getPartorlContentId());
            //巡查内容结果
            partorlRecordItemDO.setPartorlResult(item.getPartorlResult());
            //巡查内容备注
            partorlRecordItemDO.setPartorlMark(item.getPartorlMark());
            //保存巡查记录从表内容
            this.partorlRecordItemService.save(partorlRecordItemDO);
            //2.1 保存巡查记录 证明文件
            if (item.getListProve().size() > 0) {
                List<PartorlProveDO> listProve = new ArrayList<>();
                for (PartorlProveVo itemProve : item.getListProve()) {
                    PartorlProveDO partorlProveDO = new PartorlProveDO();
                    //设置巡查记录从表 主键
                    partorlProveDO.setPartorlRecordItemId(partorlRecordItemDO.getPartorlRecordItemId());
                    //设置序号
                    partorlProveDO.setPartorlProveNum(itemProve.getPartorlProveNum());
                    //设置文件名称
                    partorlProveDO.setPartorlProveFileName(itemProve.getPartorlProveFileName());
                    //设置文件存储路径
                    if(itemProve.getPartorlProveFilePath().startsWith("http")){
                        String splitMaterialPath = StringSplitUtil.splitMaterialPath(itemProve.getPartorlProveFilePath(), this.netUtil.getUrlSuffix(""));
                        partorlProveDO.setPartorlProveFilePath(splitMaterialPath);
                    }else{
                        partorlProveDO.setPartorlProveFilePath(itemProve.getPartorlProveFilePath());
                    }
                    //设置文件大小
                    partorlProveDO.setPartorlProveFileSize(itemProve.getPartorlProveFileSize());
                    listProve.add(partorlProveDO);
                }
                this.partorlProveService.saveBatch(listProve);
            }
        }
    }




    /**
     * 构建 巡查记录 模型
     *
     * @param processId     流程编号
     * @param tokenUserInfo 当前登录用户信息
     * @return
     */
    private PartorlRecordDO getPartorlRecord(Integer processId, TokenUserInfo tokenUserInfo) {
        PartorlRecordDO partorlRecordDO = new PartorlRecordDO();
        //待办事项编号
        partorlRecordDO.setNewprocessId(processId);
        //填报日期
        partorlRecordDO.setPartorlDate(LocalDate.now());
        //填报人
        partorlRecordDO.setPartorlUser(tokenUserInfo.getUserName());
        //填报人 userID
        partorlRecordDO.setPartorlUserId(tokenUserInfo.getUserId());
        //是否正常 默认为 是
        partorlRecordDO.setPartorlNomal(PartorlConstFile.PARTOR_NOMAL_TRUE);
        //是否超时 默认为 否
        partorlRecordDO.setPartorlOrvertime(PartorlConstFile.PARTOR_OVERTIME_FALSE);
        //设置机构号
        partorlRecordDO.setOrgId(tokenUserInfo.getOrgId());
        //设置机构名称
        partorlRecordDO.setOrgName(tokenUserInfo.getOrgName());
        //创建人
        partorlRecordDO.setCreateUser(tokenUserInfo.getUserName());
        //创建时间
        partorlRecordDO.setCreateTime(LocalDateTime.now());
        //更新人
        partorlRecordDO.setUpdateUser(tokenUserInfo.getUserName());
        //更新时间
        partorlRecordDO.setUpdateTime(LocalDateTime.now());
        return partorlRecordDO;
    }
}
