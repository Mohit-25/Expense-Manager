package com.Repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.Entity.SubCategoryEntity;

@Repository
public interface SubCategoryRepository extends CrudRepository<SubCategoryEntity, Integer>{

	SubCategoryEntity findBySubcname(String subcName);
    List<SubCategoryEntity> findAll();
	Optional<SubCategoryEntity> findBySubcnameAndCategory(String subcname, Integer cn);
	
}
