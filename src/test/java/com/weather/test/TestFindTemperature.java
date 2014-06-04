package com.weather.test;

import junit.framework.Assert;
import org.junit.Test;

import com.weather.api.WeatherRequest;
import com.weather.service.WeatherService;

public class TestFindTemperature {
	
	private WeatherService weatherService = new WeatherService();
	
	/*
	 * tests the existence of zip code 
	 * Success case returns status code of 1
	 */
	@Test
	public void testValidCity() {
		WeatherRequest req = new WeatherRequest();
		req.setZipCode("94117");
		Assert.assertEquals(weatherService.findTemperature(req).getStatusCode(),1); 
	}
	
	/*
	 * tests the non-existing zip code 
	 * returns status code of -2
	 */
	@Test
	public void testInvalidZIpCode() {
		WeatherRequest req = new WeatherRequest();
		req.setZipCode("12100");
		Assert.assertEquals(weatherService.findTemperature(req).getStatusCode(),-2);
	}
}
