package com.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import com.Entity.UserEntity;
import com.Repository.UserRepository;
import com.google.gson.Gson;

//@Component
public class AuthTokenFilter implements Filter{

	@Autowired
	UserRepository userrepo;
	
	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest request = (HttpServletRequest)(req);
		String token =  request.getHeader("token");
		
		if(token == null || token.trim().length() != 16)
		{
			
		}
		else
		{
			String userJsonString = new Gson().toJson(token);
			UserEntity userExist= userrepo.findByToken(token).orElse(null);
			
			PrintWriter out = resp.getWriter();
			resp.setContentType("application/json");
			resp.setCharacterEncoding("UTF-8");
			out.print(userJsonString);
			out.flush();
			
			chain.doFilter(req, resp);
		}
	}


}
