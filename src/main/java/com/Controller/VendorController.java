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
import com.Entity.UserEntity;
import com.Entity.VendorEntity;
import com.Repository.UserRepository;
import com.Repository.VendorRepository;

@CrossOrigin
@RestController
public class VendorController {

	@Autowired
	VendorRepository vrepo;
	
	@Autowired
	UserRepository userrepo;
	
	@GetMapping("/vendor/{id}")
	public ResponseEntity<ResponseBean<List<VendorEntity>>> getVendorbyuserid(@PathVariable("id")Integer id)
	{
		  ResponseBean<List<VendorEntity>>res=new ResponseBean<>();
		  
		  List<VendorEntity> list=vrepo.findByUserId(id);
		  
		  res.setData(list);
		  res.setMsg("Vendor fetch successfully!");
		  
		  return ResponseEntity.ok(res);
		
	   
	}
	
	@PostMapping("/vendor")
	public ResponseEntity<ResponseBean<VendorEntity>> addvendor(@RequestBody VendorEntity vendor)
	{
	  Optional<UserEntity> userexist= userrepo.findById(vendor.getUser().getId());
	  Optional<VendorEntity> vendorexist= vrepo.findByUserIdAndVname(vendor.getUser().getId(),vendor.getVname());
	  ResponseBean<VendorEntity> res=new ResponseBean<>();
	  if(userexist.isPresent())
	  {
		  if(vendorexist.isPresent())
		  {
			  res.setData(vendor);
			  res.setMsg("Vendor Already exist!");
			  return ResponseEntity.unprocessableEntity().body(res);
		  }
		  else
		  {
			  vendor.getUser().setId(userexist.get().getId());
			  vrepo.save(vendor);
			  res.setData(vendor);
			  res.setMsg("Vendor added successfully!");
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
	
	@GetMapping("/vendor")
	public ResponseEntity<ResponseBean<List<VendorEntity>>> getAllVendor()
	{
	  ResponseBean<List<VendorEntity>>res=new ResponseBean<>();
	  List<VendorEntity>list=vrepo.findAll();
	  res.setData(list);
	  if(list.isEmpty())
	  {
		  res.setMsg("No Vendor found");
		  return ResponseEntity.unprocessableEntity().body(res);

	  }
	  else
	  {
		  res.setMsg("Vendor fetch successfully!");
		  return ResponseEntity.ok(res);
		  
	  }
	
	}
	
	@DeleteMapping("/vendor/{id}")
	public ResponseEntity<ResponseBean<VendorEntity>> deletevendor(@PathVariable("id") Integer id)
	{
		ResponseBean<VendorEntity>res=new ResponseBean<>();

		Optional<VendorEntity> vendor=vrepo.findById(id);
		
		if(vendor.isPresent())
		{
			
			res.setData(vendor.get());
			res.setMsg("Vendor deleted!");
			vrepo.deleteById(id);

			return ResponseEntity.ok(res);
		}
		else
		{
			res.setData(vendor.get());
			res.setMsg("Vendor Not Exist!");
			return ResponseEntity.unprocessableEntity().body(res);

		}
		
	}

	
}
