package com.Repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.Entity.UserEntity;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Integer>{

	Optional<UserEntity> findByEmail(String email);
	Optional<UserEntity> findByEmailAndPassword(String email, String password);
	UserEntity findByOtp(Integer otp);
   Optional<UserEntity> findByToken(String token);

}
