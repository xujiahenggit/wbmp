package com.bank.manage.service.impl;

import cn.hutool.core.util.StrUtil;
import com.bank.core.entity.BizException;
import com.bank.core.entity.TokenUserInfo;
import com.bank.core.enums.ConstantEnum;
import com.bank.core.sysConst.NewProcessStatusFile;
import com.bank.manage.dao.StaffDao;
import com.bank.manage.dos.NewProcessDO;
import com.bank.manage.dos.StaffDO;
import com.bank.manage.service.NewProcessService;
import com.bank.manage.service.StaffService;
import com.bank.manage.vo.StaffQueryVo;
import com.bank.manage.vo.StaffVo;
import com.bank.user.dos.UserDO;
import com.bank.user.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @Author: Andy
 * @Date: 2020/4/9 9:48
 * 行员信息管理 服务
 */
@Service
@Slf4j
public class StaffServiceImpl extends ServiceImpl<StaffDao, StaffDO> implements StaffService {

    @Resource
    private StaffDao staffDao;

    @Resource
    private NewProcessService newProcessService;

    @Resource
    private UserService userService;

    /**
     * 多条件符合查询 行员列表
     *
     * @param staffQueryVo 查询条件
     * @return
     */
    @Override
    public IPage<StaffDO> getPage(StaffQueryVo staffQueryVo) {
        QueryWrapper<StaffDO> queryWrapper = new QueryWrapper<>();
        if (StrUtil.isNotEmpty(staffQueryVo.getUserId())) {
            //行员工号
            queryWrapper.eq("STAFF_USERID" , staffQueryVo.getUserId());
        }
        if (StrUtil.isNotBlank(staffQueryVo.getUserName())) {
            //行员姓名
            queryWrapper.eq("STAFF_NAME" , staffQueryVo.getUserName());
        }
        if (StrUtil.isNotBlank(staffQueryVo.getPhone())) {
            //行员电话
            queryWrapper.eq("STAFF_PHONE" , staffQueryVo.getPhone());
        }
        if (StrUtil.isNotBlank(staffQueryVo.getSex())) {
            //行员性别
            queryWrapper.eq("STAFF_SEX" , staffQueryVo.getSex());
        }
        if (StrUtil.isNotBlank(staffQueryVo.getUserId())) {
            //行员 机构号
            queryWrapper.eq("STAFF_ORGID" , staffQueryVo.getOrgId());
        }
        //page 对象
        Page<StaffDO> page = new Page<>(staffQueryVo.getPageIndex(), staffQueryVo.getPageSize());
        return staffDao.selectPage(page, queryWrapper);
    }

    /**
     * 新增行员
     *
     * @param staffVo
     * @param tokenUserInfo
     * @return
     */
    @Override
    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Boolean add(StaffVo staffVo, TokenUserInfo tokenUserInfo) {
        try {
            //检查工号是否存在
            QueryWrapper<UserDO> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("USER_ID" , staffVo.getStaffUserid());
            UserDO userDO = userService.getOne(queryWrapper);
            if (userDO == null) {
                throw new BizException("无法查询【" + staffVo.getStaffUserid() + "】的用户");
            }

            //查询是否重复添加
            QueryWrapper<StaffDO> staffDOQueryWrapper = new QueryWrapper<>();
            staffDOQueryWrapper.eq("STAFF_USERID" , staffVo.getStaffUserid());
            StaffDO staffDOExist = staffDao.selectOne(staffDOQueryWrapper);
            if (staffDOExist != null) {
                throw new BizException("已存在工号为【" + staffVo.getStaffUserid() + "】的行员信息");
            }

            StaffDO staffDO = new StaffDO();
            BeanUtils.copyProperties(staffVo, staffDO);
            //设置添加时间
            staffDO.setCreateTime(LocalDateTime.now());
            //设置添加人员
            staffDO.setCreateUserid(tokenUserInfo.getUserId());
            //设置添加人员姓名
            staffDO.setCreateUsername(tokenUserInfo.getUserName());
            staffDao.insert(staffDO);
            //添加到审核列表
            NewProcessDO newProcessDO = NewProcessDO.builder()
                    .status(NewProcessStatusFile.PROCESS_WAIT)
                    .active("1")
                    .tradingId(String.valueOf(staffDO.getStaffId()))
                    .tradingName(ConstantEnum.STAFF_ADD.getType())
                    .tradingModule(ConstantEnum.STAFF_MODULE.getType())
                    .tradingType(ConstantEnum.STAFF_TYPE_MODULE.getType())
                    .creatorId(tokenUserInfo.getUserId())
                    .creatorName(tokenUserInfo.getUserName())
                    .orgId(tokenUserInfo.getOrgId())
                    .createTime(LocalDateTime.now()).build();

            log.info("插入流程表信息：{}" , newProcessDO);
            newProcessService.createProcess(newProcessDO, tokenUserInfo);
            return true;
        } catch (Exception e) {
            if (e instanceof BizException) {
                throw new BizException(((BizException) e).getErrorMsg());
            } else {
                throw new BizException("添加行员失败");
            }
        }
    }

    /**
     * 更新行员信息
     *
     * @param staffVo
     * @param tokenUserInfo
     * @return
     */
    @Override
    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Boolean modify(StaffVo staffVo, TokenUserInfo tokenUserInfo) {
        try {
            StaffDO staffDO = new StaffDO();
            BeanUtils.copyProperties(staffVo, staffDO);
            //设置更新人编号
            staffDO.setUpdateUserid(tokenUserInfo.getUserId());
            //设置更新人姓名
            staffDO.setUpdateTime(LocalDateTime.now());
            //设置更新时间
            staffDO.setUpdateUsername(tokenUserInfo.getUserName());
            staffDao.updateById(staffDO);
            //添加到审核列表
            NewProcessDO newProcessDO = NewProcessDO.builder()
                    .status(NewProcessStatusFile.PROCESS_WAIT)
                    .active("1")
                    .tradingId(String.valueOf(staffVo.getStaffId()))
                    .tradingName(ConstantEnum.STAFF_UPDATE.getType())
                    .tradingModule(ConstantEnum.STAFF_MODULE.getType())
                    .tradingType(ConstantEnum.STAFF_TYPE_MODULE.getType())
                    .creatorId(tokenUserInfo.getUserId())
                    .creatorName(tokenUserInfo.getUserName())
                    .orgId(tokenUserInfo.getOrgId())
                    .createTime(LocalDateTime.now()).build();
            log.info("插入流程表信息：{}" , newProcessDO);
            newProcessService.createProcess(newProcessDO, tokenUserInfo);
            return true;
        } catch (Exception e) {
            throw new BizException("更新行员失败");
        }
    }

    /**
     * 删除行员信息
     *
     * @param staffId       行员ID
     * @param tokenUserInfo
     * @return
     */
    @Override
    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Boolean deleteById(Integer staffId, TokenUserInfo tokenUserInfo) {
        try {
            StaffDO staffDO = staffDao.selectById(staffId);
            //添加到审核列表
            NewProcessDO newProcessDO = NewProcessDO.builder()
                    .status(NewProcessStatusFile.PROCESS_WAIT)
                    .active("1")
                    .tradingId(String.valueOf(staffDO.getStaffId()))
                    .tradingName(ConstantEnum.STAFF_DELETE.getType())
                    .tradingModule(ConstantEnum.STAFF_MODULE.getType())
                    .tradingType(ConstantEnum.STAFF_TYPE_MODULE.getType())
                    .creatorId(tokenUserInfo.getUserId())
                    .creatorName(tokenUserInfo.getUserName())
                    .orgId(tokenUserInfo.getOrgId())
                    .createTime(LocalDateTime.now()).build();
            log.info("插入流程表信息：{}" , newProcessDO);
            newProcessService.createProcess(newProcessDO, tokenUserInfo);
            return true;
        } catch (Exception e) {
            throw new BizException("删除行员信息失败");
        }
    }

    /**
     * 真正的删除逻辑
     *
     * @param staffId 行员编号
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean deleteByIdTrue(Integer staffId) {
        try {
            staffDao.deleteById(staffId);
            return true;
        } catch (Exception e) {
            throw new BizException("删除失败 行员失败");
        }
    }
}
