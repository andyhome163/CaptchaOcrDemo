package com.mljr.ocr;

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

import com.mljr.ocr.util.ImageUtil;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;  
  
  
public class CaptchaOcrDemo{  
    public static void main(String[] args) throws IOException {  
        HttpClient httpClient = HttpClients.createDefault(); 
        HttpGet httpGet = new HttpGet("http://dz.bjjtgl.gov.cn/service/checkCode.do");  
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
        
        try {
        	//灰度处理提高识别率
			ImageUtil.colorspaceGray(filepicF.getAbsolutePath(), "/Users/lizhenmin/tmp/img/test1.jpg");
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        
        File testImg=new File(picName+ "test1.jpg"); 
        
        
        
        Tesseract tessreact = new Tesseract();  
        tessreact.setDatapath("/Users/lizhenmin/work/tessdata");  
        try {  
            String result = tessreact.doOCR(testImg);  
            System.out.println(result);  
        } catch (TesseractException e) {  
            System.err.println(e.getMessage());  
        }  
    }  
}  


