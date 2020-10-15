package com.wf.interview.controller;

import java.time.LocalDateTime;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.function.EntityResponse;

import com.wf.apps.interviewApp.service.interviewService;
import com.wf.apps.interviewApp.service.userService;
import com.wf.interview.controller.*;
import com.wf.interview.dto.interviewCountDto;
import com.wf.interview.dto.interviewDto;
import com.wf.interview.entity.Interview;
import com.wf.interview.entity.User;
import com.wf.interview.exceptions.StudentNotFoundException;
import com.wf.interview.exceptions.customExceptions;
import com.wf.interview.service.InterviewSerivce;
import com.wf.interview.service.impl.InterviewServiceImpl;
import com.wf.interview.service.impl.UserServiceImpl;
import com.wf.training.cruddemo.exception.model.ExceptionResponse;


@RestController
@RequestMapping("/api")
public class InterviewController {

	@Autowired
	UserServiceImpl userservice;
	@Autowired
	InterviewServiceImpl interviewservice;	

	
	// THis method get all Interviewers
	
	@PostMapping("/interviews")
	public ResponseEntity<interviewDto> addInterview(@Valid @RequestBody interviewDto interviewDto,BindingResult result) {
		
	
		if(result.hasErrors())
		{
			customExceptions exception=new customExceptions();
			for(FieldError err:result.getFieldErrors())
			{
				if(exception.getErrorMessage()==null) {
				exception.setErrorCode(err.getCode());
				exception.setErrorMessage(err.getDefaultMessage());
				}
				else
				{
					exception.setErrorCode(exception.getErrorCode()+" || "+err.getCode());
					exception.setErrorMessage(exception.getErrorMessage()+" || "+err.getDefaultMessage());					
				}
			}
			exception.setErrorTimeStamp(LocalDateTime.now());
			throw exception;
		}
		
		if(interviewDto.getUserSkills() == null || interviewDto.getUserSkills().size() == 0 || interviewDto.getUserSkills().contains(null) || interviewDto.getUserSkills().contains(""))
		{
			throw new customExceptions(LocalDateTime.now(),"User Skill Error","User Skills should'nt be null or empty or blank");
		}
		return new ResponseEntity<interviewDto>(interviewservice.saveInterview(interviewDto),HttpStatus.OK);
	}
	
	@GetMapping("/interviewers")
	public ResponseEntity<List<Interview>> getAllInterviews() {
		List<Interview> interview = this.service.getAllInterviews();
		
		ResponseEntity<List<Interview>> response = 
				new ResponseEntity<List<Interview>>(interview, HttpStatus.OK);
		return response;
	}
	
	//This method is useful to search by ID
	
	@GetMapping("/interviewers{id}")
	public ResponseEntity<Interview> getInterviewById(@PathVariable("id") Long id) {
	Interview interview = this.service.getInterview(id);
		
		if(interview == null) {
			throw  new StudentNotFoundException("Student not Found with Id-" + id);
		}
		
		ResponseEntity<Interview> response = 
				new ResponseEntity<Interview>(interview, HttpStatus.OK);
		return response;
	}
	
	// THis method add all Attendee
	
	@PostMapping("/interviewers")
	public ResponseEntity<Interview> addAttendee(@RequestBody User interview) {
		Interview addedUser = this.service.addAttendee(interview);
		ResponseEntity<Interview> response = 
				new ResponseEntity<Interview>(addedUser, HttpStatus.OK);
		return response;
	}
	
	// THis method add all update interview
	
	@PutMapping("/modifyStatus/{interviewName}/{status}")
	public ResponseEntity<interviewDto> modifyInterviewStatus(@PathVariable("interviewName") String interviewName,@PathVariable("status") String status)
	{
		interviewDto interview=interviewservice.getInterview(interviewName);
		if(interview==null)
			throw new customExceptions(LocalDateTime.now(),"Interview Exception","Interview doesnt exist");
		return new ResponseEntity<interviewDto>(interviewservice.modifyStatus(interviewName,status),HttpStatus.OK);
	}
	
	@PutMapping("/interviewers")
	public ResponseEntity<Interview> editStudent(@RequestBody Interview interview) {
		Interview updatedStudent = this.service.updateInterview(interview);
		ResponseEntity<Interview> response = 
				new ResponseEntity<Interview>(updatedStudent, HttpStatus.OK);
		return response;
	}
	
	// THis method add all delete interview
	
	@DeleteMapping("/interviewers{id}")
	public ResponseEntity<Interview> deleteStudent(@PathVariable("id") Long id) {
		Interview interview = this.service.deleteInterview(id);
		if(interview == null) {
			throw  new StudentNotFoundException("interview not Found with Id-" + id);
		}
		ResponseEntity<Interview> response = 
				new ResponseEntity<Interview>(interview, HttpStatus.OK);
		return response;
	}
	
	@PutMapping("/modifyStatus/{interviewName}/{status}")
	public ResponseEntity<interviewDto> modifyInterviewStatus(@PathVariable("interviewName") String interviewName,@PathVariable("status") String status)
	{
		interviewDto interview=interviewservice.getInterview(interviewName);
		if(interview==null)
			throw new customExceptions(LocalDateTime.now(),"Interview Exception","Interview doesnt exist");
		return new ResponseEntity<interviewDto>(interviewservice.modifyStatus(interviewName,status),HttpStatus.OK);
	}
	
	@GetMapping("/interviewCount")
	public ResponseEntity<interviewCountDto> getInterviewCount()
	{
		return new ResponseEntity<interviewCountDto>(interviewservice.getInterviewsCount(),HttpStatus.OK);
	}
	
	// Exception Handler Method
	@ExceptionHandler(InterviewrNotFoundException.class)
	public ResponseEntity<ExceptionResponse> handler(InterviewrNotFoundException ex) {
		ExceptionResponse exResponse =
				new ExceptionResponse();
		ResponseEntity<ExceptionResponse> response = 
				new ResponseEntity<ExceptionResponse>(exResponse, HttpStatus.NOT_FOUND);
		return response;
	}
	
	// Exception Handler Method
	@ExceptionHandler(Exception.class)
	public ResponseEntity<EntityResponse> handler(Exception ex) {
		ExceptionResponse exResponse =
				new EntityResponse();
		ResponseEntity<ExceptionResponse> response = 
				new ResponseEntity<ExceptionResponse>(exResponse, HttpStatus.BAD_REQUEST);
		return response;
	}

		private String interviewName;
	
	
}
