package com.bank.esb.dao;

import com.bank.esb.dos.WorkOrderAttachmentDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Param;

import java.util.List;
/**
 * 处理附件表 Mapper 接口
 *
 * @author 代码自动生成
 * @since 2020-07-13
 */
public interface WorkOrderAttachmentDao extends BaseMapper<WorkOrderAttachmentDO> {

	/**
	 * 自定义分页
	 *
	 * @param page
	 * @param workOrderAttachment
	 * @return
	 */
	List<WorkOrderAttachmentDO> listPage(IPage page,@Param("model") WorkOrderAttachmentDO workOrderAttachment);

}
