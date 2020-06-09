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
     * http素材临时路径--共享
     */
    @Value("${MATERIAL.HTTP_PATH}")
    private String HTTP_PATH;

    /**
     * 素材临时存储路径-共享
     */
    @Value("${MATERIAL.UPLOAD_FILE_PATH}")
    private String UPLOAD_FILE_PATH;

    /**
     * 素材临时存储路径-共享
     */
    @Value("${MATERIAL.FILE_PATH_FILE}")
    private String FILE_PATH_FILE;

    /**
     * 素材临时存储FTP_IP
     */
    @Value("${MATERIAL.FILE_FTP_IP}")
    private String FILE_FTP_IP;

    /**
     * 素材临时存储FTP_PORT
     */
    @Value("${MATERIAL.FILE_FTP_PORT}")
    private String FILE_FTP_PORT;

    /**
     * 素材临时存储FTP_USER
     */
    @Value("${MATERIAL.FILE_FTP_USER}")
    private String FILE_FTP_USER;

    /**
     * 素材临时存储FTP_PASS
     */
    @Value("${MATERIAL.FILE_FTP_PASS}")
    private String FILE_FTP_PASS;

    /**
     * 素材临时存储路径-FTP_PATH
     */
    @Value("${MATERIAL.FILE_FTP_PATH}")
    private String FILE_FTP_PATH;

    /**
     * http素材临时路径-FTP
     */
    @Value("${MATERIAL.FILE_FTP_HTTP_PATH}")
    private String FILE_FTP_HTTP_PATH;


    @Value("${GAME.HTTP_DDL}")
    private String HTTP_DDL;

    @Value("${GAME.HTTP_XQDZ}")
    private String HTTP_XQDZ;

    @Value("${MESSAGE.PATH}")
    private String MESSAGE_PATH;

    /**
     * 巡查证明文件 上传路径
     */
    @Value("${PARTORAL.PROVE_FILE_PATH}")
    private String PROVE_FILE_PATH;

    /**
     * 巡查证明文件 访问路径
     */
    @Value("${PARTORAL.PROVE_ACCESS_PATH}")
    private String PROVE_ACCESS_PATH;

    /**
     * 快乐服务 上传路径
     */
    @Value("${HAPPYSERVICE.HAPPY_FILE_PATH}")
    private String HAPPY_FILE_PATH;

    /**
     * 快乐服务 访问路径
     */
    @Value("${HAPPYSERVICE.HAPPY_ACCESS_PATH}")
    private String HAPPY_ACCESS_PATH;

    /**
     * 活动沙龙 上传路径
     */
    @Value("${ACTIVITIE.ACTIVITIE_FILE_PATH}")
    private String ACTIVITIE_FILE_PATH;

    /**
     * 活动沙龙 访问路径
     */
    @Value("${ACTIVITIE.ACTIVITIE_ACCESS_PATH}")
    private String ACTIVITIE_ACCESS_PATH;

    /**
     * 外包人员 上传路径
     */
    @Value("${CARDSUPPLE.CARD_FILE_PATH}")
    private String CARD_FILE_PATH;

    /**
     * 外包人员 访问路径
     */
    @Value("${CARDSUPPLE.CARD_ACCESS_PATH}")
    private String CARD_ACCESS_PATH;

    @Value("${tomcat.baseIp}")
    private String tomcatBaseIp;

//    @Value("${tomcat.port}")
//    private String tomcatPort;
//
//
//    @Value("${tomcat.contextPath}")
//    private String contextPath;

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
     * 巡查记录excel访问路径
     */
    @Value("${PARTORAL.RECORD_EXCEL_ACCESS_PATH}")
    private String RECORD_EXCEL_ACCESS_PATH;

    /**
     * 巡查记录证明文件打包zip路径
     */
    @Value("${PARTORAL.RECORD_PROVE_ZIP_PATH}")
    private String RECORD_PROVE_ZIP_PATH;

    /**
     * 巡查记录证明文件打包zip 访问路径
     */
    @Value("${PARTORAL.RECORD_PROVE_ZIP_ACCESS_PATH}")
    private String RECORD_PROVE_ZIP_ACCESS_PATH;

    /**
     * 巡查记录 zip 文件地址
     */
    @Value("${PARTORAL.RECORD_DOWNLOAD_PATH}")
    private String RECORD_DOWNLOAD_PATH;

    /**
     * 巡查记录zip 访问路径
     */
    @Value("${PARTORAL.RECORD_DOWNLOAD_ACCESS_PATH}")
    private String RECORD_DOWNLOAD_ACCESS_PATH;

    /**
     * 加班申请 存储路径
     */
    @Value("${CARDSUPPLE.WORK_FILE_PATH}")
    private String WORK_FILE_PATH;

    /**
     * 加班申请 存储路径
     */
    @Value("${CARDSUPPLE.WORK_ACCESS_PATH}")
    private String WORK_ACCESS_PATH;

    /**
     * 月度考勤excel目录
     */
    @Value("${ATTEND.DOWN_EXCEL_PATH}")
    private String DOWN_EXCEL_PATH;

    /**
     * 月度考勤excel访问路径
     */
    @Value("${ATTEND.DOWN_EXCEL_ACCESS_PATH}")
    private String DOWN_EXCEL_ACCESS_PATH;
}
