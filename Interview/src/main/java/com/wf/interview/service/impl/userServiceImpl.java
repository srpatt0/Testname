package com.wf.interview.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.wf.interview.dto.userConvertor;
import com.wf.interview.dto.userDto;
import com.wf.interview.entity.user;
import com.wf.interview.repository.userRepository;
import com.wf.interview.repository.interviewRepository;
import com.wf.interview.service.userService;

// @Component
@Service
@Transactional
public class userServiceImpl implements userService {

	
	@Autowired
	userRepository urepository;
	
	@Autowired
	interviewRepository irepository;
	
	@Override
	public List<userDto> getAll() {
		List<userDto> list=new ArrayList<userDto>();
		// TODO Auto-generated method stub
		//System.out.println(userrepository.findAll().toString());
		for(user user:urepository.findAll())
			list.add(userConvertor.userToUserDtoConverter(user));
			
		return list;
	}

	@Override
	public userDto getById(Integer id) {
		// TODO Auto-generated method stub
		user user=urepository.findById(id).orElse(null);
		if(user==null)
			return null;
		return userConvertor.userToUserDtoConverter(user);
	}

	@Override
	public userDto add(userDto userDto) {
		// TODO Auto-generated method stub
		user user=userConvertor.userDtoToUserConverted(userDto);
		return userConvertor.userToUserDtoConverter(urepository.save(user));
	}

//	@Override
//	public User update(User user) {
//		// TODO Auto-generated method stub
//		return this.repository.save(user); // save & update
//	}

	
	@Override
	public userDto delete(String mobile) {
		// TODO Auto-generated method stub
		user user=urepository.findByMobile(mobile);
		if(user==null)
			return null;
		user.getInterviews().forEach(u->u.getUsers().remove(user));
		irepository.saveAll(user.getInterviews());
		urepository.deleteByMobile(mobile);
		return userConvertor.userToUserDtoConverter(user);
	}

	@Override
	public boolean isPresent(String mobile) {
		// TODO Auto-generated method stub
				if(urepository.findByMobile(mobile)==null)
					return false;
				return true;
	}

	@Override
	public user update(user user) {
		// TODO Auto-generated method stub
		return null;
	}

}
