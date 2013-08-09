package cn.fm.utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import net.sf.ehcache.util.PropertyUtil;

public class ConnUtil {
	
	private static Properties read() throws IOException
	{
		 InputStream in = PropertyUtil.class.getClassLoader().getResourceAsStream("jdbc.properties");;
		 Properties p = new Properties();
		 p.load(in);
		 return p;
		 
	}
	/**
	 * get the connection of jdbc,when you call this function,you must call the closeConn function
	 * @return
	 */
	public static Connection getConn()
	{
		Connection conn=null;
		Properties p=null;
		try {
			p=ConnUtil.read();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		 if(p!=null)
		 { 
			 String url= p.get("jdbc.url").toString();
			 String user=p.get("jdbc.username").toString();
			 String password=p.get("jdbc.password").toString();
			 try {
				 conn=DriverManager.getConnection(url,user,password);
			 } catch (SQLException e) {
				 e.printStackTrace();
			 } 
		 }
	     return conn;
	}

	public static void closeConn(Connection conn)
	{
		if(conn!=null)
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
}
