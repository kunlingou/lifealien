package com.kunlinr.mybatis.service;

import java.util.List;

import com.kunlinr.mybatis.entity.Librarian;

public interface LibrarianService {
	
	Librarian getById(Integer id);
	
	List<Librarian> getAll();

	Librarian getByIdAndName(Integer id, String name);
	
}
