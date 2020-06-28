package com.bank.quartz.job;

import cn.hutool.crypto.SecureUtil;
import com.bank.core.utils.*;
import com.bank.quartz.dos.TaskLogDO;
import com.bank.quartz.service.TaskLogService;
import com.bank.quartz.utils.GetTaskLogModel;
import com.bank.user.dos.*;
import com.bank.user.service.*;
import com.bank.user.utils.GetDataUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.net.ftp.FTPClient;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * CRON 表达式 每天 凌晨3点执行
 * 0 0 3 1/1 * ?
 * @Author: Andy
 * @Date: 2020/4/2 18:18
 * 用户机构数据同步 定时任务
 */
@Slf4j
public class TranslateUserDataJob implements Job {

    @Autowired
    private ConfigFileReader configFileReader;

    @Autowired
    private OrganizationService organizationService;

    @Autowired
    private UserService userService;

    @Autowired
    private OrganizationTempService organizationTempService;

    @Autowired
    private UserTempService userTempService;

    @Autowired
    private TaskLogService taskLogService;

    @Autowired
    private NfrtOrgTempService nfrtOrgTempService;

    @Autowired
    private NfrtOrgService nfrtOrgService;

    /**
     * 用户同步数
     * 1.连接FTP
     * 2.进入服务器指定目录
     * 3.下载机构文件
     * 4.下载用户文件
     * 5.关闭FTP 连接
     * 6.同步机构数据文件到 零时表
     * 7. 同步用户数据文件到 零时表
     * 8 清空 机构表
     * 9.清空 用户表
     * 10.复制机构零时数据到 机构表
     * 11.复制用户零数据到 用户表
     * 12.创建 ADMIN 用户
     * 13.删除文件
     * 14.记录日志
     * @param jobExecutionContext
     * @throws JobExecutionException
     */
    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
        //系统当前时间
        LocalDateTime localDateTime=LocalDateTime.now();
        //开始时间
        long start=System.currentTimeMillis();
        try {
            String fileDate = DateUtils.getDATE_yyyyMMdd(new Date());
            //1连接FTP
            String ftpIp = configFileReader.getFTP_IP();
            int ftpPort = Integer.parseInt(configFileReader.getFTP_PORT());
            String ftpUserName = configFileReader.getFTP_USER_NAME();
            String ftpPassword = configFileReader.getFTP_PASSWORD();
            log.info("FTP 连接：IP:" + ftpIp + "|端口：" + ftpPort + "|用户名：" + ftpUserName + "|密码：" + ftpPassword);
            FTPClient ftpClient = FTPUtil.getFTPClient(ftpIp, ftpPort, ftpUserName, ftpPassword);
            log.info("FTP:" + ftpClient);
            //2.进入FTP 服务器 指定目录
            String ftpPath = configFileReader.getFTP_FILE_PATH() + fileDate;
            log.info("进入FTP服务器指定目录：" + ftpPath);
            FTPUtil.accessDirectory(ftpClient, ftpPath);
            //3.下载机构文件
            String localFilePath = configFileReader.getLOCAL_PATH();
            log.info("本地服务器大数据文件存放目录：" + localFilePath);
            String orgFileName = configFileReader.getORG_FILENAME().replace("?", fileDate);
            log.info("机构大数据文件名称" + orgFileName);
            FTPUtil.downloadFile(ftpClient, localFilePath, orgFileName);
            //4.下载用户文件
            String userFilenName = configFileReader.getUSER_FILENAME().replace("?", fileDate);
            log.info("用户大数据文件名称：" + userFilenName);
            FTPUtil.downloadFile(ftpClient, localFilePath, userFilenName);

            //============新增 同步核心机构文件========================
            String ftpNfrtPath=configFileReader.getFTP_FILE_HX_PATH();
            log.info("进入FTP服务器 核心文件存放地址："+ftpNfrtPath);
            FTPUtil.accessDirectory(ftpClient,"../.."+ftpNfrtPath);
            //下载核心机构文件
            String hxOrgFileName=configFileReader.getHX_ORGFILE_NAME().replace("?",fileDate);
            FTPUtil.downloadFile(ftpClient, localFilePath, hxOrgFileName);
            //========================================================

            //5.关闭FTP 连接
            FTPUtil.logoutAndDisconnect(ftpClient);
            //6.同步机构数据文件到 零时表
            String localOrgFile = localFilePath + orgFileName;
            log.info("本地机构大数据文件：" + localOrgFile);
            File fileOrg=new File(localOrgFile);
            List<List<Object>> listdataorg = BigDataFileReader.parseText(fileOrg, new String(new byte[]{0x06}));
            List<OrganizationTempDO> listOrg = GetDataUtils.getOrgData(listdataorg);
            // 清空组织机构零时表
            organizationTempService.clearnTempOrgData();
            //保存到 零时表
            organizationTempService.saveBatch(listOrg);
            log.info("机构文件同步到零时表======》完成");
            //7. 同步用户数据文件到 零时表
            String localUserFile = localFilePath + userFilenName;
            log.info("本地用户大数据文件：" + localUserFile);
            File fileUser=new File(localUserFile);
            List<List<Object>> listdatauser = BigDataFileReader.parseText(fileUser, new String(new byte[]{0x06}));
            List<UserTempDO> listUser = GetDataUtils.getUserData(listdatauser);

            //==========================同步核心机构文件==============
            String localHxOrgFile = localFilePath + hxOrgFileName;
            log.info("本地核心机构大数据文件："+localHxOrgFile);
            File fileHxOrg=new File(localHxOrgFile);
            List<List<Object>> listHxOrg=BigDataFileReader.parseNfrtOrgText(fileHxOrg,"|");
            List<NfrtOrgTempDO> listHxOrgDo=GetDataUtils.getNfrtOrgList(listHxOrg);
            // 清空 零时表
            nfrtOrgTempService.ClearnNfrtOrgTemp();
            // 保存 零时表
            nfrtOrgTempService.saveBatch(listHxOrgDo);
            //=======================================================
            //清空 用户零时表
            userTempService.clearnTmepUserData();
            //保存到零时表
            userTempService.saveBatch(listUser);
            log.info("用户文件同步到零时表======》完成");
            //8 清空 机构表
            organizationService.clearnOrgData();
            //9.清空 用户表
            userService.clearnUserData();
            //10.复制机构零时数据到 机构表
            organizationService.copyData();
            //11.复制用户零数据到 用户表
            userService.copyData();
            //=============================核心机构============================
            //清空核心机构表
            nfrtOrgService.clearnNfrt();
            //复制 零时表的数据 到 核心机构文件
            nfrtOrgService.copyTempData();
            //================================================================
            //12.创建ADMIN 用户
            userService.save(this.getAdminUser());
            //13.删除本地 大数据文件
            fileOrg.delete();
            fileUser.delete();
            fileHxOrg.delete();
            //14.保存日志
            TaskLogDO taskLogDO = GetTaskLogModel.getModel(jobExecutionContext, "1" , localDateTime, System.currentTimeMillis() - start, null);
            taskLogService.save(taskLogDO);
        } catch (Exception e) {
            //这里记录错误日志
            TaskLogDO taskLogDO = GetTaskLogModel.getModel(jobExecutionContext, "2" , localDateTime, System.currentTimeMillis() - start, e.fillInStackTrace());
            taskLogService.save(taskLogDO);
        }
    }


    /**
     * 构造系统管理员 模型
     * @return
     */
    private UserDO getAdminUser(){
        UserDO userDO=new UserDO();
        //用户号
        userDO.setUserId("ADMIN");
        //用户名
        userDO.setUserName("ADMIN");
        //密码
        userDO.setPassword(Md5Utils.getEnCode("88888888"));
        //所属机构号
        userDO.setOrgId("100");
        //所属机构名称
        userDO.setOrgName("长沙银行");
        //岗位名称
        userDO.setPositionName("系统管理员");
        //手机号码
        userDO.setUserPhone("111111111111");
        //性别
        userDO.setUserGender("男");
        //身份证号码
        userDO.setUserIdentiyno("111111111111111111");
        //设置在岗状态
        userDO.setUserWorkStatus("正式在岗");
        return userDO;
    }
}
