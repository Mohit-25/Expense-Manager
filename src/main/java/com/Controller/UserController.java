package com.Controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Bean.ResponseBean;
import com.Entity.UserEntity;
import com.Repository.UserRepository;
import com.Service.TokenGeneration;

@CrossOrigin
@RestController
public class UserController {
	
	
	@Autowired
	UserRepository userrepo;
	
	@Autowired
	TokenGeneration gtoken;
	
	
	
	@PostMapping("/signupuser")
	public ResponseEntity<ResponseBean<UserEntity>> addUser(@RequestBody UserEntity user)
	{
		Optional<UserEntity> optional= userrepo.findByEmail(user.getEmail());
		ResponseBean<UserEntity> res=new ResponseBean<>();
		if(optional.isEmpty())
		{
//			String encPassword=passwordencoder.encode(user.getPassword());
//			user.setPassword(encPassword);
			userrepo.save(user);
			
			res.setData(user);
			res.setMsg("Signup Done");
			return ResponseEntity.ok(res);
		}
		else
		{
			res.setData(user);
			res.setMsg("User Already Exists!!");
			return ResponseEntity.unprocessableEntity().body(res);
		}
			
	}
	
	@PostMapping("/loginuser")
	public ResponseEntity<ResponseBean<UserEntity>> loginuser(@RequestBody UserEntity user)
	{
		Optional<UserEntity> optional= userrepo.findByEmailAndPassword(user.getEmail(), user.getPassword());
		ResponseBean<UserEntity> res=new ResponseBean<>();
		if(optional.isEmpty())
		{   
			res.setData(optional.get());
		    res.setMsg("Please Enter Valid Credentials!");
			return ResponseEntity.unprocessableEntity().body(res);
		}
		else
		{
			
			
			String token=gtoken.generateToken(16);
			optional.get().setToken(token);
			userrepo.save(optional.get());
			
			res.setData(optional.get());
			res.setMsg("Success!!");
			return ResponseEntity.ok(res);
		}
	}
	
	@GetMapping("/forgot/{email}")
	public ResponseEntity<ResponseBean<UserEntity>> forgotpassword(@PathVariable("email") String email)
	{
		Optional<UserEntity>optional=userrepo.findByEmail(email);
		ResponseBean<UserEntity> res=new ResponseBean<>();
		
	    res.setMsg("OTP is Sent!");
	    
		if(!optional.isEmpty())
		{
			
			Integer a= (int)(Math.random()*(999999-100000+1)+100000);
//			Integer a = (int) (Math.random()*(max-min+1)+min);   
			optional.get().setOtp(a);
			userrepo.save(optional.get());
			System.out.println(a);
			return ResponseEntity.ok(res);
		}
		else
		{
			return ResponseEntity.unprocessableEntity().body(res);
		}
		
		
	}
	
	@GetMapping("/verify/{otp}")
	public ResponseEntity<ResponseBean<UserEntity>> verify(@PathVariable("otp") Integer otp)
	{
	 UserEntity user=userrepo.findByOtp(otp);
	 
	 ResponseBean<UserEntity> res=new ResponseBean<>();
	 res.setData(user);
	 if(user==null)
	 {   res.setMsg("Invalid OTP");
		 return ResponseEntity.unprocessableEntity().body(res);
	 }
	 else
	 {
		 res.setMsg("Success!");
		 return ResponseEntity.ok(res);
	 }
	}
	

}
