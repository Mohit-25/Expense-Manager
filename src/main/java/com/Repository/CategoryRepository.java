package com.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.Entity.CategoryEntity;
import com.Entity.VendorEntity;

@Repository
public interface CategoryRepository extends CrudRepository<CategoryEntity, Integer> {

	List<CategoryEntity> findAll();
	CategoryEntity findByCname(String getcName);
	CategoryEntity findByCnameAndUsercatId(String cname,Integer userid);
//    Optional<CategoryEntity> findByVname(Integer id);
}
