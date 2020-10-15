package com.wf.interview.service;

import java.util.List;

import com.wf.interview.dto.userDto;
import com.wf.interview.entity.user;

public interface userService {
	
	public userDto add(userDto userDto);
	public userDto delete(String mobile);
	public List<userDto> getAll();  
	public userDto getById(Integer id);
	public boolean isPresent(String mobile);
	public user update(user user);
	
}
