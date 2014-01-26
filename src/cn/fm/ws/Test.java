package cn.fm.ws;

import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.List;

import org.apache.axis2.AxisFault;

import cn.fm.ws.WeatherWSStub.GetRegionProvinceResponse;
import cn.fm.ws.WeatherWSStub.GetSupportCityStringResponse;
import cn.fm.ws.WeatherWSStub.GetWeatherResponse;


public class Test {

	public static void main(String[] args) throws Exception {
		
		 MobileCodeWS service = new MobileCodeWS();
		 MobileCodeWSSoap soap = service.getMobileCodeWSSoap();
		 System.out.println(soap.getMobileCodeInfo("15982351234", ""));

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

}
