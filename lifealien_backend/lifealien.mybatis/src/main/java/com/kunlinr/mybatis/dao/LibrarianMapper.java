package com.kunlinr.mybatis.dao;

import java.util.List;

import com.kunlinr.mybatis.entity.Librarian;

/**
 * @author kunlingou
 * @date 2019/09/24
 */
public interface LibrarianMapper {

    /**
     * 通过id获取对象
     * 
     * @param id
     * @return
     */
    Librarian getById(Integer id);

    /**
     * 获取所有数据
     * 
     * @return
     */
    List<Librarian> getAll();

    /**
     * 通过id和name获取对象
     * 
     * @param id
     * @param name
     * @return
     */
    Librarian getByIdAndName(Integer id, String name);
}
