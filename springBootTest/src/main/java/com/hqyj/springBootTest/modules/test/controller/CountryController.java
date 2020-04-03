package com.hqyj.springBootTest.modules.test.controller;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.hqyj.springBootTest.modules.test.entity.City;
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
	/**
	 * 
	 * @param countryId
	 * @return
	 */
	@RequestMapping("/cities/{countryId}")
	public List<City> queryCitiesByCountryId(@PathVariable int countryId) {
		return cs.queryCitiesByCountryId(countryId);
		
	}
	/*
	 * localhost:8089/country
	 */
	@RequestMapping("/country")
	public Country getCountryByName(@RequestParam String countryName) {
		return cs.getCountryByName(countryName);
		
	}
	@RequestMapping("/cities")
	PageInfo<City> queryCitiesByPage(int countryId,int currentPage,int pageSize){
		return cs.queryCitiesByPage(countryId, currentPage, pageSize);
	}
	/*
	 * localhost:8089/city
	 */
	@PostMapping(value = "/city",consumes = "application/json")
	public City insertCity(@RequestBody City city) {
		cs.insertCity(city);
		return city;
	}
	
	
	/*
	 * localhost:8089/cityUpdate
	 */
	@PostMapping(value = "/cityUpdate",consumes = "application/x-www-form-urlencoded")
	public City updateCity(@ModelAttribute City city) {
		cs.updateCity(city);
		return city;
	}
	
	@DeleteMapping("/cityDelete/{cityId}")
	public void deleteCity(@PathVariable int cityId) {
		cs.deleteCity(cityId);
	}
}
