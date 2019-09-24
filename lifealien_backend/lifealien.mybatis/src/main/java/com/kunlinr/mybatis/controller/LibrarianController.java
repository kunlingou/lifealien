package com.kunlinr.mybatis.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kunlinr.mybatis.entity.Librarian;
import com.kunlinr.mybatis.service.LibrarianService;

/**
 * @author kunlingou
 * @date 2019/09/24
 */
@RestController
@RequestMapping("api/mybatis")
public class LibrarianController {

    @Autowired
    private LibrarianService librarianService;

    @RequestMapping("getLibrariaById")
    public Librarian getLibrarianInfo(Integer id) {
        return librarianService.getById(id);
    }

    @RequestMapping("getLibrariaByIdAndName")
    public Librarian getLibrarianInfo(Integer id, String name) {
        return librarianService.getByIdAndName(id, name);
    }

    @RequestMapping("getAllLibraria")
    public List<Librarian> getLibrarianInfos() {
        return librarianService.getAll();
    }

}
