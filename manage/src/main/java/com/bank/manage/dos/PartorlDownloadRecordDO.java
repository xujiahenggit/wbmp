package com.bank.manage.dos;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @Author: Andy
 * @Date: 2020/5/14 9:30
 */
@Data
@ApiModel("巡查记录下载历史")
@TableName("T_PARTORL_DOWNLOAD_RECORD")
public class PartorlDownloadRecordDO extends Model<PartorlDownloadRecordDO> {
    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    @TableId(value = "PARTORL_DOWNLOAD_REDORD_ID", type = IdType.AUTO)
    private Integer partorlDownloadRedordId;

    /**
     * 巡查记录主键
     */
    private Integer partorlRecordId;

    /**
     * 下载次数
     */
    private Integer partorlDownloadNum;

    /**
     * excel路径
     */
    private String partorlDownloadExcelpath;

    /**
     * 证明文件打包记录
     */
    private String partorlDownloadProviepath;
}
