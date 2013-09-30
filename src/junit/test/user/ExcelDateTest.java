package junit.test.user;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

import cn.fm.bean.user.Book;
import cn.fm.bean.user.Student;
import cn.fm.utils.ExportExcelUtils;

public class ExcelDateTest {
	
	public static void main(String[] args) throws Exception {
	      // 测试学生
		ExportExcelUtils<Student> ex = new ExportExcelUtils<Student>();
	      String[] headers = { "学号", "姓名", "年龄", "性别", "出生日期" };
	      List<Student> dataset = new ArrayList<Student>();
	      dataset.add(new Student(10000001, "张三", 20, true, new Date()));
	      dataset.add(new Student(20000002, "李四", 24, false, new Date()));
	      dataset.add(new Student(30000003, "王五", 22, true, new Date()));
	      // 测试图书
	      ExportExcelUtils<Book> ex2 = new ExportExcelUtils<Book>();
	      String[] headers2 = { "图书编号", "图书名称", "图书作者", "图书价格", "图书ISBN",
	            "图书出版社", "封面图片" };
	      List<Book> dataset2 = new ArrayList<Book>();
	      try {
	    	 String url = System.getProperty("user.dir")+"/src/james.jpg";
	         BufferedInputStream bis = new BufferedInputStream(
	                new FileInputStream(url));
	         byte[] buf = new byte[bis.available()];
	         while ((bis.read(buf)) != -1) {
	            //
	         }
	         dataset2.add(new Book(1, "jsp", "leno", 300.33f, "1234567",
	                "清华出版社", buf));
	         dataset2.add(new Book(2, "java编程思想", "brucl", 300.33f, "1234567",
	                "阳光出版社", buf));
	         dataset2.add(new Book(3, "DOM艺术", "lenotang", 300.33f, "1234567",
	                "清华出版社", buf));
	         dataset2.add(new Book(4, "c++经典", "leno", 400.33f, "1234567",
	                "清华出版社", buf));
	         dataset2.add(new Book(5, "c#入门", "leno", 300.33f, "1234567",
	                "汤春秀出版社", buf));
	         
	         List<String> dd=new ArrayList<String>();
	         for(int i=0; i<headers2.length; i++)
	         {
	        	 String title = headers2[i];
	        	 
	        	 dd.add(title); 
	         }
	         
	         OutputStream out = new FileOutputStream("E://a.xls");
	         OutputStream out2 = new FileOutputStream("E://b.xls");

	         ex.exportExcel(headers, dataset, out);
	         ex2.exportExcel(headers2, dataset2, out2);
	         out.close();
	         JOptionPane.showMessageDialog(null, "导出成功!");
	         System.out.println("excel导出成功！");
	      } catch (FileNotFoundException e) {
	         // TODO Auto-generated catch block
	         e.printStackTrace();
	      } catch (IOException e) {
	         // TODO Auto-generated catch block
	         e.printStackTrace();
	      }
	   }
}