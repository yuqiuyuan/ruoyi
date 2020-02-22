package com.ruoyi.web.core;

import com.ruoyi.common.config.Global;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * pdf 操作工具类
 *
 * @author drebander
 * @since dander
 */
public class PdfHelper {
    /**
     * pdf转图片
     *
     * @param filePath 文件路径
     * @return List<imagePath>
     */
    public static List<String> pdfToImagePathList(String filePath) {
        List<String> list = new ArrayList<>();
        //获取文件路径

        String fileDirectory = filePath.substring(0, filePath.lastIndexOf("."));

        String imagePath;
        File file = new File(filePath);
        try {
            File f = new File(fileDirectory);
            if (!f.exists()) {
                f.mkdir();
            }
            PDDocument doc = PDDocument.load(file);
            PDFRenderer renderer = new PDFRenderer(doc);
            int pageCount = doc.getNumberOfPages();
            for (int i = 0; i < pageCount; i++) {
                //第二个参数越大生成图片分辨率越高，转换时间也就越长
                BufferedImage image = renderer.renderImage(i, 1.25f);
                imagePath = fileDirectory + "/" + i + ".jpg";
                ImageIO.write(image, "PNG", new File(imagePath));
                list.add(imagePath);
            }
            //关闭文件,不然该pdf文件会一直被占用。
            doc.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 将pdf首页转换成图片
     *
     * @param filePath 文件路径
     * @return imagePath
     */
    public static String pdfToImagePath(String filePath) {
        String rootPath = Global.getUploadPath();
        String fileDirectory = filePath.substring(0, filePath.lastIndexOf("/"));
        final String fileName = filePath.substring(filePath.lastIndexOf("/") + 1, filePath.lastIndexOf("."));
        final String imgFile = fileDirectory + File.separator + fileName + ".jpg";
        File file = new File(rootPath + filePath);
        try {
            File img = new File(rootPath + imgFile);
            if (img.exists()) {
                img.delete();
            }
            File f = new File(fileDirectory);
            if (!f.exists()) {
                f.mkdir();
            }
            PDDocument doc = PDDocument.load(file);
            PDFRenderer renderer = new PDFRenderer(doc);
            BufferedImage image = renderer.renderImage(0, 1.25f);
            ImageIO.write(image, "PNG", img);
            //关闭文件,不然该pdf文件会一直被占用。
            doc.close();
        } catch (IOException e) {
            e.printStackTrace();

        }
        return imgFile;
    }

}
