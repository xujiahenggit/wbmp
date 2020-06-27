package com.bank.manage.vo;

import com.bank.manage.dos.WbmpOrgBalanceDO;
import lombok.Data;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;

/**
 * 网点机构存款表视图实体类
 *
 * @author zhaozhongyuan
 * @since 2020-06-22
 */
@Data
@ApiModel(value = "WbmpOrgBalanceVO对象", description = "网点机构存款表")
public class WbmpOrgBalanceVO implements Serializable {
	private static final long serialVersionUID = 1L;

}
