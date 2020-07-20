package com.bank.core.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.Data;

/**
 * @Author: Andy
 * @Date: 2020/4/8 14:57
 * 读取自定义配置文件
 */
//@PropertySource("classpath:config.properties")
@Data
@Component
public class ConfigFileReader {

    /**
     * 系统日志版本
     */
    @Value("${LOG.VERSION}")
    private String LOG_VERSION;

    /**
     * FTP IP
     */
    @Value("${DAT.FTP_IP}")
    private String FTP_IP;

    /**
     * FTP 端口
     */
    @Value("${DAT.FTP_PORT}")
    private String FTP_PORT;

    /**
     * FTP 用户名
     */
    @Value("${DAT.FTP_USER_NAME}")
    private String FTP_USER_NAME;

    /**
     * FTP 密码
     */
    @Value("${DAT.FTP_PASS_WORD}")
    private String FTP_PASSWORD;

    //行里进行密码加密改造
    public String getFTP_PASSWORD() {
        //解密
        return ThreeDes.dePassword(this.FTP_PASSWORD);
    }
    
    /**
     * FTP 人力资源文件路径
     */
    @Value("${DAT.FTP_FILE_PATH}")
    private String FTP_FILE_PATH;

    /**
     * FTP 核心机构文件路径
     */
    @Value("${DAT.FTP_FILE_HX_PATH}")
    private String FTP_FILE_HX_PATH;

    /**
     * 大数据文件本地路径
     */
    @Value("${DAT.LOCALPATH}")
    private String LOCAL_PATH;

    /**
     * 机构文件名称
     */
    @Value("${DAT.ORGFILE_NAME}")
    private String ORG_FILENAME;

    /**
     * 用户文件名称
     */
    @Value("${DAT.USERFILE_NAME}")
    private String USER_FILENAME;

    /**
     * 核心机构文件名称
     */
    @Value("${DAT.HX_ORGFILE_NAME}")
    private String HX_ORGFILE_NAME;

    /**
     * 素材临时存储路径-共享
     */
    @Value("${MATERIAL.FILE_PATH_FILE}")
    private String FILE_PATH_FILE;

    @Value("${GAME.HTTP_DDL}")
    private String HTTP_DDL;

    @Value("${GAME.HTTP_XQDZ}")
    private String HTTP_XQDZ;

    /**
     * 巡查证明文件 上传路径
     */
    @Value("${PARTORAL.PROVE_FILE_PATH}")
    private String PROVE_FILE_PATH;

    /**
     * 快乐服务 上传路径
     */
    @Value("${HAPPYSERVICE.HAPPY_FILE_PATH}")
    private String HAPPY_FILE_PATH;

    /**
     * 活动沙龙 上传路径
     */
    @Value("${ACTIVITIE.ACTIVITIE_FILE_PATH}")
    private String ACTIVITIE_FILE_PATH;

    /**
     * 外包人员 上传路径
     */
    @Value("${CARDSUPPLE.CARD_FILE_PATH}")
    private String CARD_FILE_PATH;

    /**
     * 应用ip，仅开发环境使用此参数
     */
    @Value("${application.ip}")
    private String applicationIp;

    @Value("${application.port}")
    private String applicationPort;

    @Value("${application.contextPath}")
    private String applicationContextPath;

    @Value("${spring.profiles.active}")
    private String springProfile;

    /**
     * 二维码生成路径
     */
    @Value("${MATERIAL.qrImages}")
    private String qrImages;

    /**
     * 巡查记录生成excel文件地址
     */
    @Value("${PARTORAL.RECORD_EXCEL}")
    private String RECORD_EXCEL;

    /**
     * 巡查记录证明文件打包zip路径
     */
    @Value("${PARTORAL.RECORD_PROVE_ZIP_PATH}")
    private String RECORD_PROVE_ZIP_PATH;

    /**
     * 巡查记录 zip 文件地址
     */
    @Value("${PARTORAL.RECORD_DOWNLOAD_PATH}")
    private String RECORD_DOWNLOAD_PATH;

    /**
     * 加班申请 存储路径
     */
    @Value("${CARDSUPPLE.WORK_FILE_PATH}")
    private String WORK_FILE_PATH;

    /**
     * 月度考勤excel目录
     */
    @Value("${ATTEND.DOWN_EXCEL_PATH}")
    private String DOWN_EXCEL_PATH;

}
