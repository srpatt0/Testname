package com.wf.interview.repository;

import java.util.List;

import com.wf.interview.entity.User;

public interface UserRepositoryCustom {
	List<User> veryComplexBusinessLogicQuery(String email);
}
