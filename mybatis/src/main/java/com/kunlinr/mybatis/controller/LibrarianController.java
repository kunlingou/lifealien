package com.kunlinr.mybatis.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.kunlinr.mybatis.entity.Librarian;
import com.kunlinr.mybatis.service.LibrarianService;

@Controller
@RequestMapping("api/mybatis")
public class LibrarianController {
	
	@Autowired
    private LibrarianService librarianService;
    
    @RequestMapping("getLibrariaById")
    public Librarian getLibrarianInfo(Integer id) {
        return librarianService.getById(id);
    }
    
//    @RequestMapping("getAllLibraria")
//    public String getLibrarianInfos() {
//        return "123";
//    }
    
//    @RequestMapping("getAllLibraria")
//    public List<Librarian> getLibrarianInfos() {
//        return librarianService.getAll();
//    }
    
    @RequestMapping("getAllLibraria")
    public ModelAndView getLibrarianInfos() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("itemsList",librarianService.getAll());
		modelAndView.setViewName("itemsListView");
		return modelAndView;
    }
}
