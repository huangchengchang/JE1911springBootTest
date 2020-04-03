package com.hqyj.springBootTest.modules.test.service.impl;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hqyj.springBootTest.modules.test.dao.CountryDao;
import com.hqyj.springBootTest.modules.test.entity.City;
import com.hqyj.springBootTest.modules.test.entity.Country;
import com.hqyj.springBootTest.modules.test.service.CountryService;
@Service
public class CountryServiceImpl implements CountryService{

	@Autowired
	private CountryDao cd;
	
	@Override
	public Country getCountryById(int countryId) {
		// TODO Auto-generated method stub
		return cd.getCountryById(countryId);
	}

	@Override
	public List<City> queryCitiesByCountryId(int countryId) {
		//return cd.queryCitiesByCountryId(countryId);
		return Optional.ofNullable(cd.queryCitiesByCountryId(countryId))
				.orElse(Collections.emptyList());
	}

	@Override
	public Country getCountryByName(String countryName) {
		// TODO Auto-generated method stub
		return cd.getCountryByName(countryName);
	}

	@Override
	public PageInfo<City> queryCitiesByPage(int countryId, int currentPage, int pageSize) {
		PageHelper.startPage(currentPage, pageSize);
		List<City> cities = Optional.ofNullable(cd.queryCitiesByCountryId(countryId))
		.orElse(Collections.emptyList());
		return new PageInfo(cities);
	}

	@Override
	public City insertCity(City city) {
		cd.insertCity(city);
		return city;
	}

	
	@Transactional(propagation = Propagation.REQUIRED,noRollbackFor = ArithmeticException.class)
	@Override
	public City updateCity(City city) {
		cd.updateCity(city);
		return city;
	}

	@Override
	public void deleteCity(int cityId) {
		cd.deleteCity(cityId);
		
	}

}
