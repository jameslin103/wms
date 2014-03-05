package junit.test.company;


import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.fm.bean.salary.BalanceDetail;
import cn.fm.service.salary.BalanceDetailService;
import cn.fm.ws.MobileCodeWS;
import cn.fm.ws.MobileCodeWSSoap;
import cn.fm.ws.WeatherWSStub;
import cn.fm.ws.WeatherWSStub.GetRegionProvinceResponse;
import cn.fm.ws.WeatherWSStub.GetSupportCityStringResponse;
import cn.fm.ws.WeatherWSStub.GetWeatherResponse;

public class BalanceDetailServiceTest {

	static BalanceDetailService balanceDetailService;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		ApplicationContext  axt=new ClassPathXmlApplicationContext("beans.xml");
		
		balanceDetailService=(BalanceDetailService)axt.getBean("balanceDetailServiceImpl");	
		
	}
	
	@Test
	public void testAllBalanceDetail() throws Exception
	{
			 MobileCodeWS service = new MobileCodeWS();
			 MobileCodeWSSoap soap = service.getMobileCodeWSSoap();
			 System.out.println(soap.getMobileCodeInfo("13809505940", ""));

			 List<String> provinces = getProvinces();
			 for (String province : provinces) {
			 System.out.println(province);
			 }

			List<String> cities = getCities("四川");
			for (String city : cities) {
				System.out.println(city);
			}
			
			List<String> weathers=getWeather("成都");
			for (int i=0;i< weathers.size();i++) {
				System.out.println(i+"=="+weathers.get(i));
			}
		}

		public static List<String> getProvinces() throws Exception {
			WeatherWSStub stub = new WeatherWSStub();
			WeatherWSStub.GetRegionProvince getRegionProvince = new WeatherWSStub.GetRegionProvince();
			GetRegionProvinceResponse response = stub.getRegionProvince(getRegionProvince);
			return Arrays.asList(response.getGetRegionProvinceResult().getString());
		}

		public static List<String> getCities(String province) throws Exception {
			WeatherWSStub stub = new WeatherWSStub();
			WeatherWSStub.GetSupportCityString getCities = new WeatherWSStub.GetSupportCityString();
			getCities.setTheRegionCode(province);
			GetSupportCityStringResponse response = stub
					.getSupportCityString(getCities);
			return Arrays.asList(response.getGetSupportCityStringResult()
					.getString());
		}

		public static List<String> getWeather(String city) throws Exception {
			WeatherWSStub stub = new WeatherWSStub();
			WeatherWSStub.GetWeather getWeather = new WeatherWSStub.GetWeather();
			getWeather.setTheCityCode(city);
			GetWeatherResponse response = stub.getWeather(getWeather);
			return Arrays.asList(response.getGetWeatherResult().getString());
		}
		
	@Test
	public void update()
	{
		BalanceDetail balanceDetail=new BalanceDetail();
		balanceDetail.setServiceWith(new BigDecimal(1000));
		balanceDetail.setReceivedFunds(new BigDecimal(800));
		balanceDetail.setNote("已完成配置");
		balanceDetail.setFiveFund(new BigDecimal(300));
		balanceDetail.setServiceWith(new BigDecimal(700));
		balanceDetail.setWages(new BigDecimal(200));
		balanceDetailService.update(balanceDetail);

		
	}
	@Test
	public void save()
	{
		BalanceDetail balanceDetail=new BalanceDetail();
		balanceDetail.setServiceWith(new BigDecimal(1000));
		balanceDetail.setReceivedFunds(new BigDecimal(800));
		balanceDetail.setNote("已完成配置");
		balanceDetail.setFiveFund(new BigDecimal(300));
		balanceDetail.setServiceWith(new BigDecimal(700));
		balanceDetail.setWages(new BigDecimal(200));
//		Enterprise en=new Enterprise();
//		en.setEnterpriseId(1);
//		balanceDetail.setEnterprise(en);
//		balanceDetail.getEnterprise().getEnterpriseId();
//		
		balanceDetailService.save(balanceDetail);
		
	}
	
	@Test
	public void save1(){
		System.out.println("====");
		BigDecimal tax=new BigDecimal("4.5");
		BigDecimal proportion = new BigDecimal(100);
		BigDecimal resultsTax = tax.divide(proportion, 3,BigDecimal.ROUND_HALF_UP);
	
		System.out.println(resultsTax);
		
		
	}
	
}
