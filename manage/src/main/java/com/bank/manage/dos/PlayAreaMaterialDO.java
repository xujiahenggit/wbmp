package com.bank.manage.dos;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * 区域素材中间表
 */
@Data
@Builder
@TableName("T_PLAYAREA_MATERIAL")
public class PlayAreaMaterialDO extends Model<PlayAreaMaterialDO> {


    private static final long serialVersionUID = 5582620586615141893L;
    /**
     * 主键
     */
    @TableId(value = "ID", type = IdType.AUTO)
    private Integer id;

    /**
     * 样式编号
     */
    private Integer styleNum;

    /**
     * 区域编号
     */
    private String areaNum;

    /**
     * 区域名称
     */
    private String areaName;

    /**
     * 区域类型
     */
    private String areaType;

    /**
     * 素材主键
     */
    private Integer materialId;

    /**
     * 排序
     */
    private String sort;

    /**
     * 播放时间
     */
    private String playTime;

    /**
     * 节目主键
     */
    private Integer programId;

    /**
     * 类型
     */
    private String type;

    /**
     * 终端编号
     */
    private String terminalNum;
}
