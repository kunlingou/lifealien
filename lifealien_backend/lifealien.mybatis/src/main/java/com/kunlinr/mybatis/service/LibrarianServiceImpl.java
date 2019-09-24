package com.kunlinr.mybatis.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kunlinr.mybatis.dao.LibrarianMapper;
import com.kunlinr.mybatis.entity.Librarian;

/**
 * @author kunlingou
 * @date 2019/09/24
 */
@Service
public class LibrarianServiceImpl implements LibrarianService {

    @Autowired
    private LibrarianMapper librarianMapper;

    @Override
    public Librarian getById(Integer id) {
        return librarianMapper.getById(id);
    }

    @Override
    public List<Librarian> getAll() {
        return librarianMapper.getAll();
    }

    @Override
    public Librarian getByIdAndName(Integer id, String name) {
        return librarianMapper.getByIdAndName(id, name);
    }
}
