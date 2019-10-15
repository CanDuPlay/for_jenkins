package com.zklcsoftware.common.web.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Base64Utils;

public class Base64Util {
	
	/**
	 * 根据图片地址转换为base64编码字符串
	 * @param url
	 * @return
	 */
	public static String getImageStr(String url) {
		//判断url类型
		if(!StringUtils.isBlank(url)){
			try {
				if(url.contains("http://") || url.contains("https://")){
					//网络图片暂时不做处理
					return url;
//					//下载网络文件
//		    		URL curl = new URL(url);    
//		    	    HttpURLConnection conn = (HttpURLConnection)curl.openConnection();    
//		    	    //设置超时间为3秒  
//		    	    conn.setConnectTimeout(3*1000);  
//		    	    //防止屏蔽程序抓取而返回403错误  
//		    	    conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");  
//	    	        //得到输入流  
//	    	        InputStream in = conn.getInputStream();    
//	    	        //获取自己数组  
//	    	        byte[] getData = readInputStream(in);
//	    	        in.read(getData);
//	    	        in.close();
//	    	        // 加密
//				    return "data:image/jpeg;base64,"+Base64Utils.encodeToString(getData);
				}else{
					InputStream inputStream = null;
					byte[] data = null;
			        inputStream = new FileInputStream(url);
			        data = new byte[inputStream.available()];
			        inputStream.read(data);
			        inputStream.close();
			        // 加密
				    return "data:image/jpeg;base64,"+Base64Utils.encodeToString(data);
				}
			} catch (IOException e) {
		        return null;
		    }
		}else{
			return null;
		}
	}
	
	/**
     * 从输入流中获取字节数组
     * @param inputStream
     * @return
     * @throws IOException
     */
    private static  byte[] readInputStream(InputStream inputStream) throws IOException {  
        byte[] buffer = new byte[1024];  
        int len = 0;  
        ByteArrayOutputStream bos = new ByteArrayOutputStream();  
        while((len = inputStream.read(buffer)) != -1) {  
            bos.write(buffer, 0, len);  
        }  
        bos.close();  
        return bos.toByteArray();  
    }  
	
/*	public static void main(String[] args) {
	    String strImg = getImageStr("http://img1.3lian.com/2015/w7/98/d/22.jpg");
	    System.out.println(strImg);
	}*/
	
}
