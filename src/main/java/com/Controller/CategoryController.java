package com.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.Bean.ResponseBean;
import com.Entity.CategoryEntity;
import com.Entity.SubCategoryEntity;
import com.Entity.UserEntity;
import com.Entity.VendorEntity;
import com.Repository.CategoryRepository;
import com.Repository.SubCategoryRepository;
import com.Repository.UserRepository;

@CrossOrigin
@RestController
public class CategoryController {
	
	@Autowired
	CategoryRepository crepo;
	

	@Autowired
	SubCategoryRepository subcrepo;
	
	@Autowired
	UserRepository userrepo;
	
	
	@GetMapping("/category")
	public ResponseEntity<ResponseBean<List<CategoryEntity>>> getAllCategory()
	{
		  ResponseBean<List<CategoryEntity>>res=new ResponseBean<>();
		  List<CategoryEntity> list=crepo.findAll();
		  
		  res.setData(list);
		  res.setMsg("Category fetch successfully!");
		  
		  return ResponseEntity.ok(res);
		
	   
	}
	
	
	
	@PostMapping("/category")
	public ResponseEntity<ResponseBean<CategoryEntity>> addcategory(@RequestBody CategoryEntity category) 
	{
		 
		  Optional<UserEntity> userexist= userrepo.findById(category.getUsercat().getId());
//		  Optional<CategoryEntity> catexist=crepo.findByCnameAndUserid(category.getCname(),category.getUsercat().getId());
		  Optional<CategoryEntity> catexist=crepo.findByCname(category.getCname());
		  ResponseBean<CategoryEntity> res=new ResponseBean<>();
		  if(userexist.isPresent())  
		  {
			  if(catexist.isPresent())
			  {
				  res.setData(category);
				  res.setMsg("Category Already exist!");
				  return ResponseEntity.unprocessableEntity().body(res);
			  }
			  else
			  {
				  category.getUsercat().setId(userexist.get().getId()); 
				  crepo.save(category);
				  res.setData(category);
				  res.setMsg("Category added successfully!");
				  return ResponseEntity.ok(res);
			  }
			  
		  }
		  else
		  {
			  res.setData(null);
			  res.setMsg("User not exist!");
			  return ResponseEntity.unprocessableEntity().body(res);
		  }
		  
	 }
////	@PutMapping("/employee")
////	public EmployeeEntity updateEmployee(@RequestBody EmployeeEntity employee)
////	{ 
////		
////	}
//	
//	@DeleteMapping("/student/{studentId}")
//	public StudentEntity deleteStudent(@PathVariable("studentId") Integer Id)
//	{
//		StudentEntity student=studentrepo.findByStudentId(Id);
//		
//		studentrepo.deleteById(Id);
//		return student;
//	}
	
	

}
