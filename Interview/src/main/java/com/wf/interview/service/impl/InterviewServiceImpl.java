package com.wf.interview.service.impl;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.wf.interview.repository.interviewRepository;
import com.wf.apps.interviewApp.dto.interviewConvertor;
import com.wf.interview.entity.interview;
import com.wf.interview.dto.interviewCountDto;
import com.wf.interview.dto.interviewDto;
import com.wf.interview.entity.Interview;
import com.wf.interview.entity.User;
import com.wf.interview.service.InterviewSerivce;

@Component
public class InterviewServiceImpl implements InterviewSerivce {
	
	@Autowired
	public interviewRepository interviewrepository;

	@Override
	public List<Interview> getAllInterviews() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Interview getInterview(String interviewName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Interview getInterview(String interviewName, String interviewerName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> getAllAttendees(Interview interview) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Interview addAttendee(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Interview updateInterview(Interview interview) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Interview deleteInterview(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Interview getInterview(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public interviewCountDto getInterviewsCount() {
		// TODO Auto-generated method stub
				interviewCountDto countDto=new interviewCountDto();
				countDto.setNoOfInterviews(interviewrepository.interviewCount());
				return countDto;
	}

	@Override
	public interviewDto getInterview(String interviewName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public interviewDto getAllAttendees(String intName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public interviewDto addAttendee(String interviewName, Integer userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public interviewDto updateInterview(String intName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public interviewDto deleteInterview(String intName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public interviewDto getInterview(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public interviewDto modifyStatus(String intName, String status) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isAttendeeAddedToInterview(String intName, Integer userId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public interviewCountDto getInterviewCount() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public interviewDto getInterview(String interviewName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public interviewDto getInterview(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public interviewDto addInterview(interviewDto interviewDto) {
		// TODO Auto-generated method stub
		interview interview=interviewConvertor.interviewDtoToInterviewConvertor(interviewDto);
		interview.setDate(LocalDate.now());
		interview.setTime(LocalTime.now());
		interviewrepository.save(interview); 
		return interviewConvertor.interviewToInterviewDtoConvertor(interview);
	}

}
