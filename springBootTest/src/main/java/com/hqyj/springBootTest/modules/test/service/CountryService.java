package com.hqyj.springBootTest.modules.test.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.hqyj.springBootTest.modules.test.entity.City;
import com.hqyj.springBootTest.modules.test.entity.Country;

public interface CountryService {
	Country getCountryById(int countryId);
	
	List<City> queryCitiesByCountryId(int countryId);
	
	Country getCountryByName(String countryName);
	
	PageInfo<City> queryCitiesByPage(int countryId,int currentPage,int pageSize);
	
	City insertCity(City city);
	
	City updateCity(City city);
	
	void deleteCity(int cityId);
}
