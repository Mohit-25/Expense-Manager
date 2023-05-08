package com.Dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.Entity.SubCategoryEntity;


public interface UserDao {

	List<SubCategoryEntity> searchCriteria(Integer id);

}
