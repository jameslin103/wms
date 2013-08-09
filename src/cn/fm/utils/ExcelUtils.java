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
       * @param sheet Ҫ������ݵĹ����� 
     * @param list �������� 
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
                        //��һ��,��� XXX, Label(��,��,ֵ)  
                        //sheet.addCell(new Label(j++, i + 3, bs.getId()));  
                        //�ڶ���,��� XXXXX  
                         sheet.addCell(new Label(j++, i + 3, strs[0].equals("1") ? "��" : "��"));  
                     } catch (WriteException ex) {  
                        ex.printStackTrace();  
                    }  
                }  
                i++;  
            }  
        }  
    }  
  
    /** 
     * �õ�ʵ�ʱ����ļ���Ŀ¼ 
   */  
    public static String getRootPath() {  
        return ServletActionContext.getServletContext().getRealPath("").replace("\\", "/") + "/Edu/exceltemplet/";  
    }  
  
    /** 
     * ���excel 
     * @param is ԭʼexcelģ�������� 
     * @param path ��ʱ�ļ�Ŀ¼ 
     * @param fileName �ļ��� 
     * @param list Ҫ�������� 
     * @return ���������ʱ�ļ���Ŀ¼ 
     */  
    public String exportExcel(InputStream is, String path, String fileName, List list) {  
        //��ʱĿ¼,����������ʱ�ļ�  
        String tempPath = null ;  
        WritableWorkbook wb = null;  
        File f = new File(getRootPath() + path);  
        //�������򴴽���  
        if (!f.exists())   
            f.mkdirs();  
        tempPath = getRootPath() + path + "/" + fileName + ".xls";  
        final File file = new File(tempPath);  
        OutputStream oss = null;  
        try {  
            //������ʱ�ļ�  
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
  
        //��һ��ʱ��֮��,ɾ����ʱ�ļ�  
        new Thread(new Runnable() {  
            public void run() {  
                try {  
                    // �߳�˯20��  
             Thread.sleep(20000);  
                } catch (InterruptedException e) {  
                    e.printStackTrace();  
                }  
                // ɾ����ʱ�ļ�  
                file.delete();  
            }  
        }).start();  
        return path + "/" + fileName + ".xls";  
    }  
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
