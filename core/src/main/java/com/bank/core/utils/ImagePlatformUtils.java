package com.bank.core.utils;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.core.env.Environment;

import com.bank.core.entity.BizException;
import com.bank.core.entity.ImagePlatformFile;
import com.bank.core.entity.ImagePlatformResponse;
import com.sunyard.client.SunEcmClientApi;
import com.sunyard.client.bean.ClientBatchBean;
import com.sunyard.client.bean.ClientBatchFileBean;
import com.sunyard.client.bean.ClientBatchIndexBean;
import com.sunyard.client.bean.ClientFileBean;
import com.sunyard.client.impl.SunEcmClientSocketApiImpl;
import com.sunyard.util.OptionKey;

import lombok.extern.slf4j.Slf4j;

/**
 * 影像平台工具类
 * ClassName: ImagePlatformUtils
 *
 * @author Yanwen D. Ding
 *
 * Copyright © 2016 Yusys Technologies Co., Ltd.
 *
 * All Rights Reserved
 *
 * http://www.yusys.com.cn
 *
 * Create Time: 2020/07/13 15:50:21
 */
@Slf4j
public class ImagePlatformUtils {

    /**
     * 现场检查
     * 检查内容：9301010000
     userName：fxyj
    password:fxyj1234
    MODEL_CODE:XCJC
    filePartName:XCJC_PART
    
    
    预警编号：9401000000
    预警监测
    userName：fxyj
    password:fxyj1234
    MODEL_CODE:YJJC
    filePartName:YJJC_PART
    
    1、上传： 总数（AMOUNT:代表上传图片的总数） 页码（BUSI_PAGENUM_NUMBER：影像平台前端需要，参数逻辑为 初始为1 有几张图片就+1的形式传 每个图片对应一个页码 不能重复）
    2、更新：更新-追加  需要注意 总数会发生变化，所以首先要查询出原批次总数是多少（调用查询批次接口）。然后追加几张 更新时候总数就得加上你追加得那集中得出得总数
    页码逻辑也是一样，比如原批次总数为5  那么他的最大页码应该也是5 所以后续追加 是从6开始 根据你图片几张定义到几
    3、更新-删除  删除中 会用到FILE_NO 这个参数， 首先要查询出来这个参数（调用查询批次接口返回报文里面会带有），然后除总数要变化，其他可以不用变化
     */

    private String ip = "162.16.1.224"; //为SunECMDM的ip地址

    private int socketPort = 8021;//为SunECMDM的socket端口

    private String userName = "fxyj"; //登录SunECMDM的用户名

    private String passWord = "fxyj1234"; //登录SunECMDM的密码

    private String serverName = "SunECM"; // 连接的服务工程名称

    private String groupName = "SunECM组"; // 内容存储服务器组名

    // SOCKET接口
    private SunEcmClientApi clientApi = null;

    private String modelCode = ""; // 索引对象内容模型代码

    private String filePartName = ""; // 文档对象模型代码

    private String busiFileType = "";

    public ImagePlatformUtils(String type) {
        Environment env = (Environment) ApplicationContextUtil.getBeanByClass(Environment.class);
        boolean flag = StringUtils.equals(type, "xcjc");
        modelCode = flag ? "XCJC" : "YJJC";
        filePartName = flag ? "XCJC_PART" : "YJJC_PART";
        busiFileType = flag ? "9301010000" : "9401000000";

        ip = env.getProperty("IMAGE_PLATFORM.IP");
        socketPort = Integer.parseInt(env.getProperty("IMAGE_PLATFORM.PORT"));
        userName = env.getProperty("IMAGE_PLATFORM.USER_NAME");
        passWord = env.getProperty("IMAGE_PLATFORM.PASS_WORD");

        clientApi = new SunEcmClientSocketApiImpl(ip, socketPort);
    }

    public void login() {
        try {
            String resultMsg = clientApi.login(userName, passWord);
            log.info("影像平台===登陆返回的信息[" + resultMsg + "]");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void logout() {
        try {
            String resultMsg = clientApi.logout(userName);
            log.info("影像平台===登出返回的信息[" + resultMsg + "]");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 影像文件上传
     * @param date
     * @param busiNo
     * @return
     */
    public ImagePlatformResponse upload(File[] listFiles) {
        ClientBatchBean clientBatchBean = new ClientBatchBean();
        clientBatchBean.setUser(userName);
        clientBatchBean.setPassWord(passWord);
        clientBatchBean.setModelCode(modelCode);
        clientBatchBean.setBreakPoint(false);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String busiSerialNo = UUID.randomUUID().toString();
        String busiStartDate = sdf.format(new Date());

        ClientBatchIndexBean clientBatchIndexBean = new ClientBatchIndexBean();
        clientBatchIndexBean.addCustomMap("AMOUNT", listFiles.length + "");
        clientBatchIndexBean.addCustomMap("BUSI_START_DATE", busiStartDate);
        clientBatchIndexBean.addCustomMap("BUSI_SERIAL_NO", busiSerialNo);

        ClientBatchFileBean clientBatchFileBean = new ClientBatchFileBean();
        clientBatchFileBean.setFilePartName(filePartName);

        for (int i = 0; i < listFiles.length; i++) {
            File file = listFiles[i];
            ClientFileBean fileBean = new ClientFileBean();
            fileBean.setFileName(file.getAbsolutePath());
            String fileName = file.getName();
            fileBean.setFileFormat(fileName.substring(fileName.lastIndexOf(".") + 1));
            fileBean.addOtherAtt("BUSI_FILE_PAGENUM", (i + 1) + "");
            fileBean.addOtherAtt("BUSI_FILE_TYPE", busiFileType);
            clientBatchFileBean.addFile(fileBean);
        }

        clientBatchBean.setIndex_Object(clientBatchIndexBean);
        clientBatchBean.addDocument_Object(clientBatchFileBean);

        ImagePlatformResponse response = new ImagePlatformResponse();
        try {
            String resultMsg = clientApi.upload(clientBatchBean, groupName);
            String[] result = resultMsg.split("<<::>>");
            if (result[0].equals("SUCCESS")) {
                response.setBusiSerialNo(busiSerialNo);
                response.setBusiStartDate(busiStartDate);
                response.setContentId(result[1]);
                log.info("影像平台上传成功！影像ID[{}]", result[1]);
            }
            else {
                throw new BizException("影像平台上传失败：" + resultMsg);
            }
        }
        catch (Exception e) {
            throw new BizException(e.getMessage());
        }
        return response;
    }

    /**
     * 影像文件更新
     * @param date
     * @param busiNo
     * @return
     */
    public boolean update(String contentId, String busiStartDate, File[] listFiles) {
        boolean result = false;
        ClientBatchBean clientBatchBean = new ClientBatchBean();
        clientBatchBean.setUser(userName);
        clientBatchBean.setPassWord(passWord);
        clientBatchBean.setModelCode(modelCode);
        clientBatchBean.setOwnMD5(false);

        clientBatchBean.getIndex_Object().addCustomMap("BUSI_START_DATE", busiStartDate);
        clientBatchBean.getIndex_Object().setContentID(contentId);

        ImagePlatformResponse response = query(contentId, busiStartDate);
        clientBatchBean.getIndex_Object().addCustomMap("AMOUNT", (response.getAmount() + listFiles.length) + "");
        List<ImagePlatformFile> fileList = response.getImagePlatformFileList();
        //升序排列后获取最后一个元素
        int maxPageNum = fileList.size() == 0 ? 0 : fileList.get(fileList.size() - 1).getPageNum();

        ClientBatchFileBean clientBatchFileBean = new ClientBatchFileBean();
        clientBatchFileBean.setFilePartName(filePartName);

        for (int i = 0; i < listFiles.length; i++) {
            File file = listFiles[i];
            ClientFileBean fileBean = new ClientFileBean();
            fileBean.setOptionType(OptionKey.U_ADD);
            fileBean.setFileName(file.getAbsolutePath());
            String fileName = file.getName();
            fileBean.setFileFormat(fileName.substring(fileName.lastIndexOf(".") + 1));
            fileBean.addOtherAtt("BUSI_FILE_PAGENUM", (maxPageNum + i + 1) + "");
            fileBean.addOtherAtt("BUSI_FILE_TYPE", busiFileType);
            clientBatchFileBean.addFile(fileBean);
        }
        clientBatchBean.addDocument_Object(clientBatchFileBean);

        try {
            String resultMsg = clientApi.update(clientBatchBean, groupName, true);
            if (resultMsg.startsWith("SUCCESS")) {
                log.info("影像平台更新成功，影像ID[{}]！", contentId);
                result = true;
            }
            else {
                throw new BizException("影像平台删除失败：" + resultMsg);
            }
        }
        catch (Exception e) {
            throw new BizException(e.getMessage());
        }
        return result;
    }

    /**
     * 影像文件删除
     * @param date
     * @param busiNo
     * @return
     */
    public boolean delete(String contentId, String busiStartDate, List<String> deleteFileNames) {
        boolean result = false;
        ClientBatchBean clientBatchBean = new ClientBatchBean();
        clientBatchBean.setUser(userName);
        clientBatchBean.setPassWord(passWord);
        clientBatchBean.setModelCode(modelCode);
        clientBatchBean.setOwnMD5(false);

        clientBatchBean.getIndex_Object().addCustomMap("BUSI_START_DATE", busiStartDate);
        clientBatchBean.getIndex_Object().setContentID(contentId);

        ImagePlatformResponse response = query(contentId, busiStartDate);
        clientBatchBean.getIndex_Object().addCustomMap("AMOUNT", (response.getAmount() - deleteFileNames.size()) + "");

        ClientBatchFileBean clientBatchFileBean = new ClientBatchFileBean();
        clientBatchFileBean.setFilePartName(filePartName);

        for (int i = 0; i < deleteFileNames.size(); i++) {
            String fileName = deleteFileNames.get(i);
            ClientFileBean fileBean = new ClientFileBean();
            fileBean.setOptionType(OptionKey.U_DEL);
            fileBean.setFileNO(fileName.substring(0, fileName.lastIndexOf(".")));
            clientBatchFileBean.addFile(fileBean);
        }
        clientBatchBean.addDocument_Object(clientBatchFileBean);

        try {
            String resultMsg = clientApi.update(clientBatchBean, groupName, true);
            if (resultMsg.startsWith("SUCCESS")) {
                log.info("影像平台删除成功，影像ID[{}]！", contentId);
                result = true;
            }
            else {
                throw new BizException("影像平台删除失败：" + resultMsg);
            }
        }
        catch (Exception e) {
            throw new BizException(e.getMessage());
        }
        return result;
    }

    /**
     * 影像文件查询
     * @param contentId 影像ID
     * @param busiStartDate 影像时间
     * @return
     */
    public ImagePlatformResponse query(String contentId, String busiStartDate) {
        ClientBatchBean clientBatchBean = new ClientBatchBean();
        clientBatchBean.setUser(userName);
        clientBatchBean.setPassWord(passWord);
        clientBatchBean.setModelCode(modelCode);

        clientBatchBean.getIndex_Object().setContentID(contentId);
        clientBatchBean.getIndex_Object().addCustomMap("BUSI_START_DATE", busiStartDate);

        ClientBatchFileBean clientBatchFileBean = new ClientBatchFileBean();
        clientBatchFileBean.setFilePartName(filePartName);

        // 将文档部件信息与批次信息关联
        clientBatchBean.addDocument_Object(clientBatchFileBean);
        ImagePlatformResponse response = new ImagePlatformResponse();
        response.setContentId(contentId);
        response.setBusiStartDate(busiStartDate);
        response.setAmount(0);

        try {
            String resultMsg = clientApi.queryBatch(clientBatchBean, groupName);

            String[] result = resultMsg.split("<<::>>");
            if (result[0].equals("0001")) {
                ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(result[1].getBytes());
                SAXReader builder = new SAXReader();
                Document doc = builder.read(byteArrayInputStream);
                Element rootElement = doc.getRootElement();
                String amount = rootElement.element("BatchBean").element("index_Object").element("customMap").element("AMOUNT").element("string").getTextTrim();
                response.setAmount(Integer.parseInt(amount));
                List<Element> fileElements = rootElement.element("BatchBean").element("document_Objects").element("BatchFileBean").element("files").elements("FileBean");

                List<ImagePlatformFile> imagePlatformFileList = new ArrayList<ImagePlatformFile>();

                for (int i = 0; i < fileElements.size(); i++) {
                    Element fileElement = fileElements.get(i);
                    String fileType = fileElement.attribute("FILE_FORMAT").getValue();
                    String fileNo = fileElement.attribute("FILE_NO").getValue();
                    String url = fileElement.attribute("URL").getValue();
                    String pageNum = fileElement.element("otherAtt").element("BUSI_FILE_PAGENUM").element("string").getTextTrim();

                    ImagePlatformFile file = new ImagePlatformFile();
                    file.setFileType("2");
                    file.setHttpFilePath(url);
                    file.setFileName(fileNo + "." + fileType);
                    file.setPageNum(Integer.parseInt(pageNum == "" ? "0" : pageNum));
                    imagePlatformFileList.add(file);
                }
                //按照页码数升序排列
                imagePlatformFileList = imagePlatformFileList.stream().sorted(Comparator.comparing(ImagePlatformFile::getPageNum)).collect(Collectors.toList());
                response.setImagePlatformFileList(imagePlatformFileList);
            }
            else {
                throw new BizException("影像平台查询错误：" + resultMsg);
            }
            log.info("查询批次返回的信息[{}]", resultMsg);
        }
        catch (Exception e) {
            throw new BizException("影像平台查询错误：" + e.getMessage());
        }
        return response;
    }

    public static void main(String[] args) {
        ImagePlatformUtils utils = new ImagePlatformUtils("xcjc");
        //utils.login();
        //utils.upload("20200713", UUID.randomUUID().toString(true));
        //utils.logout();
        //utils.queryBatch("202007_242_CF03E9D2-C910-8279-077A-E9FBAFACA420-1", "20200713");
        List<String> list = new ArrayList<>();
        list.add("2143453215154.2314214.jpg");
        list.add("2143453215154.23142147556765.png");
        utils.delete("202007_130_221B7599-B3F7-9111-6CE7-947D0DE21663-1", "20200714", list);
    }
}
