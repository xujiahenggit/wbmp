package com.bank.manage.vo;

import com.bank.manage.dos.ActivitieSalonDO;
import lombok.Data;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;

/**
 * 活动沙龙 视图实体类
 *
 * @author zhaozhongyuan
 * @since 2020-06-05
 */
@Data
@ApiModel(value = "ActivitieSalonVO对象", description = "活动沙龙 ")
public class ActivitieSalonVO implements Serializable {
	private static final long serialVersionUID = 1L;

}
