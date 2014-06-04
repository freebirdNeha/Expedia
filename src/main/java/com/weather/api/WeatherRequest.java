package com.weather.api;

public class WeatherRequest {
	private String zipCode ;

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	
	public WeatherRequest() {
		super();
	}

	public WeatherRequest(String zipCode) {
		super();
		this.zipCode = zipCode;
	}

	@Override
	public String toString() {
		return "WeatherRequest [zipCode=" + zipCode + "]";
	}	
}
