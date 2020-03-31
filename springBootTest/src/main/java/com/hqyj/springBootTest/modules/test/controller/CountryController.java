package com.hqyj.springBootTest.modules.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hqyj.springBootTest.modules.test.entity.Country;
import com.hqyj.springBootTest.modules.test.service.CountryService;

@RestController
public class CountryController {
	
	@Autowired
	private CountryService cs;
	
	@RequestMapping("/country/{countryId}")
	public Country getCountryById(@PathVariable int countryId) {
		
		return cs.getCountryById(countryId);
	}
	
}
