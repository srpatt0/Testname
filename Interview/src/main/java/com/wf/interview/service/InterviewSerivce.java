package com.wf.interview.service;

import java.util.Date;
import java.util.List;

import com.wf.interview.dto.interviewDto;
import com.wf.interview.dto.interviewCountDto;
import com.wf.interview.entity.interview;
import com.wf.interview.entity.user;

public interface InterviewSerivce {	
	
	public List<interviewDto> getAllInterviews();  //9
	public interviewDto getInterview(String interviewName); //7
	public interviewDto getAllAttendees(String intName);  //11
	public interviewCountDto getInterviewsCount();  //8	
	public interviewDto addAttendee(String interviewName,Integer userId);  //10
	public interviewDto updateInterview(String intName);  //6
	public interviewDto deleteInterview(String intName);   //5
	public interviewDto getInterview(Long id);
	public interviewDto addInterview(interviewDto interviewDto);
	public interviewDto modifyStatus(String intName, String status);
	public boolean isAttendeeAddedToInterview(String intName,Integer userId);
	public interviewCountDto getInterviewCount();
}
