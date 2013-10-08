package cn.fm.web.action.company;

import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import javax.annotation.Resource;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.struts2.ServletActionContext;

import cn.fm.service.salary.CreateSalaryBudgetTableService;
import cn.fm.web.action.BaseAction;

@SuppressWarnings("serial")
public class ExportSalaryBudgetByPoiAction extends BaseAction{
	
	@Resource
	private CreateSalaryBudgetTableService   createSalaryBudgetTableService;
	
	
	
	
	 private static String fileName = "工资预算表.xls";
	
	
	
	
	
	  public String getFileName() {  
	        return fileName;  
	    }  
	      
	      
	      
	    public void setFileName(String fileName) {  
	        this.fileName = fileName;  
	    }  
	
	
	
	    public InputStream getDownloadFile()  
	    {  
	    	
	    	//return createSalaryBudgetTableService.getInputStream();
	    	return null;
		} 

	    public String downloadSalaryBudgetTable()
		{
	    	this.setFileName("员工基本信息表.xls");
	    	try {
				fileName = new String(fileName.getBytes(), "iso8859-1");//解决中文 文件名问题
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			header();
			return SUCCESS;
		}
		
	    
	    
	    
	    /**
	     * 表头
	     */
	    public static void header()
	    {
	    	HSSFWorkbook workbook = new HSSFWorkbook();
	 	    HSSFSheet sheet = workbook.createSheet(fileName);
	 	    sheet.setDefaultColumnWidth(10);
	 	    HSSFRow zeroRow = sheet.createRow(0);
	 	    zeroRow.setHeight((short)300);

		    // 表头
		    for (int i= 0; i < 10; i++)
		    {
		        HSSFCell cell = zeroRow.createCell(i);
		        cell.setCellValue("字段" + i);
		        // 设置表头样式
		        setHeaderStyle(workbook, cell);
		    }

		    // 内容
		    for (int i =1; i <= 30; i++) {
		        HSSFRow row = sheet.createRow(i);
		        for (int j = 0; j < 10; j++) {
		            HSSFCell cell = row.createCell(j);
		            cell.setCellValue("张三" + i + j);
		             
		            // 设置内容区样式
		            setContentStyle(workbook, cell);
		        }
		    }
		    // 输出文件
		     exprotExcel(workbook, null);
		}
	    
		/**
		 * 设置表头样式
		 * @param workbook
		 * @param cell
		 */
		public static void setHeaderStyle(HSSFWorkbook workbook, HSSFCell cell) 
		{
		    HSSFFont font = workbook.createFont();
		    font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		    font.setFontName("宋体");
		    font.setFontHeight((short) 220);
		    CellStyle cs = workbook.createCellStyle();
		    cs.setAlignment(CellStyle.ALIGN_CENTER);
		    cs.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		    cs.setFillBackgroundColor(HSSFColor.GREY_25_PERCENT.index);
		    cs.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
		    cs.setFillPattern(CellStyle.SOLID_FOREGROUND);
		    cs.setFont(font);
		    cs.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		    cs.setBorderRight(HSSFCellStyle.BORDER_THIN);
		    cs.setBorderTop(HSSFCellStyle.BORDER_THIN);
		    cs.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		    cs.setWrapText(true);
		    cell.setCellStyle(cs);
		}

		/**
		 * 设置内容区样式
		 * @param workbook
		 * @param cell
		 */
		
		
		public  static void setContentStyle(HSSFWorkbook workbook,HSSFCell cell)
		{
		    HSSFFont cellFont = workbook.createFont();
		    cellFont.setFontName("宋体");
		    cellFont.setFontHeight((short) 210);
		    CellStyle cellStyle = workbook.createCellStyle();
		    cellStyle.setAlignment(CellStyle.ALIGN_LEFT);
		    cellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		    cellStyle.setFont(cellFont);
		    cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		    cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
		    cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
		    cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		    cellStyle.setWrapText(true);
		    cell.setCellStyle(cellStyle);
		}

		/**
		 * 输出文件
		 * @param workbook
		 * @param fileName
		 */
		public static void exprotExcel(HSSFWorkbook workbook, String fileName)
		{
			
			FileOutputStream outputStream = null;
		    fileName = fileName==null ? fileName : workbook.getSheetName(0);
		    try {
		        outputStream = new FileOutputStream("D:\\" + fileName + ".xls");
		        workbook.write(outputStream);
		        outputStream.flush();
		        outputStream.close();
		    } catch (FileNotFoundException e) {
		        e.printStackTrace();
		    } catch (IOException e) {
		        e.printStackTrace();
		    } finally {
		        try {
		            if (outputStream != null) {
		                outputStream.close();
		            }
		        } catch (Exception e2) {
		            e2.printStackTrace();
		        }
		    }
		}

	
}
