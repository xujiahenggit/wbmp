package com.bank.core.utils;

import lombok.extern.slf4j.Slf4j;
//import org.bytedeco.javacv.FFmpegFrameGrabber;
//import org.bytedeco.javacv.Frame;
//import org.bytedeco.javacv.Java2DFrameConverter;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

@Slf4j
public class VideoUtil {
    /**
     * 将视频文件帧处理并以“jpg”格式进行存储。
     * 依赖FrameToBufferedImage方法：将frame转换为bufferedImage对象
     //*@param videoPath 视频文件路径
     //*@param videoFramesPath 存放截取视频某一帧的图片
     //* @param videoFileName 文件名称
     */
    /*public static String grabberVideoFramer(String videoPath,String videoFramesPath, String videoFileName){
        log.info("=========================进入获取视频第一帧方法==========================");
        //最后获取到的视频的图片的路径
        String videPicture="";
        //Frame对象
        Frame frame = null;
        //标识
        int flag = 0;
        //文件绝对路径+名字
        String fileName = null;
        try {
			 *//*
            获取视频文件
            *//*
            FFmpegFrameGrabber fFmpegFrameGrabber = new FFmpegFrameGrabber(videoPath + "/" + videoFileName);
            fFmpegFrameGrabber.start();

            //获取视频总帧数
            int ftp = fFmpegFrameGrabber.getLengthInFrames();

            while (flag <= ftp) {
                frame = fFmpegFrameGrabber.grabImage();
				*//*
				对视频的第五帧进行处理
				 *//*
                if (frame != null && flag==5) {
                    //文件绝对路径+名字
                   fileName = videoFramesPath +"/"+ videoFileName.substring(0,videoFileName.lastIndexOf("."))+"_" + String.valueOf(flag) + ".jpg";
                    //文件储存对象
                    File outPut = new File(fileName);
                    ImageIO.write(FrameToBufferedImage(frame), "jpg", outPut);

                    break;
                }
                flag++;
            }
            fFmpegFrameGrabber.stop();
            fFmpegFrameGrabber.close();
            log.info("=========================进入获取视频第一帧方法执行成功==========================");
        } catch (Exception e) {
            log.info("=========================进入获取视频第一帧方法执行报错："+e.getMessage());
        }
        return fileName;
    }*/

    /*public static BufferedImage FrameToBufferedImage(Frame frame) {
        //创建BufferedImage对象
        Java2DFrameConverter converter = new Java2DFrameConverter();
        BufferedImage bufferedImage = converter.getBufferedImage(frame);
        return bufferedImage;
    }*/



}
