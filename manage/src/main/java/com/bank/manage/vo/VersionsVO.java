package com.bank.manage.vo;

import com.bank.manage.dos.VersionsDO;
import lombok.Data;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;

/**
 * 应用版本维护视图实体类
 *
 * @author zhaozhongyuan
 * @since 2020-06-01
 */
@Data
@ApiModel(value = "VersionsVO对象", description = "应用版本维护")
public class VersionsVO implements Serializable {
	private static final long serialVersionUID = 1L;

}
