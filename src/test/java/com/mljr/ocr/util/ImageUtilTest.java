package com.mljr.ocr.util;
import java.util.List;

import org.junit.Test;

import com.mljr.ocr.util.ImageUtil;

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

}