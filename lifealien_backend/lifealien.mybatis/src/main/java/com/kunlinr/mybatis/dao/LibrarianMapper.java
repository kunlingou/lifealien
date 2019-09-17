package com.kunlinr.mybatis.dao;

import java.util.List;

import com.kunlinr.mybatis.entity.Librarian;

public interface LibrarianMapper {
	
	Librarian getById(Integer id);
	
	List<Librarian> getAll();

	Librarian getByIdAndName(Integer id, String name);
}
