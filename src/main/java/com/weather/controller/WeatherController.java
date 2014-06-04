package com.weather.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.weather.api.WeatherRequest;
import com.weather.api.WeatherResponse;
import com.weather.service.WeatherService;


@Controller
public class WeatherController {
	
	private static final Logger logger = LoggerFactory.getLogger(WeatherController.class);
	
	@Autowired
	private WeatherService weatherService; 
	
	@RequestMapping(value = "/displaytemperature", method = RequestMethod.POST)
	public ModelAndView displayTemperature(@ModelAttribute("SpringWeb")WeatherRequest request, ModelMap model) throws Exception {
		WeatherResponse response=null;
		
		logger.debug("Received request to display temperature with zip code :: "+request.getZipCode());
		
		boolean validRequest = weatherService.validateZipCode(request);	
		if(validRequest){
			 logger.debug("valid zip code :: "+request.getZipCode());
			 response = weatherService.findTemperature(request);
			 if(response.getStatusCode() == 1){
				 logger.debug("response :: "+response.toString());
				 ModelAndView successModel = new ModelAndView("weatherDetails");
				 successModel.addObject("response", response);
				 return successModel;
			 }
			 else{
				 logger.debug("invalid zip code :: "+request.getZipCode());
				 return new ModelAndView("invalidZipCode", "command", new WeatherRequest());
			 }
		}
		else
			 return new ModelAndView("weatherHome", "command", new WeatherRequest());
			 
	}
	
	@RequestMapping(value = "/temperature", method = RequestMethod.GET)
	public ModelAndView employee() throws Exception {
		 return new ModelAndView("weatherHome", "command", new WeatherRequest());
		
	}
	
}
