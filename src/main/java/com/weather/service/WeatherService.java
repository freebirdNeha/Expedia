package com.weather.service;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.weather.api.WeatherRequest;
import com.weather.api.WeatherResponse;

@Service
public class WeatherService {
	
	private static final Logger logger = LoggerFactory.getLogger(WeatherService.class);
	
	private static String WEATHER_URL_STRING = "http://api.wunderground.com/api/ed044d75b91fb500/conditions/q/";
	
	/*
	 * validates the zip code
	 * length should be 5
	 * should contain only numbers
	 */
	public boolean validateZipCode(WeatherRequest request){
		
		if(null == request || "".equals(request.getZipCode()) || null == request.getZipCode()){
			logger.debug("invalid request");
			return false;
		}
		
		String zipCode = request.getZipCode();
		
		if(zipCode.length() != 5){
			logger.debug("invalid zip code :: "+zipCode+" with length :: "+zipCode.length());
			return false;
		}
		for(int i=0;i<5;i++){
			if(zipCode.charAt(i)<'0' || zipCode.charAt(i)>'9' ){
				logger.debug("invalid zip code :: "+zipCode+" :: contains non numeric character :: "+zipCode.charAt(i));
				return false;
			}
		}
		return true;
	}
	
	/*
	 * hits the api :: http://api.wunderground.com/api 
	 * fetches the temperature, 
	 * validates response through validateResult()
	 * if a valid zip code returns status code 1
	 * if error returns status code -1 or -2
	 */
	public WeatherResponse findTemperature(WeatherRequest request) {
        WeatherResponse response = new WeatherResponse();
        try {
        	
        	URL weatherURLXml = new URL(WEATHER_URL_STRING+request.getZipCode()+".xml");
          
            URLConnection uc = weatherURLXml.openConnection();
            uc.connect();
          
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(uc.getInputStream());
            doc.getDocumentElement().normalize();     
          
            if(!validateResponse(doc))
            	return new WeatherResponse("Invalid Zip Code", -2);
            
            NodeList state = doc.getElementsByTagName("state_name");
            Node stateNode = state.item(0);
                    
            if (stateNode.getNodeType() == Node.ELEMENT_NODE) {
               response.setState(stateNode.getFirstChild().getNodeValue());
            }
            NodeList city = doc.getElementsByTagName("city");
            Node cityNode = city.item(0);
                    
            if (cityNode.getNodeType() == Node.ELEMENT_NODE) {
               response.setCity(cityNode.getFirstChild().getNodeValue());
            }
            NodeList temp_f = doc.getElementsByTagName("temp_f");
            Node temp_fNode = temp_f.item(0);
                    
            if (temp_fNode.getNodeType() == Node.ELEMENT_NODE) {
               response.setTemp_f(temp_fNode.getFirstChild().getNodeValue());
            }
            response.setStatusMsg("Valid Zip Code");
            response.setStatusCode(1);
            return response;
            
           } catch (MalformedURLException ex) {
        	   return new WeatherResponse("Invalid Zip Code", -1);
           } catch (IOException e) {
        	   return new WeatherResponse("Invalid Zip Code", -1);
           } catch (ParserConfigurationException e) {
        	   return new WeatherResponse("Invalid Zip Code", -1);
           } catch (SAXException e) {
        	   return new WeatherResponse("Invalid Zip Code", -1);
           }
        
    }
	
	private boolean validateResponse(Document doc) {
		NodeList error = null;
		error = doc.getElementsByTagName("error");
		Node errorNode = error.item(0);
        if(null != errorNode)
        	return false;
		return true;
	}
}
