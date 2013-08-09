package cn.fm.web.action.company;

import java.io.FileInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;


import org.apache.struts2.ServletActionContext;

import jxl.Workbook;

import cn.fm.utils.ExcelUtils;
import cn.fm.web.action.BaseAction;

public class ExcelUploadAction extends BaseAction
{
	   private String fileName;        //文件名  
	    private String tempPath;      //临时文件目录  
	  
	    public String download(){  
	         initExcel();  
	        return "excel";  
	    }  
	  
	    /** 
	     * 将最新的数据插入excel 
	     */  
	    public void initExcel(){  
	        List list = new ArrayList();   
	        //这里是获取需要填充的数  
	        //list = XXXService().getXXX();        
	        //excel模板绝对路径  
	      String path = "/Edu/exceltemplet/";  
	       try{  
	            String filepath = ServletActionContext.getServletContext().getRealPath(path+ fileName +".xls");  
	            FileInputStream fis = new FileInputStream(filepath);  
	            ExcelUtils eu = new ExcelUtils();  
	            tempPath = eu.exportExcel(fis,"temp/"+createFileName(), fileName, list);  
	       }catch(Exception e){  
	            e.printStackTrace();  
	       }  
	    }  
	  
	    public InputStream getExcelStream() {  
	        return ServletActionContext.getServletContext().getResourceAsStream("/Edu/exceltemplet/"+tempPath);  
	    }  
	  
	     /** 
	     * 以年月日时分秒毫秒+4位随机数的格式来创建一个文件名，不带扩展名 
	     * @return 文件名 
	     */  
	    public static String createFileName() {  
	        StringBuffer sb = new StringBuffer();  
	        Date date = new Date();  
	        //获取年月日时分秒  
	        sb.append(new SimpleDateFormat("yyyyMMddHHmmss").format(date));  
	        //毫秒  
	        String milli = String.valueOf(date.getTime() % 1000);  
	        while (milli.length() < 3) {  
	            milli = "0" + milli;  
	        }  
	        sb.append(milli);  
	        //四位随机数  
	        String rondom = String.valueOf(new Random().nextInt(10000));  
	        while (rondom.length() < 4) {  
	            rondom = "0" + rondom;  
	        }  
	        sb.append(rondom);  
	        return sb.toString();  
	    }  
	  
	      
	    public String getFileName() {  
	        return fileName;  
	    }  
	  
	    public void setFileName(String fileName) {  
	        this.fileName = fileName;  
	    }
}

