package cn.fm.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import cn.fm.bean.uploadfile.ExcelUploadFile;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

public class ExcelUtils {
	   
    /** 
       * @param sheet 要添加数据的工作表 
     * @param list 填充的数据 
     */  
    public void addCellOfBug(WritableSheet sheet, List list) {  
        if (list.size() > 0) {  
            int i = 0;  
            for (Iterator it = list.iterator(); it.hasNext();) {  
                int j = 0;  
                ExcelUploadFile bs = (ExcelUploadFile) it.next();  
                if (null != bs) {  
                    try {  
                        String bugs = bs.getFilepath();  
                        String[] strs = bugs.split(";");  
                        //第一列,填充 XXX, Label(列,行,值)  
                        //sheet.addCell(new Label(j++, i + 3, bs.getId()));  
                        //第二列,填充 XXXXX  
                         sheet.addCell(new Label(j++, i + 3, strs[0].equals("1") ? "是" : "否"));  
                     } catch (WriteException ex) {  
                        ex.printStackTrace();  
                    }  
                }  
                i++;  
            }  
        }  
    }  
  
    /** 
     * 得到实际保存文件根目录 
   */  
    public static String getRootPath() {  
        return ServletActionContext.getServletContext().getRealPath("").replace("\\", "/") + "/Edu/exceltemplet/";  
    }  
  
    /** 
     * 输出excel 
     * @param is 原始excel模版输入流 
     * @param path 临时文件目录 
     * @param fileName 文件名 
     * @param list 要填充的数据 
     * @return 返回相对临时文件的目录 
     */  
    public String exportExcel(InputStream is, String path, String fileName, List list) {  
        //临时目录,用于生成临时文件  
        String tempPath = null ;  
        WritableWorkbook wb = null;  
        File f = new File(getRootPath() + path);  
        //不存在则创建它  
        if (!f.exists())   
            f.mkdirs();  
        tempPath = getRootPath() + path + "/" + fileName + ".xls";  
        final File file = new File(tempPath);  
        OutputStream oss = null;  
        try {  
            //创建临时文件  
            if(file.createNewFile()){  
                oss = new FileOutputStream(file);  
                wb = Workbook.createWorkbook(oss, Workbook.getWorkbook(is));  
                WritableSheet sheet = wb.getSheet(0);  
                if ("bug".equals(fileName)) {  
                    addCellOfBug(sheet, list);  
                }  
                wb.write();  
            }  
        } catch (Exception e) {  
            e.printStackTrace();  
        } finally {  
            try {  
                wb.close();  
                oss.flush();  
                oss.close();  
            } catch (Exception e) {  
                e.printStackTrace();  
            }  
        }  
  
        //过一段时间之后,删除临时文件  
        new Thread(new Runnable() {  
            public void run() {  
                try {  
                    // 线程睡20秒  
             Thread.sleep(20000);  
                } catch (InterruptedException e) {  
                    e.printStackTrace();  
                }  
                // 删除临时文件  
                file.delete();  
            }  
        }).start();  
        return path + "/" + fileName + ".xls";  
    }  
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
