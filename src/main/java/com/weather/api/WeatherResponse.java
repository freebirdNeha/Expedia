package com.weather.api;

public class WeatherResponse {
	private String city;
	private String state;
	private String temp_f;
	private String statusMsg;
	private int statusCode;
	
	public WeatherResponse(String statusMsg, int statusCode) {
		this.statusMsg = statusMsg;
		this.statusCode = statusCode;
	}
	public WeatherResponse() {}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getTemp_f() {
		return temp_f;
	}
	public void setTemp_f(String temp_f) {
		this.temp_f = temp_f;
	}
	public String getStatusMsg() {
		return statusMsg;
	}
	public void setStatusMsg(String statusMsg) {
		this.statusMsg = statusMsg;
	}
	public int getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	@Override
	public String toString() {
		return "WeatherResponse [city=" + city + ", state=" + state
				+ ", temp_f=" + temp_f + ", statusMsg=" + statusMsg
				+ ", statusCode=" + statusCode + "]";
	}
		
}
