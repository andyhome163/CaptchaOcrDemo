package com.mljr.ocr;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;

import com.asprise.ocr.Ocr;
import com.mljr.ocr.util.ImageUtil;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import net.sourceforge.tess4j.util.ImageHelper;  
  
  
public class CaptchaOcrDemo{  
    public static void main(String[] args) throws IOException {  
        HttpClient httpClient = HttpClients.createDefault(); 
        HttpGet httpGet = new HttpGet("https://wode.homecredit.cn/CustomerService/getCheckCode");  
//      GetMethod getMethod = new GetMethod("https://dynamic.12306.cn/otsweb/passCodeAction.do?rand=sjrand");  
        HttpResponse response = httpClient.execute(httpGet);
        if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {  
            System.err.println("Method failed: " + response.getStatusLine());  
            return ;  
        }  
        String picName = "/Users/lizhenmin/tmp/img/";  
        File filepic=new File(picName);  
        if(!filepic.exists())  
            filepic.mkdir();  
       // File filepicF=new File(picName+new Date().getTime() + ".jpg");  
        File filepicF=new File(picName+"test.jpg");  
        InputStream inputStream = response.getEntity().getContent();  
        OutputStream outStream = new FileOutputStream(filepicF);  
        IOUtils.copy(inputStream, outStream);  
        outStream.close();  

    	File test1 = new File( "/Users/lizhenmin/tmp/img/test1.jpg");
    	File test2 = new File( "/Users/lizhenmin/tmp/img/test2.jpg");
    	File test3 = new File( "/Users/lizhenmin/tmp/img/test3.jpg");
    	File test4 = new File( "/Users/lizhenmin/tmp/img/test4.jpg");
        
        try {
        	
        	//ImageUtil.imgToBlackWhite(filepicF, test1);
        	ImageUtil.crop(filepicF.getAbsolutePath(), test1.getAbsolutePath(), 10, 3, 20, 25);
        	ImageUtil.crop(filepicF.getAbsolutePath(), test2.getAbsolutePath(), 30, 3, 20, 25);
        	ImageUtil.crop(filepicF.getAbsolutePath(), test3.getAbsolutePath(), 50, 3, 20, 25);
        	ImageUtil.crop(filepicF.getAbsolutePath(), test4.getAbsolutePath(), 70, 3, 20, 25);
        	//ImageUtil.resize(filepicF.getAbsolutePath(), test1.getAbsolutePath(), 65, 30, 100d, false);//
        	//ImageUtil.scaleResize(filepicF.getAbsolutePath(), filepicF.getAbsolutePath(), 200, 60);
        	//灰度处理提高识别率
			ImageUtil.colorspaceGray(test1.getAbsolutePath(), "/Users/lizhenmin/tmp/img/test1.jpg");
			ImageUtil.colorspaceGray(test2.getAbsolutePath(), "/Users/lizhenmin/tmp/img/test2.jpg");
			ImageUtil.colorspaceGray(test3.getAbsolutePath(), "/Users/lizhenmin/tmp/img/test3.jpg");
			ImageUtil.colorspaceGray(test4.getAbsolutePath(), "/Users/lizhenmin/tmp/img/test4.jpg");
        	//ImageUtil.monochrome("/Users/lizhenmin/tmp/img/test1.jpg", "/Users/lizhenmin/tmp/img/test1.jpg");
        	//FileUtils.copyFile(filepicF,new File( "/Users/lizhenmin/tmp/img/test1.jpg"));
			
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        
       
        
        
        Tesseract tessreact = new Tesseract();  
        tessreact.setDatapath("/Users/lizhenmin/work/tessdata");  
        try {  
        	
        	// 这里对图片黑白处理,增强识别率.这里先通过截图,截取图片中需要识别的部分  
        //	BufferedImage textImage = ImageHelper.convertImageToGrayscale(ImageHelper.getSubImage(testImg, 0, 0, 100, 30));  
        	// 图片锐化,自己使用中影响识别率的主要因素是针式打印机字迹不连贯,所以锐化反而降低识别率  
        	// textImage = ImageHelper.convertImageToBinary(textImage);  
        	// 图片放大5倍,增强识别率(很多图片本身无法识别,放大5倍时就可以轻易识,但是考滤到客户电脑配置低,针式打印机打印不连贯的问题,这里就放大5倍)  
        	//textImage = ImageHelper.getScaledInstance(textImage, endX * 5, endY * 5);  
        	
            String result1 = tessreact.doOCR(test1).replaceAll("\\n", "");  
            String result2 = tessreact.doOCR(test2).replaceAll("\\n", ""); 
            String result3 = tessreact.doOCR(test3).replaceAll("\\n", ""); 
            String result4 = tessreact.doOCR(test4).replaceAll("\\n", ""); 
            System.out.println(result1+result2+result3+result4);  
        } catch (TesseractException e) {  
            System.err.println(e.getMessage());  
        }  
        
        
       // aspriseOcr(testImg);
    } 
    
 static void   aspriseOcr(File imageFile) {
    	
    	Ocr.setUp(); // one time setup
    	Ocr ocr = new Ocr(); // create a new OCR engine
    	ocr.startEngine("eng", Ocr.SPEED_FASTEST); // English
    	String s = ocr.recognize(new File[] {imageFile},
    			Ocr.RECOGNIZE_TYPE_TEXT, Ocr.OUTPUT_FORMAT_PLAINTEXT); // PLAINTEXT | XML | PDF | RTF
    	System.out.println("Result: " + s);
    	ocr.stopEngine();
    	
    }
  
}  


