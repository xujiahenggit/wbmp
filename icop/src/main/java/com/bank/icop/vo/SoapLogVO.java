package com.bank.icop.vo;

import com.bank.icop.dos.SoapLogDO;
import lombok.Data;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;

/**
 * SOAP调用第三方接口日志视图实体类
 *
 * @author zhaozhongyuan
 * @since 2020-06-09
 */
@Data
@ApiModel(value = "SoapLogVO对象", description = "SOAP调用第三方接口日志")
public class SoapLogVO implements Serializable {
	private static final long serialVersionUID = 1L;

}
