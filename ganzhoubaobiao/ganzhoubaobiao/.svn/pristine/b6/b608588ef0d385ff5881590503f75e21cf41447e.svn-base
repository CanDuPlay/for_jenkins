package com.zklcsoftware.basic.util;

import java.io.File;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import com.zklcsoftware.common.web.controller.MyException;

public class StringUtil {

	public static double pers = 1048576; //1024*1024

    public static String returnPOColName(String dbColName){
        
        String [] strs=dbColName.split("_");
        String poColName="";
        for(int i=0;i<strs.length;i++){
            poColName =poColName+(strs[i].replaceFirst(strs[i].charAt(0) + "",
                    new String(strs[i].charAt(0) + "").toUpperCase()));
        }
        return poColName;
        
    }
    
    /**
     * 转换sql参数   aaa,bbbb,bbb==>  "aaa","bbb","ccc"
     */
    public static String replaceString(String param){
        StringBuilder sb = new StringBuilder();
        String para[] =param.split(",");
        for(int i =0;i<para.length;i++){
            sb.append("'"+para[i]+"',");
        }
        String aa = sb.toString();
        return aa.substring(0, aa.lastIndexOf(","));
    }
    
    /**
     * 转换sql参数   aaa,bbbb,bbb==>  "aaa","bbb","ccc"
     */
    public static String replaceString(List param){
        StringBuilder sb = new StringBuilder();
        
        for(int i =0;i<param.size();i++){
            sb.append("'"+param.get(i).toString()+"',");
        }
        String aa = sb.toString();
        if(!"".equals(aa)){
            return aa.substring(0, aa.lastIndexOf(","));
        }else{
            return "";
        }
        
    }
    
    /**
     * 转换科学计数法
     * @param obj
     * @return
     */
    public static String convertBig(String obj){
    	BigDecimal db = new BigDecimal(obj);
    	String ii = db.toPlainString();
    	
    	return ii;
    }
    
   /**
     * 
     * <p>
     * MB，KB转字节功能
     * </p>
     * @author sunjian 时间 2018年12月14日 上午9:38:48
     * @param str
     * @return
     */
    public static long sizeFormat(String str){
        long size = 0;
        if(StringUtils.isNotBlank(str)){
            if(str.toUpperCase().endsWith("KB")){
                size = (long)(Double.parseDouble(str.substring(0,str.length()-2))*1024);
            }else if(str.toUpperCase().endsWith("MB")){
                size = (long)(Double.parseDouble(str.substring(0,str.length()-2))*pers);
            }else if(str.toUpperCase().endsWith("M")){
                size = (long)(Double.parseDouble(str.substring(0,str.length()-2))*pers);
            }else if(str.toUpperCase().endsWith("GB")){
                size = (long)(Double.parseDouble(str.substring(0,str.length()-2))*1024*pers);
            }else if(str.toUpperCase().endsWith("G")){
                size = (long)(Double.parseDouble(str.substring(0,str.length()-2))*1024*pers);
            }
        }
        return size;
    }
    
    /**
     * 
     * <p>
     * 判断是否为允许的上传文件类型,true表示允许
     * </p>
     * @author sunjian 时间 2018年12月14日 上午9:39:05
     * @param fileName 文件名称
     * @param suffixs 限制的类型字符
     * @return
     */
    public static boolean checkFileType(String fileName, String suffixs) {
        // 转换类型为List
        List<String> suffixList = Arrays.asList(suffixs.split(","));
        for(String str : suffixList)
        {
            if(StringUtils.isNotBlank(str)){
                if (fileName.trim().toLowerCase().endsWith(str.toLowerCase())) {
                    return true;
                }
            }
        }
        return false;
    }
    
    /**
     * 
     * <p>
     * 创建文件路径功能
     * </p>
     * @author sunjian 时间 2018年12月14日 上午10:21:01
     * @param path
     * @return
     * @throws MyException 
     */
    public static void createDirectory(String path) throws MyException {
        try {
            File wf = new File(path);
            if (!wf.exists()) {
                wf.mkdirs();
            }
        } catch (Exception e) {
            throw new MyException("系统找不到指定的文件！");
        }
    }
}
