package com.hqyj.springBootTest.modules.test.service;

import com.hqyj.springBootTest.modules.test.entity.Country;

public interface CountryService {
	Country getCountryById(int countryId);
}
