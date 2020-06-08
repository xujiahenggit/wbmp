package com.bank.manage.dos;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
@TableName("T_MATERIAL")
public class MaterialDO extends Model<MaterialDO> {

    private static final long serialVersionUID = 2264085484013756006L;
    /**
     * 素材主键
     */
    @TableId(value = "MATERIAL_ID", type = IdType.AUTO)
    private Integer materialId;

    /**
     * 素材名称
     */
    private String materialName;

    /**
     * 素材分类 00 图片 01 视频 02PDF 03 文字
     */
    private String materialType;

    /**
     * 素材大小
     */
    private String materialSize;

    /**
     * 素材版式 00横版01竖版
     */
    private String materialFormat;

    /**
     * 素材说明
     */
    private String materialSpec;

    /**
     * 素材路径
     */
    private String materialPath;

    /**
     * 组织机构ID
     */
    private String orgId;

    /**
     * 标签
     */
    private String label;

    /**
     * 创建时间
     */
    private LocalDateTime createdTime;

    /**
     * 创建人
     */
    private String createdUser;

    /**
     * 修改人
     */
    private String updateUser;

    /**
     * 修改时间
     */
    private LocalDateTime updateTime;

    /**
     * 是否强制播放 00是 01否
     */
    private String forcePlay;

    /**
     * 失效时间
     */
    private String expirTime;

    /**
     * 设备类型
     */
    private String deviceType;

    /**
     * 文本
     */
    private String text;

    /**
     * 机构名称
     */
    private String orgName;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
