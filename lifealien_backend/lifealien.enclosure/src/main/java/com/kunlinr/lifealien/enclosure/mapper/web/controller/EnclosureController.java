package com.kunlinr.lifealien.enclosure.mapper.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.kunlinr.lifealien.enclosure.mapper.EnclosureMapper;
import com.kunlinr.lifealien.enclosure.po.Enclosure;

@RestController
@RequestMapping("/api/enclosure")
public class EnclosureController {
	
	@Autowired
	EnclosureMapper enclosureMapper;
	
	@RequestMapping(path = "/get",method = {RequestMethod.GET, RequestMethod.POST})
	public Map<String, String> getUserAll(){
		Map<String, String> results = new HashMap<String,String>();
		results.put("type", "img");
		List<Enclosure> selectByExample = enclosureMapper.selectByExample(null);
		return results;
	}
}
