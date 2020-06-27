package com.bank.manage.util;

import lombok.extern.slf4j.Slf4j;
import org.icepdf.core.pobjects.Document;
import org.icepdf.core.util.GraphicsRenderingHints;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class PDF2Image {

    public static List<String> pdf2Image(String PDF_path){
        log.info("进入PDF转图片，PDF源地址,{}",PDF_path);
        Document document = new Document();
        try {
            document.setFile(PDF_path);
        } catch (Exception e) {
            e.printStackTrace();
        }
        float scale = 2.5f;//缩放比例
        float rotation = 0f;//旋转角度
        List<String> imagePath = new ArrayList<>();
        String substring = PDF_path.substring(0, PDF_path.lastIndexOf("."));
        for (int i = 0; i < document.getNumberOfPages(); i++) {
            BufferedImage image = (BufferedImage)
                    document.getPageImage(i, GraphicsRenderingHints.SCREEN, org.icepdf.core.pobjects.Page.BOUNDARY_CROPBOX, rotation, scale);
            RenderedImage rendImage = image;
            try {
                String filePath = substring +"_"+ i + ".jpg";
                File file = new File(filePath);
                ImageIO.write(rendImage, "png", file);
                imagePath.add(filePath);
            } catch (IOException e) {
                e.printStackTrace();
            }
            image.flush();
        }
        document.dispose();
        log.info("PDF转图片成功！");
        return imagePath;
    }
}
