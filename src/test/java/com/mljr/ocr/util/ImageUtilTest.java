package com.mljr.ocr.util;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class ImageUtilTest {

    static String path = "/Users/lizhenmin/tmp/img/test.jpg";
    static String targetpath = "/Users/lizhenmin/tmp/img/test1.jpg";

    @Test
    public void test_getImageInfo() {

        System.out.println(ImageUtil.getImageInfo(path));
    }

    @Test
    public void test_addTextMark() throws Exception {

        ImageUtil.addTextWatermark(path, targetpath, "bbbbb");
    }

    @Test
    public void test_addImgMark() throws Exception {

        ImageUtil.addImgWatermark(path, targetpath, 100);
    }

    @Test
    public void test_resize() throws Exception {

         ImageUtil.resize(path, targetpath, 1024, 1000, 90d, false);
        //ImageUtil.jdkResize(path, 0.3f);
    }

    @Test
    public void test_removeProfile() throws Exception {

        ImageUtil.removeProfile(path, targetpath);
    }
    @Test
    public void test_colorspaceGray() throws Exception {
    	
    	ImageUtil.colorspaceGray(path, targetpath);
    }
    @Test
    public void test_monochrome() throws Exception {
    	
    	ImageUtil.monochrome(path, targetpath);
    }

    @Test
    public void test_scaleResizeImage() throws Exception {

        ImageUtil.scaleResize(path, targetpath, 256, null);
    }

    @Test
    public void test_cropImage() throws Exception {

        ImageUtil.crop(path, targetpath, 0, 100, 400, 400);
    }

    @Test
    public void test_subsectionImage() throws Exception {

        List<String> list = ImageUtil.subsection(path, targetpath, 200, 200);
        System.out.println(list);
    }

    @Test
    public void test_rotate() throws Exception {

        ImageUtil.rotate(path, targetpath, 90d);
    }

    @Test
    public void test_img() throws Exception {
        //读取文件夹里面的图片
        String fileName = "picture";
        BufferedImage img = ImageIO.read(new File("/Users/lizhenmin/tmp/img/test.jpg"));
        //获取图片的高宽
        int width = img.getWidth();
        int height = img.getHeight();
        
        //循环执行除去干扰像素
        for(int i = 1;i < width;i++){
            Color colorFirst = new Color(img.getRGB(i, 1));
            int numFirstGet = colorFirst.getRed()+colorFirst.getGreen()+colorFirst.getBlue();
            for (int x = 0; x < width; x++)
            {
                for (int y = 0; y < height; y++)
                {
                    Color color = new Color(img.getRGB(x, y));
                    System.out.println("red:"+color.getRed()+" | green:"+color.getGreen()+" | blue:"+color.getBlue());
                    int num = color.getRed()+color.getGreen()+color.getBlue();
                    if(num >= numFirstGet){
                        img.setRGB(x, y, Color.WHITE.getRGB());
                    }
                }
            }
        }
        
        //图片背景变黑色
        for(int i = 1;i<width;i++){
            Color color1 = new Color(img.getRGB(i, 1));
            int num1 = color1.getRed()+color1.getGreen()+color1.getBlue();
            for (int x = 0; x < width; x++)
            {
                for (int y = 0; y < height; y++)
                {
                    Color color = new Color(img.getRGB(x, y));
                    System.out.println("red:"+color.getRed()+" | green:"+color.getGreen()+" | blue:"+color.getBlue());
                    int num = color.getRed()+color.getGreen()+color.getBlue();
                    if(num==num1){
                        img.setRGB(x, y, Color.BLACK.getRGB());
                    }else{
                        img.setRGB(x, y, Color.WHITE.getRGB());
                    }
                }
            }
        }
        //保存图片
        File file = new File("/Users/lizhenmin/tmp/img/test2.jpg");
        if (!file.exists())
        {
            File dir = file.getParentFile();
            if (!dir.exists())
            {
            dir.mkdirs();
            }
            try
            {
            file.createNewFile();
            }
            catch (IOException e)
            {
            e.printStackTrace();
            }
        }
        ImageIO.write(img, "jpg", file);
    }
}