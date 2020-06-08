package com.bank.manage.dos;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
@TableName("T_CATALOG_MATERIAL")
public class CatalogMaterialDO implements Serializable {

    private static final long serialVersionUID = -178666341826281201L;
    /**
     * 主键
     */
    @TableId(value = "ID", type = IdType.AUTO)
    private Integer id;

    /**
     * 素材主键
     */
    private Integer materialId;

    /**
     * 目录主键
     */
    private Integer catalogId;

}
