package com.bank.manage.service;

import com.bank.core.entity.TokenUserInfo;
import com.bank.manage.dto.GroupDTO;

import java.util.List;
import java.util.Map;

public interface GroupService {

    /**
     * 新增设备分组
     * @param groupDTO
     * @return
     */
    Integer save(GroupDTO groupDTO, TokenUserInfo tokenUserInfo);

    /**
     * 修改设备分组信息
     * @param groupDTO
     * @return
     */
    Integer updateGroup(GroupDTO groupDTO);

    /**
     * 查询设备分组树形结构
     * @return
     */
    List<Map<String, Object>> findGroup();

    /**
     * 删除设备分组
     * @param id
     * @return
     */
    Boolean deleteGroup(Integer id);

    /**
     * 添加设备到设备分组
     * @param deviceIdList
     * @param groupId
     * @return
     */
    Boolean saveDeviceGroup(List<Integer> deviceIdList, String groupId);
}
