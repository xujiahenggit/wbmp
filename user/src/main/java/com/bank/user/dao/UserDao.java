package com.bank.user.dao;

import com.bank.user.dos.UserDO;
import com.bank.user.dto.UserDTO;
import com.bank.user.dto.UserInfoDto;
import com.bank.user.vo.UserVO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author wpp.arthur
 * @Date 2020/02/29
 */
public interface UserDao extends BaseMapper<UserDO> {

    /**
     *清空用户表
     */
    void clearnUserData();

    /**
     * 复制零时表 的数据到 用户表
     */
    void copyData();

    /**
     * 获取 用户列表
     * @param page
     * @param orgId
     * @return
     */
    IPage<UserDTO> getUserListRoles(Page<UserDTO> page,@Param(value = "orgId") String orgId);

    /**
     * 查询 用户列表
     * @param listOrgIds 本机机构及下级机构列表
     * @param page 分页对象
     * @param userVO 查询参数
     * @return
     */
    IPage<UserInfoDto> selectUsersPage(Page<UserInfoDto> page,@Param(value = "listOrgIds") List<String> listOrgIds,@Param(value = "userVO") UserVO userVO);
}
