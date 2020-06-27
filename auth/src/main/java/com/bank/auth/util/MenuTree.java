package com.bank.auth.util;

import com.bank.auth.dto.PermissionDTO;
import com.bank.core.entity.Menu;

import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class MenuTree {

    public static TreeSet<Menu> initMenuTree(Set<PermissionDTO> permissionDTOS, Map<Integer, Object> map, int level) {
        TreeSet<Menu> menuTreeSet = new TreeSet<>();
        for (PermissionDTO permissionDTO : permissionDTOS) {
            Integer parentId = permissionDTO.getParentId();
            if (parentId == 1) {
                boolean checked = false;
                Integer permissionId = permissionDTO.getPermissionId();
                if (map!=null&&map.containsKey(permissionId)) {
                    checked = true;
                }
                Menu menu = new Menu(permissionId,
                        permissionDTO.getPermissionName(), parentId,
                        permissionDTO.getIcon(), permissionDTO.getRouterPath(),
                        permissionDTO.getSort(), getChildren(permissionId, permissionDTOS,map, level), checked);
                menuTreeSet.add(menu);
            }
        }

        return menuTreeSet;
    }

    private static TreeSet<Menu> getChildren(Integer permissionId, Set<PermissionDTO> permissionDTOS, Map<Integer, Object> map, int level) {
        TreeSet<Menu> menuTree = new TreeSet<>();
        for (PermissionDTO permissionDTO : permissionDTOS) {
            int permissionType = Integer.parseInt(permissionDTO.getPermissionType());
            Integer parentId = permissionDTO.getParentId();
            if (parentId.equals(permissionId) && permissionType <= level) {
                Integer id = permissionDTO.getPermissionId();
                boolean checked = false;
                if (map!=null&&map.containsKey(id)) {
                    checked = true;
                }
                menuTree.add(new Menu(id,
                        permissionDTO.getPermissionName(), parentId,
                        permissionDTO.getIcon(), permissionDTO.getRouterPath(),
                        permissionDTO.getSort(), getChildren(id, permissionDTOS,map, level), checked));

            }
        }
        return menuTree;
    }
}
