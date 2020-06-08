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
package com.bank.message.dos;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 实体类
 *
 * @author zhaozhongyuan
 * @since 2020-04-16
 */
@Data
@TableName("S_DEVICE_ONLINE")
@Builder
@ApiModel(value = "DeviceOnline对象", description = "DeviceOnline对象")
public class DeviceOnline implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("MAC")
    private String mac;

    @TableField("TERMINAL_NUM")
    private String terminalNum;

    @TableField("UPDATE_TIME")
    private Date updateTime;


}
