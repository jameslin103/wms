package cn.fm.utils;

import java.math.BigDecimal;

/**
 * 
 * @author jameslin
 * @date   2013-10-11
 * @version 1.0
 * @category 计算个人计税工具类
 */
public class PersonalTaxUtil {

	
	/**
	 * 计税方法
	 * @param threshold
	 * @return 所交的税
	 */
	public static BigDecimal  getPersonalTaxResults(Double wage,Double threshold, Double fiveBase){
		
		BigDecimal  results = null;
		Double remainingWage=0.0;
		if(wage<threshold){
			results=new BigDecimal("0.0");
			return results;
		}else{
			remainingWage=wage-threshold-fiveBase;
		}
		if(remainingWage<0){
			results=new BigDecimal("0.0");
			return results;
		}
		
		if(remainingWage!=0){
			if(remainingWage==1500){
				results=new BigDecimal(remainingWage*0.03-0).setScale(2,BigDecimal.ROUND_HALF_DOWN);
			}else if(remainingWage>=1500 && remainingWage<=4500){
				results=new BigDecimal(remainingWage*0.1-105).setScale(2,BigDecimal.ROUND_HALF_DOWN);
				
			}else if(remainingWage>=4500 && remainingWage<=9000){
				results=new BigDecimal(remainingWage*0.2-555).setScale(2,BigDecimal.ROUND_HALF_DOWN);
				
			}else if(remainingWage>=9000 && remainingWage<=35000){
				results=new BigDecimal(remainingWage*0.25-1005).setScale(2,BigDecimal.ROUND_HALF_DOWN);
				
			}else if(remainingWage>=35000 && remainingWage<=55000){
				results=new BigDecimal(remainingWage*0.3-2755).setScale(2,BigDecimal.ROUND_HALF_DOWN);
				
			}else if(remainingWage>=55000 && remainingWage<=80000){
				results=new BigDecimal(remainingWage*0.35-5505).setScale(2,BigDecimal.ROUND_HALF_DOWN);
				
			}else if(remainingWage>=80000){
				results=new BigDecimal(remainingWage*0.45-13505).setScale(2,BigDecimal.ROUND_HALF_DOWN);
				
			}else{
				results=new BigDecimal(0.00);
			}
			
		}
		
		return results;
	}
	
}
