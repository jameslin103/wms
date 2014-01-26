package cn.fm.web.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import cn.fm.ws.MobileCodeWS;
import cn.fm.ws.MobileCodeWSSoap;
import cn.fm.ws.WeatherWSStub;
import cn.fm.ws.WeatherWSStub.GetRegionProvinceResponse;
import cn.fm.ws.WeatherWSStub.GetSupportCityStringResponse;
import cn.fm.ws.WeatherWSStub.GetWeatherResponse;

public class WeatherAction extends BaseAction{
	
	private static final long serialVersionUID = 7334492350013391659L;
	
	private String phoneNum;
	private String province;
	private String city;
	
	
	public String phoneAddress() {
		return SUCCESS;
	}

	public String getAddress() {
		MobileCodeWS service = new MobileCodeWS();
		MobileCodeWSSoap soap = service.getMobileCodeWSSoap();
		return soap.getMobileCodeInfo(phoneNum, "");
	}

	public String getProvinces() {
		WeatherWSStub stub = null;
		try {
			stub = new WeatherWSStub();
			WeatherWSStub.GetRegionProvince getRegionProvince = new WeatherWSStub.GetRegionProvince();
			GetRegionProvinceResponse response = stub.getRegionProvince(getRegionProvince);
			List<Entity> data = new ArrayList<Entity>();

			String[] result = response.getGetRegionProvinceResult().getString();
			for (String s : result) {
				Entity entity = new Entity();
				entity.setName(s.substring(0, s.indexOf(",")));
				entity.setCode(s.substring(s.indexOf(",") + 1));
				data.add(entity);
			}
			responseJson(data);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return NONE;
	}

	public String getCities() {
		WeatherWSStub stub = null;
		try {
			stub = new WeatherWSStub();
			WeatherWSStub.GetSupportCityString getCities = new WeatherWSStub.GetSupportCityString();
			getCities.setTheRegionCode(province);
			GetSupportCityStringResponse response = stub.getSupportCityString(getCities);
			List<Entity> data = new ArrayList<Entity>();

			String[] result = response.getGetSupportCityStringResult().getString();
			for (String s : result) {
				Entity entity = new Entity();
				entity.setName(s.substring(0, s.indexOf(",")));
				entity.setCode(s.substring(s.indexOf(",") + 1));
				data.add(entity);
			}
			responseJson(data);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return NONE;

	}

	public String getWeather() {
		WeatherWSStub stub;
		try {
			stub = new WeatherWSStub();
			WeatherWSStub.GetWeather getWeather = new WeatherWSStub.GetWeather();
			getWeather.setTheCityCode(city);
			GetWeatherResponse response = stub.getWeather(getWeather);
			responseJson(Arrays.asList(response.getGetWeatherResult().getString()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return NONE;
	}

	class Entity {
		private String name;
		private String code;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getCode() {
			return code;
		}

		public void setCode(String code) {
			this.code = code;
		}

	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	
	

}
