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
	   private String fileName;        //�ļ���  
	    private String tempPath;      //��ʱ�ļ�Ŀ¼  
	  
	    public String download(){  
	         initExcel();  
	        return "excel";  
	    }  
	  
	    /** 
	     * �����µ����ݲ���excel 
	     */  
	    public void initExcel(){  
	        List list = new ArrayList();   
	        //�����ǻ�ȡ��Ҫ������  
	        //list = XXXService().getXXX();        
	        //excelģ�����·��  
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
	     * ��������ʱ�������+4λ������ĸ�ʽ������һ���ļ�����������չ�� 
	     * @return �ļ��� 
	     */  
	    public static String createFileName() {  
	        StringBuffer sb = new StringBuffer();  
	        Date date = new Date();  
	        //��ȡ������ʱ����  
	        sb.append(new SimpleDateFormat("yyyyMMddHHmmss").format(date));  
	        //����  
	        String milli = String.valueOf(date.getTime() % 1000);  
	        while (milli.length() < 3) {  
	            milli = "0" + milli;  
	        }  
	        sb.append(milli);  
	        //��λ�����  
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

