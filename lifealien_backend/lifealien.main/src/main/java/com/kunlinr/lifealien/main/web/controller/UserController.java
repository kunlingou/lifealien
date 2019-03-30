package com.kunlinr.lifealien.main.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kunlinr.lifealien.enclosure.mapper.EnclosureMapper;
import com.kunlinr.lifealien.enclosure.po.Enclosure;
import com.kunlinr.lifealien.main.mapper.UserMapper;
import com.kunlinr.lifealien.main.web.controller.pagination.PaginationMultiTypeValuesHelper;

@RestController
@RequestMapping("/api/persons")
public class UserController {
	
	@Autowired
	UserMapper userMapper;
	
//	@Autowired
//	EnclosureMapper enclosureMapper;
	
	@RequestMapping(method = {RequestMethod.GET, RequestMethod.POST}, produces = MediaType.APPLICATION_JSON_VALUE)
	public Map<String, PaginationMultiTypeValuesHelper> getUserAll(
			@RequestParam Integer page,
			@RequestParam String sex,
			@RequestParam String email
	){
		Map<String, PaginationMultiTypeValuesHelper> results = new HashMap<>();
		PaginationMultiTypeValuesHelper multiValue = new PaginationMultiTypeValuesHelper();
		multiValue.setResults(userMapper.selectByExample(null));
		multiValue.setCount(4);
		multiValue.setPage(1);
		multiValue.setTotal(4L);
		results.put("data", multiValue);
		
//		List<Enclosure> selectByExample = enclosureMapper.selectByExample(null);
		return results;
	}
}
