package com.bank.user.service;

import com.bank.core.entity.TokenUserInfo;
import com.bank.user.dos.UserDO;
import com.bank.user.dto.UserInfoDto;
import com.bank.user.vo.UserSaveUpdateVo;
import com.bank.user.vo.UserVO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 用户服务接口
 *
 * @author wpp.arthur
 * @Date 2020/02/29
 */
public interface UserService extends IService<UserDO> {

    /**
     * 查询用户列表
     * @param userVO 查询参数
     * @param currentOrgId 当前用户所在的机构ID
     * @return
     */
    IPage<UserInfoDto> getUserList(UserVO userVO, String currentOrgId);

    /**
     * 清空用户表
     */
    void clearnUserData();

    /**
     * 复制零时表中的数据到 用户表
     */
    void copyData();

    /**
     * 更新密码
     * @param userid 用户工号
     * @param password 密码明文
     * @return
     */
    Boolean changePassword(String userid, String password);

    /**
     * 删除用户
     * @param userId 用户ID
     * @return
     */
    Boolean deleteUser(String userId);

    /**
     * 更新用户信息
     * @param userSaveUpdateVo 用户信息
     * @param tokenUserInfo
     * @return
     */
    Boolean updateUserInfo(UserSaveUpdateVo userSaveUpdateVo, TokenUserInfo tokenUserInfo);

    /**
     * 添加用户
     * @param userSaveUpdateVo
     * @param tokenUserInfo
     * @return
     */
    Boolean saveUserInfo(UserSaveUpdateVo userSaveUpdateVo, TokenUserInfo tokenUserInfo);
}
