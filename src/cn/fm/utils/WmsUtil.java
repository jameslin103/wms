package cn.fm.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class WmsUtil{
	public static String getId() {
		UUID uuid = UUID.randomUUID();
		return uuid.toString().replace("-", "");
	}
	
	public static String getNow(){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		return sdf.format(new Date());
	}
	
	public static int getPageSize(){
		return 15;
	}
	
	public static void main(String[] args) {
		for (int i = 0; i < 10; i++) {
			System.out.println(getId());
		}
		
	}
	public static String  getURLDecoder(String str){
		
		try {
			str=URLDecoder.decode(str.trim(),"utf-8");
		} catch (UnsupportedEncodingException e) {
			return "";
		}
		return str;
	}
}
