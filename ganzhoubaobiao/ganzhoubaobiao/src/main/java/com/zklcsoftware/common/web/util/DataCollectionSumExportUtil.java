package com.zklcsoftware.common.web.util;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.util.CollectionUtils;

import com.zklcsoftware.common.web.controller.MyException;
import com.zklcsoftware.ganzhoubaobiao.domain.TTownSubmit;

/**
 * 数据收集汇总导出工具类
 * @author non
 *
 */
@Slf4j
public class DataCollectionSumExportUtil {

	public static void exportDataCollectSummaryData(Map<Integer, Object> map, String uploadPhotoFile, List<TTownSubmit> submitList, HttpServletResponse response, HttpServletRequest request) {
		String exportFileName = "数据收集汇总";
		if(!CollectionUtils.isEmpty(submitList)) {
			// 声明一个工作薄
			HSSFWorkbook wbook = new HSSFWorkbook();
			HSSFSheet hsheet = wbook.createSheet();
			try {
                //获取各区县上传的文件路径
                String getFilePath = "";
                boolean flag = true;
                List<String> allList = new ArrayList<>();
                List<Integer> keyList = new ArrayList<>();
                for (TTownSubmit submitData : submitList) {
                	getFilePath = uploadPhotoFile + File.separator + submitData.getFileUrl();
                	Workbook filewb = dealDataFileUrl(getFilePath);
                	//得到第一个shell  
    	            Sheet fileSheet = filewb.getSheetAt(0);
    	            Row fileRow = fileSheet.getRow(0);
    	            int fileCells = fileRow.getPhysicalNumberOfCells();
    	            while (flag) {
    	            	List<String> head = new ArrayList<>();
    	            	List<String> head1 = new ArrayList<>();
    	            	//存放表头行数据
    	            	StringBuffer headExcel = new StringBuffer(128);
    	            	//存放全市行数据
    	            	StringBuffer headExcel1 = new StringBuffer(128);
        	            headExcel.append(",");
        	            headExcel1.append("全市");
        	            for(int i = 0; i < fileCells; i++){
        	            	Cell cell = fileRow.getCell(i);
        	            	if(null != cell) {
        	            		headExcel.append(cell.toString()).append(",");
        	            	}
        	            	headExcel1.append(",");
        	            }
        	            head.add(headExcel.toString());
        	            head1.add(headExcel1.toString());
        	            allList.addAll(head);
        	            allList.addAll(head1);
        	            flag = false;
					}
    	            
    	            //区县数据
    	            List<String> rowList1 = new ArrayList<>();
	            	StringBuffer buff1 = new StringBuffer(128);
	            	//区县行
    	            buff1.append(submitData.getTownName()).append(",");
    	            for(int i = 0; i < fileCells; i++){
    	            	buff1.append(",");
    	            }
    	            rowList1.add(buff1.toString());
    	            
    	            int fileRows = fileSheet.getPhysicalNumberOfRows();
    	            keyList.add(fileRows-1);
    	            List<String> rowList = new ArrayList<>();
    	            for(int r = 1; r < fileRows; r++){
    	            	StringBuffer buff = new StringBuffer(256);
    	            	buff.append(",");
    	            	Row row1 = fileSheet.getRow(r);
    	            	if (row1 == null) continue;
    	            	for(int c = 0; c < fileCells; c++){
    	            		Cell cell = row1.getCell(c);
    	            		if(null != cell) {
    	            			buff.append(cell.toString()).append(",");
    	            		}else{
    	            			buff.append(",");
    	            		}
    	                }
    	            	rowList.add(buff.toString());
    	            }
    	            allList.addAll(rowList1);
    	            allList.addAll(rowList);
                }
                //重新拼凑 拼凑数据···
                int sign = 3;
                int ii = 0;
                String cellValue = null;
                for (int j = 0; j < allList.size(); j++) {
                	int iflag = 0;
                	HSSFRow createRow = hsheet.createRow(j);
                	for(int k = 0; k < allList.get(j).split(",").length; k++) {
                		HSSFCell createCell = createRow.createCell(k);
                		cellValue = formatNumber(String.valueOf(allList.get(j).split(",")[k]));
                		if(!StringUtils.isEmpty(cellValue) && cellValue.matches("^[0-9]*[1-9][0-9]*$")) {
                			createCell.setCellValue((int)Double.parseDouble(cellValue));
                		}else if(!StringUtils.isEmpty(cellValue) && cellValue.contains(".")){
                			createCell.setCellValue(new BigDecimal(cellValue).doubleValue());
                		}else{
                			createCell.setCellValue(cellValue);
                		}
                		
                		//处理区县合并
                		if(k > 0 && j > 0) {
                			HSSFRow row = hsheet.getRow(j-1);
                			String flag1 = (String)map.get(k);
                			if(!StringUtils.isEmpty(flag1) && flag1.equals("1")) { //需要汇总的
                				if(j == sign) {
                					Integer step = keyList.get(ii);
                					HSSFCell cell = row.createCell(k+1);
                    				String sumBuff = "SUM("+toRadix(k+1+1)+(sign+1)+":"+toRadix(k+1+1)+(sign+step)+")";
                    				cell.setCellFormula(sumBuff);
                    				if(iflag == k - keyList.get(ii)) {
                    					sign = sign + step + 1;
                    					ii++;
                    				}
                				}
                			}else{
                				iflag++;
                			}
                		}
                		
                		if(!StringUtils.isEmpty(cellValue) && cellValue.equals("全市")) {
                			
                		}
                	}
                	
				}
                List<Integer> sumFirstRow = new ArrayList<>();
                for (Integer key : map.keySet()) {
                	if(!StringUtils.isEmpty((String)map.get(key)) && ((String)map.get(key)).equals("1")) {
                		sumFirstRow.add(key+2);
                	}
				}
                List<String> strList = new ArrayList<>();
                for (Integer key : sumFirstRow) {
                	int sumOfStep = 3;
                	StringBuffer sb = new StringBuffer(128);
                	sb.append("SUM(");
                	sb.append(toRadix(key) + sumOfStep);
                	for(int i=0; i < keyList.size(); i++){
                		int keyi = keyList.get(i++).intValue();
                		sumOfStep = sumOfStep + keyi + 1;
	    				sb.append(",").append(toRadix(key)+sumOfStep);
	    			}
                	sb.append(")");
                	strList.add(sb.toString());
    			}
                log.info("》》》》》》》》全市汇总数据" + strList.toString());
    			HSSFRow twoRow = hsheet.getRow(1);
    			for (int j=0; j<sumFirstRow.size(); j++) {
    				HSSFCell cell = twoRow.createCell((sumFirstRow.get(j)-1));
    				cell.setCellFormula(strList.get(j));
    			}
                hsheet.setForceFormulaRecalculation(true);
                // 下载
    			String disposition = "";
    			if (request.getHeader("USER-AGENT").toLowerCase().indexOf("firefox") > 0 ? false : true) {
    				if (request.getHeader("USER-AGENT").toLowerCase().indexOf("safari") > 0) {
    					disposition = "attachment;filename=" + new String((exportFileName + ".xls").getBytes(), "ISO-8859-1");
    				} else {
    					disposition = "attachment;filename=" + URLEncoder.encode(exportFileName + ".xls", "UTF-8");
    				}
    			} else {
    				disposition = "attachment;filename=" + new String((exportFileName + ".xls").getBytes(), "ISO-8859-1");
    			}
    			response.setContentType("application/vnd.ms-excel;charset=UTF-8");
    			response.setHeader("Content-Disposition", disposition);
    			response.flushBuffer();
    			wbook.write(response.getOutputStream());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 0.0 => 0
	 * @param 
	 * @return
	 */
	public static String formatNumber(String strValue) {
	  if (strValue.endsWith(".0")) {
          return strValue.substring(0, strValue.indexOf(".0"));
      } else {
          return strValue;
      }
	}
	
	/**
	 * excel转列数
	 * @param num
	 * @return
	 */
	public static String toRadix(Integer num){
		String[] array = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
		int count = 26;
		String out = "";
		if(num/count != 0){
			out = array[num/count - 1];
			if(num%count == 0){
				out = out + array[num%count];
			}else{
				out = out + array[num%count - 1];
			}
		}else{
			out = array[num -1];
		}
		return out;
	}
	/**
	 * 根据文件路径读取文件内容
	 * @param filePath
	 * @return
	 * @throws MyException 
	 */
	public static Workbook dealDataFileUrl(String filePath) throws MyException {
		Workbook wb = null;
		try {
			File file = new File(filePath);
			boolean isExcel2003 = ReadExcel.isExcel2003(filePath);
			InputStream is = null;
			is = new FileInputStream(file);
			byte[] byteArray = org.apache.commons.io.IOUtils.toByteArray(is);
			ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArray);
            //当excel是2003时
            if(isExcel2003){
                wb = new HSSFWorkbook(byteArrayInputStream); 
            }
            else{//当excel是2007时
                wb = new XSSFWorkbook(byteArrayInputStream); 
            }
		} catch (Exception e) {
			throw new MyException("======》》》》》》数据收集解析文件出错");
		}
		return wb;
	}
	
}
