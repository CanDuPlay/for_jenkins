package com.zklcsoftware.common.web.util;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.zklcsoftware.common.web.controller.MyException;
import com.zklcsoftware.ganzhoubaobiao.dto.PreviewBodyDTO;
import com.zklcsoftware.ganzhoubaobiao.dto.PreviewHeaderDTO;
import com.zklcsoftware.ganzhoubaobiao.dto.PreviewTableDTO;
import com.zklcsoftware.ganzhoubaobiao.dto.PreviewTdDTO;

public class ReadExcel {
    //总行数
    private int totalRows = 0;  
    //总条数
    private int totalCells = 0; 
    //错误信息接收器
    private String errorMsg;
    //构造方法
    public ReadExcel(){}
    //获取总行数
    public int getTotalRows()  { return totalRows;} 
    //获取总列数
    public int getTotalCells() {  return totalCells;} 
    //获取错误信息
    public String getErrorInfo() { return errorMsg; }  
  
    // @描述：是否是2003的excel，返回true是2003 
    public static boolean isExcel2003(String filePath)  {  
         return filePath.matches("^.+\\.(?i)(xls)$");  
     }  
   
    //@描述：是否是2007的excel，返回true是2007 
    public static boolean isExcel2007(String filePath)  {  
         return filePath.matches("^.+\\.(?i)(xlsx)$");  
    } 
  /**
   * 验证EXCEL文件
   * @param filePath
   * @return
   */
  public boolean validateExcel(String filePath){
        if (filePath == null || !(isExcel2003(filePath) || isExcel2007(filePath))){  
            errorMsg = "文件名不是excel格式";  
            return false;  
        }  
        return true;
  }
    
  /**
   * 读EXCEL文件，获取客户信息集合
   * @param fielName
   * @return
 * @throws MyException 
   */
  public PreviewTableDTO getExcelInfo(String fileName, String admin) throws MyException{
      
       //新建一个文件
       File file = new File(fileName); 
       
       PreviewTableDTO dto = null;
       //初始化输入流
       InputStream is = null;  
       try{
          //验证文件名是否合格
          if(!validateExcel(fileName)){
              return null;
          }
          //根据文件名判断文件是2003版本还是2007版本
          boolean isExcel2003 = true; 
          if(isExcel2007(fileName)){
              isExcel2003 = false;  
          }
          //根据新建的文件实例化输入流
          is = new FileInputStream(file);
          //根据excel里面的内容读取客户信息
          dto = getExcelInfo(is, isExcel2003, admin); 
          is.close();
      }catch(Exception e){
          e.printStackTrace();
          throw new MyException("模板不符合要求，请重新上传！");
      } finally{
          if(is !=null)
          {
              try{
                  is.close();
              }catch(IOException e){
                  is = null;    
                  e.printStackTrace();  
              }
          }
      }
      return dto;
  }
  /**
   * 根据excel里面的内容读取客户信息
   * @param is 输入流
   * @param isExcel2003 excel是2003还是2007版本
   * @return
 * @throws Exception 
   * @throws IOException
   */
  public PreviewTableDTO getExcelInfo(InputStream is,boolean isExcel2003,String admin) throws MyException{
	  PreviewTableDTO dto = null;
       try{
    	   byte[] byteArray = org.apache.commons.io.IOUtils.toByteArray(is);
           ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArray);
           /** 根据版本选择创建Workbook的方式 */
           Workbook wb = null;
           //当excel是2003时
           if(isExcel2003){
               wb = new HSSFWorkbook(byteArrayInputStream); 
           }
           else{//当excel是2007时
               wb = new XSSFWorkbook(byteArrayInputStream); 
           }
           //读取Excel里面客户的信息
           dto = readExcelValue(wb, admin);
       }
       catch (IOException e)  {  
           e.printStackTrace();  
       }  
       return dto;
  }
  /**
   * 读取Excel里面客户的信息
   * @param wb
   * @return
 * @throws Exception 
   */
  private PreviewTableDTO readExcelValue(Workbook wb, String admin) throws MyException{ 
      //得到第一个shell  
       Sheet sheet=wb.getSheetAt(0);
       
      //得到Excel的行数
       this.totalRows = sheet.getPhysicalNumberOfRows();
       if(totalRows < 1) {
    	   throw new MyException("模板不符合要求，请重新上传！");
       }
//       Row row3 = sheet.getRow(2);
//       if(null != row3) {
//    	   throw new MyException("模板不符合要求，请重新上传！");
//       }
      //得到Excel的列数(前提是有行数)
       if(totalRows >= 1 && sheet.getRow(0) != null){
         this.totalCells=sheet.getRow(0).getPhysicalNumberOfCells();
       }
       StringBuffer td = new StringBuffer();
       List<PreviewTdDTO> headerDto = new ArrayList<>();
       PreviewHeaderDTO previewHeaderDTO = new PreviewHeaderDTO();
       for(int r = 0;r < 1; r++){
           Row row = sheet.getRow(r);
           if (row == null) continue;
           td.append("<tr>");
           //循环Excel的列
           for(int c = 0; c < this.totalCells; c++){
               Cell cell = row.getCell(c);
               if (null != cell){
            	   PreviewTdDTO previewTdDTO = new PreviewTdDTO();
            	   if(cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
            		   previewTdDTO.setLabel(String.valueOf(cell.getNumericCellValue()));
            	   }else{
            		   previewTdDTO.setLabel(cell.toString());
            	   }
            	   headerDto.add(previewTdDTO);
            	   td.append("<td class='table-column-"+ c +"'>"+cell+"</td>");
               }
           }
           previewHeaderDTO.setHeader(headerDto);
           td.append("</tr>");
       }
       
       //处理行数据
       PreviewBodyDTO previewBodyDTO = null;
       List<PreviewBodyDTO> bodyList = new ArrayList<>();
       for(int r = 1;r < totalRows; r++){
    	   List<PreviewTdDTO> rowsDto = new ArrayList<>();
    	   previewBodyDTO = new PreviewBodyDTO();
           Row row = sheet.getRow(r);
           if (row == null) continue;
           td.append("<tr>");
           //循环Excel的列
           for(int c = 0; c < this.totalCells; c++){
               Cell cell = row.getCell(c);
               if (null != cell){
            	   td.append("<td class='table-column-"+ c +"'>"+cell+"</td>");
            	   PreviewTdDTO previewTdDTO = new PreviewTdDTO();
            	   if(cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
            		   previewTdDTO.setLabel(formatNumber(String.valueOf(cell.getNumericCellValue())));
            	   }else{
            		   previewTdDTO.setLabel(cell.toString());
            	   }
            	   rowsDto.add(previewTdDTO);
               }
           }
           td.append("</tr>");
           
           Row row_ = sheet.getRow(1);
           for(int i = 0; i < this.totalCells; i++){
        	   Cell cell = row_.getCell(i);
        	   if(null != cell && cell.getCellType() == Cell.CELL_TYPE_NUMERIC
        			   && formatNumber(String.valueOf(cell.getNumericCellValue())).equals("1")) {
        		   rowsDto.get(i).setIsNumber("1");
        	   }
           }
           previewBodyDTO.setBody(rowsDto);
           bodyList.add(previewBodyDTO);
       }
       if(!StringUtils.isEmpty(admin) && admin.equals("0")) {
    	   bodyList.remove(0);
       }
       PreviewTableDTO previewTableDTO = new PreviewTableDTO();
       previewTableDTO.setHeader(previewHeaderDTO);
       previewTableDTO.setBody(bodyList);
       previewTableDTO.setSumColums(totalCells);
       return previewTableDTO;
  }

  private static String formatNumber(String strValue) {
	  if (strValue.endsWith(".0")) {
          return strValue.substring(0, strValue.indexOf(".0"));
      } else {
          return strValue;
      }
  }
}