package cn.fm.web.action.company;
import java.io.IOException;  


import java.io.OutputStream;  
import java.util.List;  
  
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;  
  
import org.apache.poi.ss.usermodel.CellStyle;  
import org.apache.poi.ss.usermodel.Row;  
import org.apache.poi.ss.usermodel.Sheet;  
import org.apache.poi.hssf.usermodel.HSSFWorkbook;  
import org.apache.poi.ss.usermodel.Workbook;  
import org.apache.struts2.interceptor.ServletResponseAware;  

import cn.fm.bean.user.Buyer;
import cn.fm.service.user.BuyerService;

import com.opensymphony.xwork2.ActionSupport;  
  
public class OutAction extends ActionSupport implements ServletResponseAware{  
  
    /** 
     *  
     */  
    private static final long serialVersionUID = 1L;  
    
    @Resource
    private BuyerService biz ;  
     
    private String format = "xls";  
    private HttpServletResponse response;  
    private String fileName;  
    private List<Buyer> buyerList=null;
    
    public String execute(){  
    	buyerList=getByuer();

        
          
        setResponseHeader();  
        try {  
            exportExcel(response.getOutputStream());  
            response.getOutputStream().flush();  
            response.getOutputStream().close();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
        return null;  
    }  
      
    /** 设置响应头*/  
    public void setResponseHeader(){  
        try{  
            response.setContentType("application/msexcel;charset=UTF-8");  //两种方法都可以  
           // response.setContentType("application/octet-stream;charset=iso-8859-1");  
            response.setHeader("Content-Disposition", "attachment;filename="  
                    +java.net.URLEncoder.encode(this.fileName, "UTF-8"));  
            //客户端不缓存  
            response.addHeader("Pargam", "no-cache");  
            response.addHeader("Cache-Control", "no-cache");  
        }catch(Exception ex){  
            ex.printStackTrace();  
        }  
    }  
      
    /**导出数据*/  
    private void exportExcel(OutputStream os) throws IOException{  
        Workbook book = new HSSFWorkbook();  
        Sheet sheet = book.createSheet("导出信息");  
        Row row = sheet.createRow(0);  
        row.createCell(0).setCellValue("编号");  
        row.createCell(1).setCellValue("用户名");  
        row.createCell(2).setCellValue("密码");  
        row.createCell(3).setCellValue("真实姓名");  
        row.createCell(4).setCellValue("地址");  
        row.createCell(5).setCellValue("备注");
        CellStyle sty = book.createCellStyle();  
        List<Buyer> list =biz.getAllByuer();  
        for (int i = 1; i < list.size(); i++) {  
        	Buyer user = list.get(i-1);  
            row = sheet.createRow(i);  
            row.createCell(0).setCellValue(user.getVisible());  
            row.createCell(1).setCellValue(user.getUsername());  
            row.createCell(2).setCellValue(user.getPassword());  
            row.createCell(3).setCellValue(user.getRegTime());  
            row.createCell(4).setCellValue(user.getEmail());  
            row.createCell(5).setCellValue(user.getGender().toString());  
        }  
        try{  
            book.write(os);  
        }catch(Exception ex){  
            ex.printStackTrace();  
        }  
    }  
      
      
    public String getFormat() {  
        return format;  
    }  
  
  
    public void setFormat(String format) {  
        this.format = format;  
        this.fileName = "导出数据.xls"; 
    }  
  
    public String getFileName() {  
        return fileName;  
    }  
  
  
    public void setFileName(String fileName) {  
        this.fileName = fileName;  
    }  
  
    /**记住一定有该属性的set方法*/  
    public void setServletResponse(HttpServletResponse response) {  
        this.response = response;  
    }  
    @SuppressWarnings("unchecked")
	public List<Buyer> getByuer()
    {
    	return biz.getAllByuer();

    }
 
	@SuppressWarnings("unused")
	private List<Buyer> getBuyerList() {
		return buyerList;
	}
	@SuppressWarnings("unused")
	private void setBuyerList(List<Buyer> buyerList) {
		this.buyerList = buyerList;
	}
		
	
	
}
