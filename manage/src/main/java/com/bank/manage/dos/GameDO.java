/**
 * Copyright (c) 2018-2028, Chill Zhuang 庄骞 (smallchill@163.com).
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.bank.manage.dos;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 实体类
 *
 * @author zhaozhongyuan
 * @since 2020-05-06
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("T_GAME")
@ApiModel(value = "Game对象", description = "Game对象")
public class GameDO implements Serializable {

    private static final long serialVersionUID = 1L;


    /**
     * UUID
     */
  @ApiModelProperty(value = "UUID")
  @TableId(value = "ID",type = IdType.UUID)
  private String id;
    /**
     * 手机号
     */
  @ApiModelProperty(value = "手机号")
  @TableField("PHONE")
  private String phone;
    /**
     * 昵称
     */
  @ApiModelProperty(value = "昵称")
  @TableField("NAME")
  private String name;
    /**
     * 1:星球大战2:点点乐
     */
  @ApiModelProperty(value = "1:星球大战2:点点乐")
  @TableField("TYPE")
  private String type;

    /**
     * 排名
     */
  @ApiModelProperty(value = "排名")
  private Integer ranking;
    /**
     * 分数
     */
  @ApiModelProperty(value = "分数")
  @TableField("SCORE")
  private String score;
    /**
     * 用户号
     */
  @ApiModelProperty(value = "用户号")
  @TableField("USERNUM")
  private String usernum;
    /**
     * 点点乐用时
     */
  @ApiModelProperty(value = "点点乐用时")
  @TableField("TIME")
  private String time;



}
