package com.mycom.service;

public interface GeoLocationService {
	
	/**
	 * Service to obtain the country an IP address belongs to.
	 * 
	 * @param ip IP address
	 * @return String containing name of the country.
	 */
	public String getCountry(String ip);

}
