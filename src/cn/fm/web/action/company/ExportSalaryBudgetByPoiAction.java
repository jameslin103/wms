package cn.fm.web.action.company;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import cn.fm.bean.company.CustomBonus;
import cn.fm.bean.salary.SalaryTemplate;
import cn.fm.service.company.CustomBonusServices;
import cn.fm.service.salary.CreateSalaryBudgetTableService;
import cn.fm.service.salary.SalaryTemplateService;
import cn.fm.web.action.BaseAction;

@SuppressWarnings("serial")
public class ExportSalaryBudgetByPoiAction extends BaseAction{
	
	@Resource
	private CreateSalaryBudgetTableService   createSalaryBudgetTableService;
	@Resource 
	private CustomBonusServices 			customBonusServices;
	@Resource
	private SalaryTemplateService			salaryTemplateService;
	
	
	 private  String fileName="员工基本工资信息表.xls";
	 
	 private  InputStream excelFile;
	
	 private Integer  templateId;
	
	
	 
	 
	 	
	  public InputStream getExcelFile() {
		return excelFile;
	 }

	  

	public Integer getTemplateId() {
		return templateId;
	}



	public void setTemplateId(Integer templateId) {
		this.templateId = templateId;
	}



	public void setSalaryTemplateService(SalaryTemplateService salaryTemplateService)
	{
		 this.salaryTemplateService = salaryTemplateService;
    }

	public void setCreateSalaryBudgetTableService(
			CreateSalaryBudgetTableService createSalaryBudgetTableService) {
		this.createSalaryBudgetTableService = createSalaryBudgetTableService;
	}

	public void setCustomBonusServices(CustomBonusServices customBonusServices) {
		this.customBonusServices = customBonusServices;
	}



		public String getFileName() 
		{  
		        return fileName;  
		}  
 
	      
	   public void setFileName(String fileName)
	   {  
	    	this.fileName=fileName;
	   }  

	   //下载模板
	    public String downloadSalaryBudgetTable()
	    {
	    	try {
				fileName = new String(fileName.getBytes(),"ISO8859-1");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			} 
	    	header();
	        return SUCCESS;  
	    }  
	    
	    
	    /**
	     * 表头
	     */
	    public  void header()
	    {
	    	HSSFWorkbook workbook = new HSSFWorkbook();
	 	    HSSFSheet sheet = workbook.createSheet("员工基本工资信息表");
	 	    sheet.setDefaultColumnWidth(10);
	 	    HSSFRow zeroRow = sheet.createRow(0);
	 	    zeroRow.setHeight((short)500);
	 	    
	 	    //封装标题
	 	   List<String> header=new ArrayList<String>();
		 	  header.add("序号");
		 	  header.add("姓名");
		 	  header.add("基本工资");
			  header.add("身份证号码");
			  header.add("备注");
		 	  SalaryTemplate salaryTemplate=salaryTemplateService.find(templateId);
		 	  String[] customt=salaryTemplate.getSubsidyList().split(",");
		 	  int count=header.size()+customt.length;
		 	  request.getSession().setAttribute("count", count);
	 	   for (int i = 0; i <customt.length; i++)
	 	   {
	 		  CustomBonus customBonus=customBonusServices.find(Integer.parseInt(customt[i].trim()));
	 		  header.add(customBonus.getBonusName());
	 		  
	 	   }
	 	  
		    // 表头
		    for (int i=0; i <header.size(); i++)
		    {
		        HSSFCell cell = zeroRow.createCell(i);
		        cell.setCellValue(header.get(i));
		        // 设置表头样式
		        setHeaderStyle(workbook, cell);
		    }

//		    // 内容
//		    for (int i =1; i <= 30; i++) {
//		        HSSFRow row = sheet.createRow(i);
//		        for (int j = 0; j < 10; j++) {
//		            HSSFCell cell = row.createCell(j);
//		            cell.setCellValue("张三" + i + j);
//		             
//		            // 设置内容区样式
//		            setContentStyle(workbook, cell);
//		        }
//		    }
		    // 输出文件
		     exprotExcel(workbook, fileName);
		}
	    
		/**
		 * 设置表头样式
		 * @param workbook
		 * @param cell
		 */
		public void setHeaderStyle(HSSFWorkbook workbook, HSSFCell cell) 
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
		
		
		public  void setContentStyle(HSSFWorkbook workbook,HSSFCell cell)
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
		public void exprotExcel(HSSFWorkbook workbook, String fileName)
		{
				ByteArrayOutputStream baos=null;
			    try {
			    	baos = new ByteArrayOutputStream();
			        workbook.write(baos);
			        excelFile =new ByteArrayInputStream(baos.toByteArray());
			        baos.flush();
			    } catch (FileNotFoundException e) {
			        e.printStackTrace();
			    } catch (IOException e) {
			        e.printStackTrace();
			    } finally {
			        try {
			            if (baos != null) {
			            	baos.close();
			            }
			        } catch (Exception e2) {
			            e2.printStackTrace();
			        }
			    }
			 
		}
}
