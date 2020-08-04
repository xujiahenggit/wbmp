package com.bank.manage.dos;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 菜单表
 * </p>
 * @author
 * @since 2020-7-8
 */
@Data
@Builder
@TableName("sys_menu")
public class SysMenuDO implements Serializable {

    /**
     * 主键ID
     */
    @TableId(value = "MENU_ID", type = IdType.INPUT)
    private String menuId;

    /**
     * 菜单名称
     */
    private String menuName;

    /**
     *
     */
    private String menuDesc;

    /**
     *
     */
    private String url;

    /**
     *
     */
    private String menuType;

    /**
     *
     */
    private String isMenu;

    /**
     *
     */
    private String parentId;

    /**
     *
     */
    private String menuControl;

    /**
     *
     */
    private String available;

    /**
     *
     */
    private String menuLevel;

    /**
     *
     */
    private String itemControl;

    /**
     *
     */
    private String isOften;
}
