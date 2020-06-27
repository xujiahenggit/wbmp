package com.bank.core.utils;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Shape;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.OutputStream;
import java.util.Hashtable;

import javax.imageio.ImageIO;

import org.apache.commons.lang.StringUtils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import lombok.extern.slf4j.Slf4j;

/**
 *
 * ClassName: QrCodeUtil
 *
 * @author Yanwen D. Ding
 *
 * Copyright © 2016 Yusys Technologies Co., Ltd.
 *
 * All Rights Reserved
 *
 * http://www.yusys.com.cn
 *
 * Create Time: 2020/04/16 10:43:07
 */
@Slf4j
public class QrCodeUtil {

    private static final String CHARSET = "utf-8";

    private static final String FORMAT_NAME = "JPG";

    private static final int QRCODE_SIZE = 300; // 二维码尺寸，宽度和高度均是300

    private static final int WIDTH = 80;  //LOGO宽度

    private static final int HEIGHT = 80; //LOGO高度

    /**
     * 生成二维码的方法
     * @param content 目标url
     * @param imgPath logo图片地址
     * @param needCompress 是否压缩logo
     * @return 二维码图片
     * @throws Exception
     */
    private static BufferedImage createImage(String content, String imgPath, boolean needCompress) throws Exception {
        Hashtable<EncodeHintType, Object> hints = new Hashtable<>();
        //指定二维码的纠错等级为高级
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        //指定字符编码为“utf-8”
        hints.put(EncodeHintType.CHARACTER_SET, CHARSET);
        //设置图片的边距
        hints.put(EncodeHintType.MARGIN, 1);
        //参数1：内容，目标url,参数2：固定写法，参数3：二维码的宽度，参数4：二维码的高度，参数5：二维码属性设置
        BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, QRCODE_SIZE, QRCODE_SIZE, hints);
        //二维码的宽度
        int width = bitMatrix.getWidth();
        //二维码的高度
        int height = bitMatrix.getHeight();
        //生成的二维码image
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                image.setRGB(x, y, bitMatrix.get(x, y) ? 0xFF000000 : 0xFFFFFFFF);
            }
        }
        //logo图片地址为null或空时
        if (StringUtils.isBlank(imgPath)) {
            return image; //只返回二维码图片，无中间的logo
        }
        // 插入logo图片  参数1：二维码图片，参数2：logo图片地址，参数3：压缩图片
        QrCodeUtil.insertImage(image, imgPath, needCompress);
        return image;
    }

    /**
     * 插入logo图片
     * @param source 二维码图片
     * @param imgPath LOGO图片地址
     * @param needCompress 是否压缩
     * @throws Exception
     */
    private static void insertImage(BufferedImage source, String imgPath, boolean needCompress) throws Exception {
        File file = new File(imgPath);//logo图片地址放入文件
        if (!file.exists()) {
            System.err.println("" + imgPath + " 该文件不存在！");
            return;
        }
        Image src = ImageIO.read(new File(imgPath));
        int width = src.getWidth(null);
        int height = src.getHeight(null);
        // 压缩LOGO
        if (needCompress) {
            //如果上传logo宽 >60
            if (width > WIDTH) {
                width = WIDTH;
            }
            //如果上传logo高>60
            if (height > HEIGHT) {
                height = HEIGHT;
            }
            Image image = src.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            BufferedImage tag = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            Graphics g = tag.getGraphics();
            // 绘制缩小后的图
            g.drawImage(image, 0, 0, null);
            g.dispose();
            src = image;
        }
        // 插入LOGO
        Graphics2D graph = source.createGraphics();
        int x = (QRCODE_SIZE - width) / 2;
        int y = (QRCODE_SIZE - height) / 2;
        graph.drawImage(src, x, y, width, height, null);
        Shape shape = new RoundRectangle2D.Float(x, y, width, width, 6, 6);
        graph.setStroke(new BasicStroke(3f));
        graph.draw(shape);
        graph.dispose();
    }

    /**
     * 生成二维码(内嵌LOGO)
     * @param content  内容
     * @param imgPath  logo地址
     * @param destPath 存放目录
     * @param needCompress 是否压缩logo
     * @throws Exception
     */
    public static String encode(String content, String imgPath, String destPath, boolean needCompress, String filename) throws Exception {
        BufferedImage image = QrCodeUtil.createImage(content, imgPath, needCompress); //生成二维码
        mkdirs(destPath);
        String file = filename + ".jpg";
        ImageIO.write(image, FORMAT_NAME, new File(destPath + "/" + file));
        return destPath + "/" + file;
    }

    /**
     * 当文件夹不存在时，mkdirs会自动创建多层目录，区别于mkdir．(mkdir如果父目录不存在则会抛出异常)
     * @param destPath 存放目录
     */
    public static void mkdirs(String destPath) {
        File file = new File(destPath);
        if (!file.exists() && !file.isDirectory()) {
            file.mkdirs();
        }
    }

    /**
     * 生成二维码(内嵌LOGO),没有压缩
     *
     * @param content  内容
     * @param imgPath  LOGO地址
     * @param destPath 存储地址
     * @throws Exception
     */
    public static void encode(String content, String imgPath, String destPath, String filename) throws Exception {
        QrCodeUtil.encode(content, imgPath, destPath, false, filename);
    }

    /**
     * 生成二维码，无内嵌logo
     *
     * @param content      内容
     * @param destPath     存储地址
     * @param needCompress 是否压缩LOGO
     * @throws Exception
     */
    public static void encode(String content, String destPath, boolean needCompress, String filename) throws Exception {
        QrCodeUtil.encode(content, null, destPath, needCompress, filename);
    }

    /**
     * 生成二维码
     *
     * @param content  内容
     * @param destPath 存储地址
     * @throws Exception
     */
    public static void encode(String content, String destPath, String filename) throws Exception {
        QrCodeUtil.encode(content, null, destPath, false, filename);
    }

    /**
     * 生成二维码(内嵌LOGO)
     *
     * @param content      内容
     * @param imgPath      LOGO地址
     * @param output       输出流
     * @param needCompress 是否压缩LOGO
     * @throws Exception
     */
    public static void encode(String content, String imgPath, OutputStream output, boolean needCompress) throws Exception {
        BufferedImage image = QrCodeUtil.createImage(content, imgPath, needCompress);
        ImageIO.write(image, FORMAT_NAME, output);
    }

    /**
     * 生成二维码
     *
     * @param content 内容
     * @param output  输出流
     * @throws Exception
     */
    public static void encode(String content, OutputStream output) throws Exception {
        QrCodeUtil.encode(content, null, output, false);
    }

    /**
     * 解析二维码
     * 按文件参数解析
     * @param file 二维码图片
     * @return
     * @throws Exception
     */
    public static String decode(File file) throws Exception {
        BufferedImage image;
        image = ImageIO.read(file);
        if (image == null) {
            return null;
        }
        BufferedImageLuminanceSource source = new BufferedImageLuminanceSource(image);
        BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
        Result result;
        Hashtable<DecodeHintType, String> hints = new Hashtable<>();
        hints.put(DecodeHintType.CHARACTER_SET, CHARSET);//指定字符编码为“utf-8”
        result = new MultiFormatReader().decode(bitmap, hints);
        String resultStr = result.getText();//二维码文本内容
        return resultStr;
    }

    /**
     * 解析二维码
     * 按二维码图片地址解析
     * @param path 二维码图片地址
     * @return  不是二维码的内容返回null,是二维码直接返回识别的结果
     * @throws Exception
     */
    public static String decode(String path) throws Exception {
        /**
         * http://www.test.com/page/weizhi.html?devicesName=消防栓_WRbb&devicesNumber=2018092512094659f6aee7&hydrantId=d88219b0-c07e-11e8-87a3-2302a122c883
         * &equipment=触发器01,撞到触发器;触发器02,开盖触发器;触发器03,漏水触发器;
         */
        return QrCodeUtil.decode(new File(path));
    }

    /**
     *
     * wsy
     * 删除本地二维码
     * 2019年9月27日
     */
    public static void deleteQR(String QrPath) {
        if (StringUtils.isNotBlank(QrPath)) {
            System.out.println(QrPath);
            File qrImgFile = new File(QrPath);
            if (qrImgFile.exists()) {
                if (qrImgFile.delete()) {
                    log.info("本地二维码图片删除成功");
                }
            }
        }
    }

}
