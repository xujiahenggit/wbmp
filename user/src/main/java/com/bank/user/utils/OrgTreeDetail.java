package com.bank.user.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author: Andy
 * @Date: 2020/4/7 10:31
 * 组织机构层级数构建工具
 */
public class OrgTreeDetail {
    public static List<Map<String, Object>> getOrgTree(List<Map<String, Object>> allMenu, String currentUserOrgId) {
        //根节点
        List<Map<String, Object>> rootMenu = new ArrayList<Map<String, Object>>();
        for (Map<String, Object> nav : allMenu) {
            String orgId=String.valueOf(nav.get("ORG_ID"));
            //父节点是0的，为根节点。
            if(orgId.equals(currentUserOrgId)){
                rootMenu.add(nav);
            }
        }
        //为根菜单设置子菜单，getClild是递归调用的
        for (Map<String, Object> nav : rootMenu) {
            /* 获取根节点下的所有子节点 使用getChild方法*/
            String id = String.valueOf(nav.get("ORG_ID"));
            List<Map<String, Object>> childList = getChild(id, allMenu);
            nav.put("child", childList);
        }
        return rootMenu;
    }

    /**
     * 	获取子节点
     * @param id 父节点id
     * @param allMenu 所有菜单列表
     * @return 每个根节点下，所有子菜单列表
     */
    private static List<Map<String, Object>> getChild(String id,List<Map<String, Object>> allMenu){
        //子菜单
        List<Map<String, Object>> childList = new ArrayList<Map<String, Object>>();
        for (Map<String, Object> nav : allMenu) {
            // 遍历所有节点，将所有菜单的父id与传过来的根节点的id比较
            //相等说明：为该根节点的子节点。
            String parentId = String.valueOf(nav.get("parent_id"));
            if(id.equals(parentId)){
                childList.add(nav);
            }
        }
        //递归
        for (Map<String, Object> nav : childList) {
            String tempId = String.valueOf(nav.get("ORG_ID"));
            nav.put("child", getChild(tempId, allMenu));
        }
        if(childList.size() == 0){
            return new ArrayList<Map<String, Object>>();
        }
        return childList;
    }

    /**
     * 获取用户所在的 机构ID 及 下属机构ID
     * @return
     */
    public static String getUserOrgList(List<Map<String, Object>> list){
        List<String> listOrgIds= new ArrayList<>();
        if(list.size()>0){
            //获取
            for (Map<String, Object> item:list){
                listOrgIds.add(item.get("ORG_ID").toString());
            }
        }
        return null;
    }
}
