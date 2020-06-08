package com.bank.core.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.TreeSet;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Menu implements Comparable {
    private Integer id;
    private String Name;
    /**
     * 父ID
     */
    private Integer parentId;

    /**
     * 图标
     */
    private String icon;

    private String routePath;
    private Integer sort;
    private TreeSet<Menu> child;
    //默认不选中
    private boolean checked = false;

    @Override
    public int compareTo(Object o) {
        if (o instanceof Menu) {
            Menu obj = (Menu) o;
            Integer sort = obj.getSort();
            Integer id = obj.getId();
            if (sort == null) {
                return this.id - id;
            } else {
                if (this.sort == sort) {
                    return this.id - id;
                } else {
                    return this.sort - sort;
                }
            }
        }
        return 1;
    }
}
