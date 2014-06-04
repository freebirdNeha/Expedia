package com.weather.test;

import junit.framework.Assert;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.weather.api.WeatherRequest;
import com.weather.service.WeatherService;

public class TestValidateZipCode {

	private WeatherService weatherService = new WeatherService();
	
	/*
	 * valid zip code condition
	 */
	@Test
	public void testvalidZipCode() {
		WeatherRequest req = new WeatherRequest();
		req.setZipCode("94117");
		Assert.assertTrue(weatherService.validateZipCode(req));
 
	}
	
	/*
	 * tests valid zip code
	 * only numerals allowed
	 */
	@Test
	public void testInvalidZIpCode() {
		WeatherRequest req = new WeatherRequest();
		req.setZipCode("94a");
		Assert.assertFalse(weatherService.validateZipCode(req)); 
	}
	
	/*
	 * tests length parameter of zip code
	 * cann't be less than 5 characters
	 */
	@Test
	public void testInvalidZIpCodeLength() {
		WeatherRequest req = new WeatherRequest();
		req.setZipCode("946");
		Assert.assertFalse(weatherService.validateZipCode(req)); 
	}
	
}
