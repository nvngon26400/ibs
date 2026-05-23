package com.sbisec.helios.ap.dao;


import com.sbisec.helios.ap.model.Todo;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TodoDao {
	void insert(Todo todo);
    Todo select(int id);
}

