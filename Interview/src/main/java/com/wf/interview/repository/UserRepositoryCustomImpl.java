package com.wf.interview.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;

import com.wf.interview.entity.User;


public class UserRepositoryCustomImpl implements UserRepositoryCustom{

	// special bean exposed by JPA
	@Autowired
	private EntityManager entityManager;
	
	@Override
	public List<User> veryComplexBusinessLogicQuery(String email) {
		// TODO Auto-generated method stub
		return null;
		
	}

}
