package com.bank.manage.dos;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;

import lombok.Data;
import lombok.Builder;

/**
 * 样式表 实体类
 *
 * @author zhaozhongyuan
 * @since 2020-04-23
 */
@Data
@Builder
@TableName("T_STYLE_AREA")
public class StyleAreaDO implements Serializable {

    private static final long serialVersionUID = 3514863371380057188L;
    /**
     * 主键
     */
  @TableId(value = "ID", type = IdType.AUTO)
  private Integer id;
    /**
     * 设备类型
     */
  private String deviceType;
    /**
     * 样式
     */
  private String style;
    /**
     * 样式名称
     */
  private String styleName;
    /**
     * 样式分类
     */
  private String styleType;
    /**
     * 创建人
     */
  private String createdUser;
    /**
     * 创建时间
     */
  private LocalDateTime createdTime;
    /**
     * 更新人
     */
  private String updateUser;
    /**
     * 更新时间
     */
  private LocalDateTime updateTime;

  /**
   * 样式路径
   */
  private String stylePath;

  /**
   * 宽度
   */
  private String layoutWidth;

  /**
   * 长度
   */
  private String layoutHeight;

  /**
   * 背景图片地址
   */
  private String layoutBackground;
}
