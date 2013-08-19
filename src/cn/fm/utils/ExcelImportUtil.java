package cn.fm.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import jxl.Cell;
import jxl.CellType;
import jxl.DateCell;
import jxl.Sheet;
import jxl.Workbook;

public class ExcelImportUtil {
	/**
	 * @author james
	 * Excel数据导入数据库
	 * @version 1.0
	 */


		
		/**
		 * 导入报表Excel数据，生成用户表的数据库导入语句
		 * @param formFile
		 * @return list ArrayList
		 * @throws Exception
		 */
		@SuppressWarnings("unchecked")
		public static ArrayList generateStationBugSql(File file,String fileName)
				throws Exception {
			InputStream in = null;
			Workbook wb = null;
			ArrayList list = new ArrayList();
			
			try {
				if (file == null) {
					throw new Exception("文件为空！");
				}
				in = new FileInputStream(file);
				wb = Workbook.getWorkbook(in);
				
				Sheet sheet[] = wb.getSheets();
				if (sheet != null) {
					for (int i = 0; i < sheet.length; i++) {
						if (!sheet[i].getName().equalsIgnoreCase(fileName)) {						
							throw new Exception("指定文件中不包含名称为"+fileName+",请重新指定！");
						}
						for (int j = 3; j < sheet[i].getRows(); j++) {
							String[] valStr = new String[31];
							for (int k = 1; k < sheet[i].getColumns(); k++) {
								Cell cell = sheet[i].getCell(k, j);
								String content = "";
								if (cell.getType() == CellType.DATE) {
									DateCell dateCell = (DateCell) cell;
									java.util.Date importdate = dateCell.getDate();/**如果excel是日期格式的话需要减去8小时*/
									long eighthour = 8*60*60*1000;
									SimpleDateFormat simpledate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
									/**在当前日期上减8小时*/
									long time = importdate.getTime()-eighthour; 
									java.util.Date date = new java.util.Date();
									date.setTime(time);
									content = simpledate.format(date); 
								} else {
									String tempcontent = (cell.getContents() == null ? ""
											: cell.getContents());
									content = tempcontent.trim().replace('\'', ' ');
								}
								valStr[k] = content;
								
							} 
							list.add(j-3,valStr);
						}
					}
				}
				
				return list;
			} catch (Exception e) {
				e.printStackTrace();
				throw e;
			} finally {
				if (wb != null) {
					wb.close();
				}
				if (in != null) {
					try {
						in.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
	
}
