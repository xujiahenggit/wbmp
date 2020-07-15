package com.bank.esb.dao;

import com.bank.esb.dos.DatDeviceDO;
import com.bank.esb.vo.DatDeviceVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import java.util.List;
import org.apache.ibatis.annotations.Param;
/**
 * 设备信息表 Mapper 接口
 *
 * @author 代码自动生成
 * @since 2020-07-10
 */
public interface DatDeviceDao extends BaseMapper<DatDeviceDO> {

	/**
	 * 自定义分页
	 *
	 * @param page
	 * @param datDevice
	 * @return
	 */
	List<DatDeviceDO> listPage(IPage page,@Param("model") DatDeviceDO datDevice);

}
