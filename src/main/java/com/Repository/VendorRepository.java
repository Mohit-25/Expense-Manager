package com.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.Entity.VendorEntity;

@Repository
public interface VendorRepository extends CrudRepository<VendorEntity, Integer>{

	List<VendorEntity> findAll();

	Optional<VendorEntity> findByUserIdAndVname(Integer id,String name);

	List<VendorEntity> findByUserId(Integer id);
	 
}
