package cn.fm.web.action.company;

import java.io.File;
import java.io.FileInputStream;  
import java.io.IOException;  
import java.io.InputStream;  
import java.util.ArrayList;  
import java.util.Iterator;  
import java.util.List;  
  
import org.apache.poi.hssf.usermodel.HSSFWorkbook;  
import org.apache.poi.ss.usermodel.Cell;  
import org.apache.poi.ss.usermodel.Row;  
import org.apache.poi.ss.usermodel.Sheet;  
import org.apache.poi.ss.usermodel.Workbook;  
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import cn.fm.bean.uploadfile.ExcelWorkSheet;
import cn.fm.bean.uploadfile.UserInfo;


import cn.fm.web.action.BaseAction;

public class UserAction extends BaseAction{


	     
	    private File excelFile; //上传的文件  
	      
	    private String excelFileFileName; //保存原始文件名  
	      
	    //将Excel文件解析完毕后信息存放到这个对象中  
	    private ExcelWorkSheet<UserInfo> excelWorkSheet;  
	      
	    public File getExcelFile() { 
	    	
	        return excelFile;  
	    }  
	  
	    public void setExcelFile(File excelFile) {  
	        this.excelFile = excelFile;  
	    }  
	  
	    public String getExcelFileFileName() {  
	        return excelFileFileName;  
	    }  
	  
	    public void setExcelFileFileName(String excelFileFileName) {  
	        this.excelFileFileName = excelFileFileName;  
	    }  
	  
	    public ExcelWorkSheet<UserInfo> getExcelWorkSheet() {  
	        return excelWorkSheet;  
	    }  
	  
	    public void setExcelWorkSheet(ExcelWorkSheet<UserInfo> excelWorkSheet) {  
	        this.excelWorkSheet = excelWorkSheet;  
	    }  
	  
	      
	      
	    //判断文件类型  
	    public Workbook createWorkBook(InputStream is) throws IOException{  
	        if(excelFileFileName.toLowerCase().endsWith("xls")){  
	            return new HSSFWorkbook(is);  
	        }  
	        if(excelFileFileName.toLowerCase().endsWith("xlsx")){  
	            return new XSSFWorkbook(is);  
	        }  
	        return null;  
	    }  
	      
	    public String userInfo() throws Exception{  
	    	
	    	 System.out.println("xxxxxxxxx");
	    	
	    	
	        Workbook book = createWorkBook(new FileInputStream(excelFile));  
	        //book.getNumberOfSheets();  判断Excel文件有多少个sheet  
	        Sheet sheet =  book.getSheetAt(0);    
	        excelWorkSheet = new ExcelWorkSheet<UserInfo>();  
	        //保存工作单名称  
	        Row firstRow = sheet.getRow(0);  
	        Iterator<Cell> iterator = firstRow.iterator();  
	          
	        //保存列名  
	        List<String> cellNames = new ArrayList<String>();  
	        while (iterator.hasNext()) {  
	            cellNames.add(iterator.next().getStringCellValue());  
	        }  
	        excelWorkSheet.setColumns(cellNames);  
	        for (int i = 1; i <= sheet.getLastRowNum(); i++) {  
	            Row ros = sheet.getRow(i);  
	            UserInfo user = new UserInfo();
	            user.setId((int)ros.getCell(0).getNumericCellValue());
	            user.setName(ros.getCell(1).getStringCellValue());  
	            user.setPass(ros.getCell(2).getStringCellValue());  
	            user.setLastname(ros.getCell(3).getStringCellValue());  
	            user.setAddres(ros.getCell(4).getStringCellValue());  
	            user.setRemark(ros.getCell(5).getStringCellValue());  
	            excelWorkSheet.getData().add(user);  
	        }  
	        for (int i = 0; i < excelWorkSheet.getData().size(); i++) {  
	            UserInfo info = excelWorkSheet.getData().get(i);  
	            System.out.println(info.getLastname());  
	        }  
	        return SUCCESS;  
	    }  
	      
	
}
