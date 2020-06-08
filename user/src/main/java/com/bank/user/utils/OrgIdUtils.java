package com.bank.user.utils;

import com.bank.user.dos.OrganizationDO;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author: Andy
 * @Date: 2020/4/7 16:25
 */
public class OrgIdUtils {
    //子节点
    static List<OrganizationDO> childMenu = new ArrayList<OrganizationDO>();

    //获取最终的List<Integer> id 转为字符串
    public static List<String> returnFidList(String id, List<OrganizationDO> list) {
        childMenu = new ArrayList<OrganizationDO>();
        List<OrganizationDO> childList = treeMenuList(list, id);
        List<String> listIds=new ArrayList<>();
        //加入机构本身
        listIds.add(id);
        for(OrganizationDO m : childList){
            listIds.add(m.getOrgId());
        }
        return listIds;
    }

    //获取某个父节点下面的所有子节点
    public static List<OrganizationDO> treeMenuList(List<OrganizationDO> menuList, String pid){
        for(OrganizationDO mu: menuList){
            if(mu.getParentId().equals(pid)){
                treeMenuList(menuList, mu.getOrgId());
                childMenu.add(mu);
            }
        }
        return childMenu;
    }
}
