package com.Repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.Entity.SubCategoryEntity;
import com.Entity.VendorEntity;

@Repository
public interface SubCategoryRepository extends CrudRepository<SubCategoryEntity, Integer>{

	SubCategoryEntity findBySubcname(String subcName);
    List<SubCategoryEntity> findAll();
//	SubCategoryEntity findBySubcnameAndCategoryid(String subcname, Integer cn);
	
    SubCategoryEntity findBySubcnameAndCategoryCategoryId(String subcname,Integer categoryId);
	
    @Query(nativeQuery = true, value = "select s.subcname from subcategory s,category c where c.categoryid=s.categoryid and c.userid=3")
//	@Query(nativeQuery = true, value = "SELECT s.subcname FROM user_table u "+
//                                        "JOIN category c ON u.id = c.userid "+
//                                        "JOIN subcategory s ON c.category_id = s.categoryid "+
//                                        "WHERE u.id =:id ")
	List<SubCategoryEntity> getsubcategorbyid(@Param("id") int id);
}
