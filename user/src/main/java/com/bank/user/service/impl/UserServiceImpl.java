package com.bank.user.service.impl;

import cn.hutool.core.util.StrUtil;
import com.bank.core.entity.BizException;
import com.bank.core.entity.TokenUserInfo;
import com.bank.core.utils.Md5Utils;
import com.bank.user.dao.UserDao;
import com.bank.user.dos.OrganizationDO;
import com.bank.user.dos.UserDO;
import com.bank.user.dto.UserInfoDto;
import com.bank.user.service.OrganizationService;
import com.bank.user.service.UserService;
import com.bank.user.utils.OrgIdUtils;
import com.bank.user.vo.UserSaveUpdateVo;
import com.bank.user.vo.UserVO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 用户服务接口实现类
 *
 * @author wpp.arthur
 * @Date 2020/02/29
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserDao,UserDO> implements UserService {

    @Resource
    private UserDao userDao;

    @Autowired
    private OrganizationService organizationService;

    /**
     * 总行人员显示 所有用户列表
     * 当 查询机构号不为空时 查询本级及下属机构用户
     * @param userVO 查询参数
     * @param currentOrgId 当前用户所在的机构ID
     * @return
     */
    @Override
    public IPage<UserInfoDto> getUserList(UserVO userVO, String currentOrgId) {
        //获取用户所在的 机构ID和下级机构ID
        List<OrganizationDO> organizationDOList=organizationService.list();
        List<String> listOrgIds=new ArrayList<>();
        //如果是总行 则 显示所有的机构
        if(organizationService.isHeadOffice(currentOrgId)){
            if(StrUtil.isNotBlank(userVO.getOrgId())){
                listOrgIds=OrgIdUtils.returnFidList(userVO.getOrgId(),organizationDOList);
            }else{
                listOrgIds=OrgIdUtils.returnFidList("0",organizationDOList);
            }
        }else {
            listOrgIds=OrgIdUtils.returnFidList(currentOrgId,organizationDOList);
        }
        //构建 page 对象
        Page<UserInfoDto> page=new Page<>(userVO.getPageIndex(),userVO.getPageSize());
        //查询用户列表
        IPage<UserInfoDto> userDOIPage=userDao.selectUsersPage(page,listOrgIds,userVO);
        return userDOIPage;
    }

    /**
     * 清空用户表
     */
    @Override
    public void clearnUserData() {
        userDao.clearnUserData();
    }

    /**
     * 复制零时表中的数据到 用户表
     */
    @Override
    public void copyData() {
        userDao.copyData();
    }

    /**
     * 更新用户密码
     * @param userid 用户工号
     * @param password 密码明文
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean changePassword(String userid, String password) {
        try{
            UserDO userDO=new UserDO();
            //用户工号
            userDO.setUserId(userid);
            //密码
            userDO.setPassword(Md5Utils.getEnCode(password));
            userDao.updateById(userDO);
            return true;
        }catch (Exception e){
            throw new BizException("更新密码失败");
        }
    }


    /**
     * 删除用户
     * @param userId 用户ID
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean deleteUser(String userId) {
       try{
           userDao.deleteById(userId);
           return true;
       }catch (Exception e){
           throw new BizException("删除用户失败");
       }
    }

    /**
     * 更新用户信息
     * @param userSaveUpdateVo 用户信息
     * @param tokenUserInfo
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean updateUserInfo(UserSaveUpdateVo userSaveUpdateVo, TokenUserInfo tokenUserInfo) {
        try{
            UserDO userDO=new UserDO();
            BeanUtils.copyProperties(userSaveUpdateVo,userDO);
            //设置更新 用户ID 当前用户ID
            userDO.setUserUpdateUserid(tokenUserInfo.getUserId());
            //设置更新 用户姓名
            userDO.setUserUpdateUsername(tokenUserInfo.getUserName());
            //设置更新时间
            userDO.setUserUpdateTime(LocalDateTime.now());
            userDao.updateById(userDO);
            return true;
        }catch (Exception e){
            throw new BizException("更新用户信息失败");
        }
    }


    /**
     * 添加用户
     * 默认密码 888888888
     * @param userSaveUpdateVo 用户信息
     * @param tokenUserInfo
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean saveUserInfo(UserSaveUpdateVo userSaveUpdateVo, TokenUserInfo tokenUserInfo) {
       try{
           UserDO userDO=new UserDO();
           BeanUtils.copyProperties(userSaveUpdateVo,userDO);
           //设置用户密码
           userDO.setPassword(Md5Utils.getEnCode("88888888"));
           //设置 创建时间
           userDO.setUserCreateTime(LocalDateTime.now());
           //设置 创建人ID
           userDO.setUserCreateUserid(tokenUserInfo.getUserId());
           //设置更新人 名称
           userDO.setUserCreateUsername(tokenUserInfo.getUserName());
           userDao.insert(userDO);
           return true;
       }catch (Exception e){
           throw new BizException("添加用户失败");
       }
    }
}
