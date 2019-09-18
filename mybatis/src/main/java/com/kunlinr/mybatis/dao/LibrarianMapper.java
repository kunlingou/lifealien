package com.kunlinr.mybatis.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kunlinr.mybatis.entity.Librarian;

@Mapper
public interface LibrarianMapper {
	
	Librarian getById(Integer id);
	
	List<Librarian> getAll();

	Librarian getByIdAndName(Integer id, String name);
}
