/**
 * @author jameslin
 * Excel数据导入数据库
 * @version 1.0
 */
package cn.fm.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import jxl.Cell;
import jxl.CellType;
import jxl.DateCell;
import jxl.Sheet;
import jxl.Workbook;

public class GenerateSqlFromExcel {

	
	/**
	 * 导入报表Excel数据，生成用户表的数据库导入语句
	 * @param formFile
	 * @return list ArrayList
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static ArrayList generateStationBugSql(File file,String fileName,int number,int readRow)throws Exception {
		
		InputStream inputStream= null;
		Workbook workbook= null;
		ArrayList list =new ArrayList();
		
		try {
			if (file == null) 
			{
				throw new Exception("文件为空！");
			}

			inputStream= new FileInputStream(file);
			workbook= Workbook.getWorkbook(inputStream);
			Sheet sheet[] = workbook.getSheets();
			if (sheet != null) 
			{
				for (int i = 0; i < sheet.length; i++)
				{
					if (!sheet[i].getName().equalsIgnoreCase(fileName))
					{						
						throw new Exception("指定文件中不包含名称为: "+fileName+" 格式文件名,请重新指定！");
					}
					list=createSheet(sheet,i,number,readRow);
				}
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			closeDataFlow(workbook,inputStream);
		}
	}
	/**
	 * 获取工作簿
	 * @param sheet
	 * @param i
	 * @param list
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static ArrayList createSheet(Sheet sheet[],int i,int number,int readRow)
	{
		ArrayList list=new ArrayList();
		for (int j =readRow; j < sheet[i].getRows(); j++)
		{
			String[] valStr = new String[number];
			for (int k = 0; k < sheet[i].getColumns(); k++) 
			{
				
				Cell cell = sheet[i].getCell(k, j);
				String content = "";
				if (cell.getType() == CellType.DATE) {
					content=excelToDealWithDate(cell);
				} else {
					String tempcontent = (cell.getContents() == null ? "": cell.getContents());
					content = tempcontent.trim().replace('\'', ' ');
				}
				valStr[k] = content;
				
			} 
			list.add(j-readRow,valStr);
		}
		return list;
	}
	/**
	 * excel日期格式转换
	 * @param cell
	 * @param content
	 * @return
	 */
	public static String excelToDealWithDate(Cell cell)
	{
			String content="";
			DateCell dateCell = (DateCell) cell;
			java.util.Date importdate = dateCell.getDate();/**如果excel是日期格式的话需要减去8小时*/
			long eighthour = 8*60*60*1000;
			SimpleDateFormat simpledate = new SimpleDateFormat(DateUtil.FORMAT_DATE_TIME); 
			/**在当前日期上减8小时*/
			long time = importdate.getTime()-eighthour; 
			Date date = new Date();
			date.setTime(time);
			content = simpledate.format(date); 
			
			return content;
	}
	
	/**
	 * 关闭数据流
	 */
	public static void closeDataFlow(Workbook workbook,InputStream inputStream)
	{
		if (workbook!= null) 
		{
			workbook.close();
		}
		if (inputStream!= null)
		{
			try 
			{
				inputStream.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}