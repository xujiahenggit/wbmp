package com.bank.manage.service.impl;

import com.bank.core.entity.BizException;
import com.bank.core.entity.TokenUserInfo;
import com.bank.manage.dao.DeviceGroupDao;
import com.bank.manage.dao.GroupDao;
import com.bank.manage.dos.DeviceGroupDO;
import com.bank.manage.dos.GroupDO;
import com.bank.manage.dto.GroupDTO;
import com.bank.manage.service.GroupService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class GroupServiceImpl implements GroupService {

    @Autowired
    private GroupDao groupDao;

    @Autowired
    private DeviceGroupDao deviceGroupDao;

    @Override
    public Integer save(GroupDTO groupDTO, TokenUserInfo tokenUserInfo) {
        GroupDO groupDO = GroupDO.builder()
                .groupName(groupDTO.getGroupName())
                .groupType(groupDTO.getGroupType())
                .createdUser(tokenUserInfo.getUserId())
                .createdTime(LocalDateTime.now())
                .parentId(groupDTO.getParentId()).build();
        return groupDao.insert(groupDO);
    }

    @Override
    public Integer updateGroup(GroupDTO groupDTO) {
        GroupDO groupDO = GroupDO.builder()
                .groupName(groupDTO.getGroupName())
                .groupId(groupDTO.getGroupId()).build();
        return groupDao.updateById(groupDO);
    }

    @Override
    public List<Map<String, Object>> findGroup() {
        List<Map<String, Object>> maps = null;
        List<Map<String, Object>> list = this.groupDao.selectMaps(new QueryWrapper<GroupDO>());
        if(list != null && list.size()>0){
            maps = CatalogTree(list);
        }
        log.info("设备分组树形结构：{}",maps);
        return maps;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean deleteGroup(Integer id) {
        Boolean flag = null;
        try {
            if( id == 1){
                throw new BizException("根目录不允许删除");
            }
            QueryWrapper<DeviceGroupDO> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("GROUP_ID",id);
            int count = deviceGroupDao.selectCount(queryWrapper);
            //分组下存在设备则不允许删除
            if( count > 0 ){
                throw new BizException("该分组下存在设备，不允许删除");
            }
            groupDao.deleteById(id);
//            deviceGroupDao.delete(queryWrapper);
            flag = true;
        }catch (BizException e) {
            throw e;
        } catch (Exception e) {
            flag = false;
            log.error("设备分组删除失败："+e.getMessage());
        }
        return flag;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean saveDeviceGroup(List<Integer> deviceIdList, String groupId) {
        Boolean flag = null;
        try {
            //先删除所在分组，在分组
            List<DeviceGroupDO> deviceGroupDOList = deviceGroupDao.selectBatchIds(deviceIdList);
            if(deviceGroupDOList != null && deviceGroupDOList.size()>0){
                for (DeviceGroupDO deviceId:deviceGroupDOList) {
                    deviceGroupDao.deleteById(deviceId.getId());
                }
            }
            for (Integer device:deviceIdList) {
                DeviceGroupDO deviceGroupDO = DeviceGroupDO.builder()
                        .deviceId(device)
                        .groupId(Integer.parseInt(groupId)).build();
                deviceGroupDao.insert(deviceGroupDO);
            }
            flag = true;
        } catch (NumberFormatException e) {
            flag = false;
           log.error("设备添加到设备分组失败："+e.getMessage());
           throw new BizException("设备添加到分组失败！");
        }
        return flag;
    }

    private List<Map<String, Object>> CatalogTree(List<Map<String, Object>> list){
        //根节点
        List<Map<String, Object>> rootMenu = new ArrayList<Map<String, Object>>();
        for (Map<String, Object> nav : list) {
            String parentId = String.valueOf(nav.get("parent_id"));
            if("null".equals(parentId)){
                rootMenu.add(nav);
            }
        }
        //为根菜单设置子菜单，getClild是递归调用的
        for (Map<String, Object> nav : rootMenu) {
            /* 获取根节点下的所有子节点 使用getChild方法*/
            String id = String.valueOf(nav.get("GROUP_ID"));
            List<Map<String, Object>> childList = getChild(id, list);
            if(childList != null && childList.size()>0){
                nav.put("child", childList);
            }
        }
        return rootMenu;
    }

    private List<Map<String, Object>> getChild(String id, List<Map<String, Object>> list) {
        //子菜单
        List<Map<String, Object>> childList = new ArrayList<Map<String, Object>>();
        for (Map<String, Object> nav : list) {
            // 遍历所有节点，将所有菜单的父id与传过来的根节点的id比较
            //相等说明：为该根节点的子节点。
            String parentId = String.valueOf(nav.get("parent_id"));
            if(id.equals(parentId)){
                childList.add(nav);
            }
        }
        //递归
        for (Map<String, Object> nav : childList) {
            String tempId = String.valueOf(nav.get("GROUP_ID"));
            List<Map<String, Object>> a = getChild(tempId, list);
            if(a != null && a.size()>0){
                nav.put("child", a);
            }
        }
        if(childList.size() == 0){
            return new ArrayList<Map<String, Object>>();
        }
        return childList;
    }
}
