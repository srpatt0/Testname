package com.wf.interview.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.wf.interview.entity.user;

// public interface StudentRepository extends CrudRepository<T, ID>{
// @Component
@Repository
public interface userRepository 
					extends JpaRepository<user, Integer>, UserRepositoryCustom{
	// Basic crud functionalities are already exposed
	List<user> findByEmail(String email);
	
	// JPQL
	// Student : Java class
	// :<variable name>
	@Query("select s from User s where s.userId=:userId and s.email=:userEmail")
	List<user> findByComplexRequirement(@Param("userId") Integer userId, @Param("userEmail") String userEmail);
	
	public Integer deleteByMobile(String mobile);
	public user findByMobile(String mobile);
}

/**
 * Custom implementation
 * 1. Naming Convention : add a appropriatly named method in interface, implementation will be provided on the fly
 * 2. Query implementation
 * 3. Custom Interface with implementation which can be plugged with existing interface
 */












