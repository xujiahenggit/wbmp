package com.bank.core.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.net.ftp.*;

import java.io.*;
import java.net.SocketException;

/**
 * @Author: Andy
 * @Date: 2020/4/3 17:44
 */
@Slf4j
public class FTPUtil {
    /**
     * Description: 获取FTPClient对象
     *
     * @param ftpHost     FTP地址
     * @param ftpPort     FTP端口
     * @param ftpUserName FTP用户名
     * @param ftpPassword FTP用户密码
     * @return
     * @author sunzh
     * @date 2018年10月10日
     */
    public static FTPClient getFTPClient(String ftpHost, int ftpPort, String ftpUserName, String ftpPassword) {
        FTPClient ftpClient = new FTPClient();
        try {
            ftpClient.connect(ftpHost, ftpPort);
            ftpClient.login(ftpUserName, ftpPassword);
            if (FTPReply.isPositiveCompletion(ftpClient.getReplyCode())) {
                return ftpClient;
            } else {
                log.info("未连接到FTP,用户名或密码错误");
                try {
                    ftpClient.disconnect();
                } catch (IOException e) {
                    log.error("FTP断开连接时错误", e);
                }
                return null;
            }
        } catch (SocketException e) {
            log.error("FTP的IP地址可能错误，请正确配置", e);
            return null;
        } catch (IOException e) {
            log.error("FTP的端口号错误，请正确配置", e);
            return null;
        }
    }

    /**
     * 进入目录
     * Description:
     *
     * @param ftpClient
     * @param ftpPath   绝对路径
     * @return 进入目录是否成功
     * @throws IOException
     * @author sunzh
     * @date 2018年10月10日
     */
    public static void accessDirectory(FTPClient ftpClient, String ftpPath) throws Exception {
        ftpClient.setControlEncoding("UTF-8");
        ftpClient.enterLocalPassiveMode();
        ftpClient.changeWorkingDirectory(ftpPath);
    }

    /**
     * Description: 下载文件
     *
     * @param ftpClient
     * @param localPath 本地路径
     * @param fileName  下载的文件名称
     * @return 是否下载成功
     * @author sunzh
     * @date 2018年10月10日
     */
    public static boolean downloadFile(FTPClient ftpClient, String localPath, String fileName) {
        try {
            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
        } catch (IOException e) {
            log.error("FTP设置BINARY_FILE_TYPE异常", e);
        }
        File file = new File(localPath);
        if (!file.exists()) {
            file.mkdirs();
        }
        FileOutputStream os = null;
        try {
            FTPListParseEngine listParsing = ftpClient.initiateListParsing(fileName);
            if (!listParsing.hasNext()) {
                log.info(fileName + " 不存在");
                return false;
            }
            os = new FileOutputStream(new File(localPath, fileName));
            boolean success = ftpClient.retrieveFile(fileName, os);
            return success;
        } catch (FileNotFoundException e) {
            log.error("FileNotFoundException", e);
            return false;
        } catch (IOException e) {
            log.error("FTP下载文件时发生异常", e);
            return false;
        } finally {
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    log.error("FTP-FileOutputStream关闭异常", e);
                }
            }
        }
    }

    /**
     * Description: 判断是否存在
     *
     * @param
     * @author yuanrx
     * @date 2018年12月03日
     */
    public static boolean isExist(FTPClient ftpClient, String localPath, String fileName) {
        try {
            ftpClient.changeWorkingDirectory(localPath);
            FTPListParseEngine listParsing = ftpClient.initiateListParsing(fileName);
            return listParsing.hasNext();
        } catch (IOException e) {
            log.error("FTP路径错误", e);
            return false;
        }
    }

    /**
     * 检查文件是否存在
     *
     * @param filePath
     * @return
     * @throws Exception
     */
    public boolean checkFileExist(String filePath) throws Exception {
        boolean flag = false;
        File file = new File(filePath);
        if (!file.exists()) {
            log.debug("文件不存在,请检查!");
            throw new Exception("文件不存在,请检查!");
        } else {
            flag = true;
        }
        return flag;
    }

    /**
     * 改变工作目录，如失败则创建文件夹
     *
     * @param remoteFoldPath
     */
    public void changeDirectory(FTPClient ftpClient, String remoteFoldPath) throws Exception {
        if (remoteFoldPath != null) {
            String[] folder = remoteFoldPath.split("/");
            String contact = "/";
            for (int i = 0; i < folder.length; i++) {
                if (null != folder[i] && !"".equals(folder[i])) {
                    contact = contact + folder[i] + "/";
                    boolean flag = ftpClient.changeWorkingDirectory(contact);
                    if (!flag) {
                        boolean make = ftpClient.makeDirectory(contact);
                        boolean changeWorking = ftpClient.changeWorkingDirectory(contact);
                        if (!make || !changeWorking) {
                            throw new Exception("创建或者改变FTP目录失败!");
                        }
                    }
                }
            }
        }
    }

    /**
     * Description: FTP登出并断开连接
     *
     * @param ftpClient
     * @author sunzh
     * @date 2018年10月10日
     */
    public static void logoutAndDisconnect(FTPClient ftpClient) {
        if (ftpClient != null) {
            try {
                ftpClient.logout();
            } catch (IOException e) {
                log.error("FTP登出时错误", e);
            }
            try {
                ftpClient.disconnect();
            } catch (IOException e) {
                log.error("FTP断开连接时错误", e);
            }
            ftpClient = null;
        }
    }


    public static boolean uploadFile(String ftpHost, int ftpPort, String ftpUserName, String ftpPassword,
                                     String pathname, String fileName, InputStream inputStream){
        FTPClient initFtpClient = FTPUtil.getFTPClient(ftpHost,ftpPort, ftpUserName,ftpPassword);
        boolean isSuccess = false;
        try{
            log.info("开始上传文件");
            //设置该参数时才可以更改文件权限，最大权限为666
            initFtpClient.sendCommand("site umask 022");
            initFtpClient.setFileType(FTP.BINARY_FILE_TYPE);
            log.info("被动模式上传文件");
            initFtpClient.enterLocalPassiveMode();
            isSuccess = FTPUtil.CreateDirecroty(pathname,initFtpClient);
            if( isSuccess){
                isSuccess = initFtpClient.storeFile(fileName, inputStream);
            }
            //更新文件权限
            initFtpClient.sendCommand("chmod 666 -R " , fileName);
            log.info("ftp上传文件 : " + isSuccess);
            initFtpClient.logout();
        }catch (IOException e) {
            log.error("上传文件失败", e);
        }finally{
            if(initFtpClient.isConnected()){
                try{
                    initFtpClient.disconnect();
                }catch(IOException e){
                    log.error(e.getMessage(), e);
                }
            }
            if(null != inputStream){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    log.error(e.getMessage(), e);
                }
            }
        }
        return isSuccess;
    }

    //改变目录路径
    public static boolean changeWorkingDirectory(String directory, FTPClient initFtpClient) {
        boolean flag = true;
        try {
            flag = initFtpClient.changeWorkingDirectory(directory);
            if (flag) {
                log.info("进入文件夹" + directory + " 成功！");
            } else {
                log.info("进入文件夹" + directory + " 失败！开始创建文件夹");
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return flag;
    }


    //创建多层目录文件，如果有ftp服务器已存在该文件，则不创建，如果无，则创建
    public static boolean CreateDirecroty(String remote, FTPClient initFtpClient) throws IOException {
        boolean success = true;
        String directory = remote + "/";
        // 如果远程目录不存在，则递归创建远程服务器目录
        if (!directory.equalsIgnoreCase("/") && !changeWorkingDirectory(new String(directory), initFtpClient)) {
            int start = 0;
            int end = 0;
            if (directory.startsWith("/")) {
                start = 1;
            } else {
                start = 0;
            }
            end = directory.indexOf("/", start);
            String path = "";
            String paths = "";
            while (true) {
                String subDirectory = new String(remote.substring(start, end).getBytes("GBK"), "iso-8859-1");
                path = path + "/" + subDirectory;
                if (!changeWorkingDirectory(path, initFtpClient)) {
                    if (makeDirectory(subDirectory, initFtpClient)) {
                        changeWorkingDirectory(subDirectory, initFtpClient);
                    } else {
                        log.info("创建目录[" + subDirectory + "]失败");
                        changeWorkingDirectory(subDirectory, initFtpClient);
                    }
                }

                paths = paths + "/" + subDirectory;
                start = end + 1;
                end = directory.indexOf("/", start);
                // 检查所有目录是否创建完毕
                if (end <= start) {
                    break;
                }
            }
        }
        return success;
    }

    //判断ftp服务器文件是否存在
    public static boolean existFile(String path, FTPClient initFtpClient) throws IOException {

        boolean flag = false;
        FTPFile[] ftpFileArr = initFtpClient.listFiles(path);
        if (ftpFileArr.length > 0) {
            flag = true;
        }
        return flag;
    }

    //创建目录
    public static boolean makeDirectory(String dir, FTPClient initFtpClient) {
        boolean flag = true;
        try {
            flag = initFtpClient.makeDirectory(dir);
            if (flag) {
                log.info("创建文件夹" + dir + " 成功！");

            } else {
                log.info("创建文件夹" + dir + " 失败！");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }


    /**
     * 删除FTP服务器指定文件
     *
     * @param filePath
     * @throws Exception
     */
    public static void deleteFile(FTPClient ftpClient, String filePath) throws Exception {
        boolean exist = ftpClient.deleteFile(filePath);
        log.info("FTP开始删除文件:"+filePath);
        if (!exist) {
            log.info("FTP删除文件失败:文件不存在");
        }
    }

    /**
     * 上传文件到FTP根目录
     *
     * @param localFile
     * @param newName
     * @throws Exception
     */
    public void uploadFile(FTPClient ftpClient, String localFile, String newName) throws Exception {
        InputStream input = null;
        try {
            File file = null;
            if (checkFileExist(localFile)) {
                file = new File(localFile);
            }
            input = new FileInputStream(file);
            boolean result = ftpClient.storeFile(newName, input);
            if (!result) {
                throw new Exception("文件上传失败!");
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (input != null) {
                input.close();
            }
        }
    }

    /**
     * 上传文件到FTP根目录
     *
     * @param input
     * @param newName
     * @throws Exception
     */
    public void uploadFile(FTPClient ftpClient, InputStream input, String newName) throws Exception {
        try {
            boolean result = ftpClient.storeFile(newName, input);
            if (!result) {
                throw new Exception("文件上传失败!");
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (input != null) {
                input.close();
            }
        }

    }

    /**
     * 上传文件到指定的FTP路径下
     *
     * @param localFile
     * @param newName
     * @param remoteFoldPath
     * @throws Exception
     */
    public void uploadFile(FTPClient ftpClient, String localFile, String newName, String remoteFoldPath) throws Exception {
        InputStream input = null;
        log.debug("上传文件到指定的FTP路径:" + localFile);
        try {
            File file = null;
            if (checkFileExist(localFile)) {
                file = new File(localFile);
            } else {
                throw new Exception("要上传的文件不存在");
            }
            input = new FileInputStream(file);

            // 改变当前路径到指定路径
            this.changeDirectory(ftpClient, remoteFoldPath);
            boolean result = ftpClient.storeFile(newName, input);
            if (!result) {
                throw new Exception("文件上传失败!");
            }

        } catch (Exception e) {
            throw e;
        } finally {
            if (input != null) {
                input.close();
            }
        }
    }

    /**
     * 上传文件到指定的FTP路径下
     *
     * @param input
     * @param newName
     * @param remoteFoldPath
     * @throws Exception
     */
    public void uploadFile(FTPClient ftpClient, InputStream input, String newName, String remoteFoldPath) throws Exception {
        try {
            // 改变当前路径到指定路径
            this.changeDirectory(ftpClient, remoteFoldPath);
            boolean result = ftpClient.storeFile(newName, input);
            if (!result) {
                throw new Exception("文件上传失败!");
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (input != null) {
                input.close();
            }
        }
    }

    /**
     * 更改文件名
     *
     * @param directory 文件所在目录
     * @param oldFileNm 原文件名
     * @param newFileNm 新文件名
     * @throws Exception
     */
    public void rename(FTPClient ftpClient, String directory, String oldFileNm, String newFileNm) throws Exception {

        ftpClient.changeWorkingDirectory(directory);
        ftpClient.rename(oldFileNm, newFileNm);

    }

}
