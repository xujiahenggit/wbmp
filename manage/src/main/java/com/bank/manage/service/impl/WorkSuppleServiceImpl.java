package com.bank.manage.service.impl;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.bank.core.entity.BizException;
import com.bank.core.entity.FileDo;
import com.bank.core.entity.TokenUserInfo;
import com.bank.core.sysConst.NewProcessStatusFile;
import com.bank.core.sysConst.RolePermissionCode;
import com.bank.core.sysConst.SysStatus;
import com.bank.core.utils.ConfigFileReader;
import com.bank.core.utils.DateUtils;
import com.bank.core.utils.FileUploadUtils;
import com.bank.core.utils.NetUtil;
import com.bank.manage.dao.WorkSuppleDao;
import com.bank.manage.dos.UsherDO;
import com.bank.manage.dos.WorkSuppleDO;
import com.bank.manage.dto.FacilitatorDto;
import com.bank.manage.dto.WorkSuppleDto;
import com.bank.manage.service.UsherService;
import com.bank.manage.service.WorkSuppleService;
import com.bank.manage.vo.FacilitatorVo;
import com.bank.manage.vo.WorkSupplePassRejectVo;
import com.bank.manage.vo.WorkSuppleQueryVo;
import com.bank.role.dos.RoleDO;
import com.bank.role.service.RoleService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import cn.hutool.core.util.IdUtil;

/**
 * @Author: Andy
 * @Date: 2020/5/28 16:29
 */
@Service
public class WorkSuppleServiceImpl extends ServiceImpl<WorkSuppleDao, WorkSuppleDO> implements WorkSuppleService {

    @Resource
    NetUtil netUtil;

    @Resource
    private WorkSuppleDao workSuppleDao;

    @Resource
    private UsherService usherService;

    @Resource
    private ConfigFileReader configFileReader;

    @Resource
    private RoleService roleService;

    /**
     * 待办列表
     * @param workSuppleQueryVo 查询参数
     * @return
     */
    @Override
    public IPage<WorkSuppleDto> getWaitList(WorkSuppleQueryVo workSuppleQueryVo) {
        return this.getList(workSuppleQueryVo, SysStatus.QUERY_TYPE_WAIT);
    }

    /**
     * 已办列表
     * @param workSuppleQueryVo 查询参数
     * @return
     */
    @Override
    public IPage<WorkSuppleDto> getPassList(WorkSuppleQueryVo workSuppleQueryVo) {
        return this.getList(workSuppleQueryVo, SysStatus.QUERY_TYPE_AREADY);
    }

    /**
     * 审核通过
     * @param workSupplePassRejectVo 审核参数
     * @param tokenUserInfo 当前用户信息
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean passProcess(WorkSupplePassRejectVo workSupplePassRejectVo, TokenUserInfo tokenUserInfo) {
        try {
            WorkSuppleDO workSuppleDO = getProcessModel(workSupplePassRejectVo, tokenUserInfo, NewProcessStatusFile.CHECK_TYPE_PASS);
            this.updateById(workSuppleDO);
            return true;
        }
        catch (Exception e) {
            throw new BizException("审核失败");
        }
    }

    /**
     * 驳回
     * @param workSupplePassRejectVo 审核参数
     * @param tokenUserInfo 当前用户信息
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean rejectProcess(WorkSupplePassRejectVo workSupplePassRejectVo, TokenUserInfo tokenUserInfo) {
        try {
            WorkSuppleDO workSuppleDO = getProcessModel(workSupplePassRejectVo, tokenUserInfo, NewProcessStatusFile.CHECK_TYPE_REJECT);
            this.updateById(workSuppleDO);
            return true;
        }
        catch (Exception e) {
            throw new BizException("审核失败");
        }
    }

    /**
     * 用户编号 获取详细信息
     * @param workSuppleId 编号
     * @return
     */
    @Override
    public WorkSuppleDto getDetailInfo(Integer workSuppleId) {
        WorkSuppleDto workSuppleDto = workSuppleDao.getDetailInfo(workSuppleId);
        if (workSuppleDto != null) {
            workSuppleDto.setWorkSuppleImg(netUtil.getUrlSuffix("") + workSuppleDto.getWorkSuppleImg());
        }
        return workSuppleDto;
    }

    /**
     * 上传文件
     * @param file 文件
     * @return
     */
    @Override
    public FileDo uploadFile(MultipartFile file) {
        FileDo fileDo = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            //第一层目录 按 日期创建
            String fist_tab = sdf.format(new Date());
            //上传路径
            String uploadPath = configFileReader.getWORK_FILE_PATH() + "/" + fist_tab;
            //访问路径
            String accessPath = netUtil.getUrlSuffix("") + configFileReader.getWORK_FILE_PATH() + "/" + fist_tab;
            //原文件名称
            String filename = file.getOriginalFilename();
            //用UUID
            String c_fileName = IdUtil.randomUUID() + filename.substring(filename.lastIndexOf("."));;
            fileDo = FileUploadUtils.FileUpload(file, uploadPath, accessPath, c_fileName);
            return fileDo;
        }
        catch (Exception e) {
            throw new BizException("图片上传失败！");
        }
    }

    /**
     * 新增 加班申请
     * @param workSuppleDto 参数
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean saveWorkSupple(WorkSuppleDto workSuppleDto) {
        try {
            WorkSuppleDO workSuppleDO = new WorkSuppleDO();
            //设置行员引导编号
            workSuppleDO.setUsherId(workSuppleDto.getUsherId());
            //设置加班日期
            workSuppleDO.setWorkSuppleDate(workSuppleDO.getWorkSuppleDate());
            //设置加班理由
            workSuppleDO.setWorkSuppleResion(workSuppleDto.getWorkSuppleResion());
            //设置状态为 待审核
            workSuppleDO.setWorkSuppleState(NewProcessStatusFile.PROCESS_WAIT);
            //设置加班开始时间
            workSuppleDO.setWorkSuppleStarttime(workSuppleDto.getWorkSuppleStarttime());
            //设置加班结束时间
            workSuppleDO.setWorkSuppleEndtime(workSuppleDto.getWorkSuppleEndtime());
            //设置加班时长
            workSuppleDO.setWorkSuppleLength(DateUtils.getTime(workSuppleDto.getWorkSuppleEndtime(), workSuppleDto.getWorkSuppleEndtime()));
            //设置加班类型
            workSuppleDO.setWorkSuppleType(workSuppleDto.getWorkSuppleType());
            //设置证明文件地址
            workSuppleDO.setWorkSuppleImg(workSuppleDto.getWorkSuppleImg());
            //设置申请时间
            workSuppleDO.setWorkSuppleTime(LocalDateTime.now());
            this.save(workSuppleDO);
            return true;
        }
        catch (Exception e) {
            throw new BizException("新增加班申请失败！");
        }
    }

    /**
     * 统计加班时长
     * @param usherId 引导员编号
     * @param satisfactAttendYear 日期
     * @param type 0：工作日加班；1：节假日加班
     * @return
     */
    @Override
    public float getRestWorkLenghth(Integer usherId, LocalDate satisfactAttendYear, Integer type) {
        return workSuppleDao.getRestWorkLenghth(usherId, satisfactAttendYear, type);
    }

    /**
     * 引导员待办列表
     * @param facilitatorVo 查询参数
     * @param tokenUserInfo 当前登录用户
     * @return
     */
    @Override
    public IPage<FacilitatorDto> getAllWaitList(FacilitatorVo facilitatorVo, TokenUserInfo tokenUserInfo) {
        return getAllList(facilitatorVo, NewProcessStatusFile.PROCESS_WAIT, tokenUserInfo.getOrgId(), tokenUserInfo);
    }

    /**
     * 引导员 已办列表
     * @param facilitatorVo 查询参数
     * @param tokenUserInfo 当前登录用户
     * @return
     */
    @Override
    public IPage<FacilitatorDto> getAllAredyList(FacilitatorVo facilitatorVo, TokenUserInfo tokenUserInfo) {
        return getAllList(facilitatorVo, NewProcessStatusFile.PROCESS_PASS, tokenUserInfo.getOrgId(), tokenUserInfo);
    }

    /**
     * 查询所有的 引导员待办列表
     * @param facilitatorVo 查询参数
     * @param queryType 查询类型
     * @param tokenUserInfo 当前登录用户信息
     * @return
     */
    public IPage<FacilitatorDto> getAllList(FacilitatorVo facilitatorVo, String queryType, String orgId, TokenUserInfo tokenUserInfo) {
        Page<FacilitatorDto> page = new Page<>(facilitatorVo.getPageIndex(), facilitatorVo.getPageSize());
        boolean flag = false;
        List<RoleDO> listRole = roleService.getHeadOfficeUserRole(tokenUserInfo.getUserId(), RolePermissionCode.ROLE_USHER);
        if (listRole.size() > 0) {
            flag = true;
        }
        return workSuppleDao.getAllList(page, queryType, orgId, flag);
    }

    /**
     * 构造 审批 模型
     * @param workSupplePassRejectVo 审批参数
     * @param tokenUserInfo 当前用户信息
     * @param checkType 审批类型 通过 驳回
     * @return
     */
    private WorkSuppleDO getProcessModel(WorkSupplePassRejectVo workSupplePassRejectVo, TokenUserInfo tokenUserInfo, String checkType) {
        WorkSuppleDO workSuppleDO = new WorkSuppleDO();
        //设置编号
        workSuppleDO.setWorkSuppleId(workSupplePassRejectVo.getWorkSuppleId());
        if (NewProcessStatusFile.CHECK_TYPE_PASS.equals(checkType)) {
            //设置状态为审核通过
            workSuppleDO.setWorkSuppleState(NewProcessStatusFile.PROCESS_PASS);
        }
        else if (NewProcessStatusFile.CHECK_TYPE_REJECT.equals(checkType)) {
            //设置状态为 驳回
            workSuppleDO.setWorkSuppleState(NewProcessStatusFile.PROCESS_REJECT);
            //设置驳回理由
            workSuppleDO.setWorkSuppleRejectResion(workSupplePassRejectVo.getWorkSuppleRejectResion());
        }
        //审批人工号
        workSuppleDO.setWorkSuppleProcessUserId(tokenUserInfo.getUserId());
        //设置 审批人姓名
        workSuppleDO.setWorkSuppleProcessUserName(tokenUserInfo.getUserName());
        //设置审批时间
        workSuppleDO.setWorkSuppleProcessTime(LocalDateTime.now());
        return workSuppleDO;
    }

    /**
     * 按 审核类型查询 列表
     * @param workSuppleQueryVo 查询参数
     * @param queryType 审核类别 待办 已办
     * @return
     */
    private IPage<WorkSuppleDto> getList(WorkSuppleQueryVo workSuppleQueryVo, String queryType) {
        //用手机号获取 行员引导信息
        UsherDO usherDO = usherService.selectUsherByPhone(workSuppleQueryVo.getUserPhone());
        if (usherDO == null) {
            throw new BizException("未查询到引导员信息");
        }
        Page<WorkSuppleDO> page = new Page<>(workSuppleQueryVo.getPageIndex(), workSuppleQueryVo.getPageSize());
        return workSuppleDao.getList(page, usherDO.getOrgId(), queryType);
    }
}
