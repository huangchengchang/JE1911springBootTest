package com.hqyj.springBootTest.modules.test.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hqyj.springBootTest.modules.test.dao.CountryDao;
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

}
