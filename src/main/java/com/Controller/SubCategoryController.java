package com.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.Bean.ResponseBean;
import com.Dao.UserDao;
import com.Dao.UserDaoImpl;
import com.Entity.CategoryEntity;
import com.Entity.SubCategoryEntity;
import com.Repository.CategoryRepository;
import com.Repository.SubCategoryRepository;

@CrossOrigin
@RestController
public class SubCategoryController {

	@Autowired
	CategoryRepository crepo;
	
	@Autowired
	UserDaoImpl ud;

	@Autowired
	SubCategoryRepository subcrepo;

	@PostMapping("/subcategory")
	public ResponseEntity<ResponseBean<SubCategoryEntity>> addsubcategory(@RequestBody SubCategoryEntity subcategory) 
	{
		//CategoryEntity cat = crepo.findByCname(subcategory.getCategory().getCname());
//		Optional<SubCategoryEntity> subcat = subcrepo.findBySubcnameAndCategory(subcategory.getSubcname(),subcategory.getCategory().getCategoryId());
//		SubCategoryEntity subcat= subcrepo.findBySubcnameAndCategoryid(subcategory.getSubcname(),subcategory.getCategory().getCategoryId());
//		CategoryEntity cat2=crepo.findByCname(subcategory.getCategory().getCname());
//		SubCategoryEntity subcat= subcrepo.findBySubcname(subcategory.getSubcname());
		SubCategoryEntity subcat= subcrepo.findBySubcnameAndCategoryCategoryId(subcategory.getSubcname(),subcategory.getCategory().getCategoryId());
		
		ResponseBean<SubCategoryEntity> res = new ResponseBean<>();
//		if (cat == null) {
//			subcrepo.save(subcategory);
//			res.setData(subcategory);
//			res.setMsg("SubCategory added successfully1!");
//			return ResponseEntity.ok(res);
//		} 
//		else {
			
			if (subcat==null) {
				
//				subcategory.getCategory().setCategoryId(cat.getCategoryId());
				subcrepo.save(subcategory);
				res.setData(subcategory);
				res.setMsg("SubCategory added successfully2!");
				return ResponseEntity.ok(res);
			} else {
			
				res.setData(null);
				res.setMsg("Subcategory already exists!");
				return ResponseEntity.unprocessableEntity().body(res);
			}

//		}

	}
	
	@GetMapping("subcategory")
	public ResponseEntity<ResponseBean<List<SubCategoryEntity>>> getallsubCategory()
	{
	  ResponseBean<List<SubCategoryEntity>>res=new ResponseBean<>();
	  List<SubCategoryEntity>list=subcrepo.findAll();
	  res.setData(list);
	  if(list.isEmpty())
	  {
		  res.setMsg("No Subcategory found");
		  return ResponseEntity.unprocessableEntity().body(res);

	  }
	  else
	  {
		  res.setMsg("Subcategory fetch successfully!");
		  return ResponseEntity.ok(res);
		  
	  }
	
	}
	
	@GetMapping("/subcategory/{id}")
	public ResponseEntity<ResponseBean<List<SubCategoryEntity>>> getCategorybyuserid(@PathVariable("id") int id)
	{
		  ResponseBean<List<SubCategoryEntity>>res=new ResponseBean<>();
		  
         List<SubCategoryEntity> list= subcrepo.scategory(id);		
//		  List<SubCategoryEntity> list =ud.searchCriteria(id);
		  
		  res.setData(list);
		  res.setMsg("SubCategory fetch successfully!");
		  
		  return ResponseEntity.ok(res);
		
	   
	}
}
