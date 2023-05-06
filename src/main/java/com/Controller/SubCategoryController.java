package com.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.Bean.ResponseBean;
import com.Entity.CategoryEntity;
import com.Entity.SubCategoryEntity;
import com.Repository.CategoryRepository;
import com.Repository.SubCategoryRepository;

@RestController
public class SubCategoryController {

	@Autowired
	CategoryRepository crepo;

	@Autowired
	SubCategoryRepository subcrepo;

	@PostMapping("/addsubcategory")
	public ResponseEntity<ResponseBean<SubCategoryEntity>> addsubcategory(@RequestBody SubCategoryEntity subcategory) 
	{
		CategoryEntity cat = crepo.findByCname(subcategory.getCategory().getCname()).get();
		SubCategoryEntity subcat = subcrepo.findBySubcname(subcategory.getSubcname());
		
		ResponseBean<SubCategoryEntity> res = new ResponseBean<>();
		
		if (cat == null) {
			subcrepo.save(subcategory);
			res.setData(subcategory);
			res.setMsg("SubCategory added successfully!");
			return ResponseEntity.ok(res);
		} 
		else {
			
			if (subcat == null) {
				
				subcategory.getCategory().setCategoryId(cat.getCategoryId());
				subcrepo.save(subcategory);
				res.setData(subcategory);
				res.setMsg("SubCategory added successfully!");
				return ResponseEntity.ok(res);
			} else {
				res.setData(subcategory);
				res.setMsg("Subcategory already exists!");
				return ResponseEntity.unprocessableEntity().body(res);
			}

		}

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
}
