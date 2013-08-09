//package cn.fm.service.uploadfile.impl;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.InputStream;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//
//import javax.servlet.ServletContext;
//import org.apache.struts2.ServletActionContext;
//import org.aspectj.util.FileUtil;
//
//import cn.fm.bean.uploadfile.ExcelUploadFile;
//import cn.fm.bean.user.Buyer;
//import cn.fm.service.base.DaoSupport;
//import cn.fm.service.uploadfile.ExcelUploadFileService;
//
//
//public class ExcelUploadFileImpl extends DaoSupport<ExcelUploadFile> implements ExcelUploadFileService{
//	
//	
//	public void getExcelFileData(String upload,String uploadFileName)
//	{
//		String date = new SimpleDateFormat("yyyyMMddHHmmssSS").format(new Date());  
//	    ServletContext aplication = ServletActionContext.getServletContext();  
//	    String path = aplication.getRealPath("/excel");  
//	    System.out.println(path);  
//	    
//	    
//	    FileUtil.copyFileToDir(path, upload, date+"_"+uploadFileName);  
//	      
//	    try {  
//	        InputStream is = new FileInputStream(new File(path,date+"_"+uploadFileName));  
//	        try {  
//	            ExcelUploadFile book;  
//	        Sheet seet= book.getSheet(0);  
//	        List<ExcelUploadFile> lists = new ArrayList();  
//	        ExcelUploadFile excelUploadFile=null;  
//	        for(int i=1;i<seet.getRows();i++)  
//	        {  
//	        	excelUploadFile =new ExcelUploadFile();  
//	        	excelUploadFile.setExpid(seet.getCell(0,i).getContents());  
//	        	excelUploadFile.setExptitle(seet.getCell(1,i).getContents());  
//	        	excelUploadFile.setMalfuncarea(seet.getCell(2,i).getContents());  
//	        	excelUploadFile.setSpecialtype(seet.getCell(3,i).getContents());  
//	        	excelUploadFile.setDevicetype(seet.getCell(4,i).getContents());  
//	        	excelUploadFile.setDevicecompany(seet.getCell(5,i).getContents());  
//	        	excelUploadFile.setVersion(seet.getCell(6,i).getContents());  
//	        	excelUploadFile.setExplevel(seet.getCell(7,i).getContents());  
//	        	excelUploadFile.setExpkeyword(seet.getCell(8,i).getContents());  
//	        	excelUploadFile.setMalfuncdescription(seet.getCell(9,i).getContents());  
//	        	excelUploadFile.setExperiencesummary(seet.getCell(10,i).getContents());  
//	                  
//	                lists.add(excelUploadFile);  
//	        }  
//	        alarmRmsJkmalfuncexperienceManager.saveOrUpdateAll(lists);  
//	          
//	        } catch (BiffException e) {  
//	            // TODO Auto-generated catch block  
//	            e.printStackTrace();  
//	        } catch (IOException e) {  
//	            // TODO Auto-generated catch block  
//	            e.printStackTrace();  
//	        }  
//	    } catch (FileNotFoundException e) {  
//	        // TODO Auto-generated catch block  
//	        e.printStackTrace();  
//	    }  
//	}
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//}
