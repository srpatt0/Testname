package com.wf.interview.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "interview_table")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "interviewId")
public class interview {
	// all fields will map to col in table
	
	@Id  // primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Auto increment
	private Long interviewId;
	private String interviewName;
	private String interviewerName;	
	private String usersSkills;	
	private Date interviewDate;	
	private String interviewTime;
	private String interviewStatus;
	private String interviewRemarks;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name="interviews_users_table",joinColumns = @JoinColumn(name="interviewId"),
	inverseJoinColumns = @JoinColumn(name="userid"))
	@JoinColumn(name = "interview_fk",referencedColumnName = "interviewId")
	private List<user> users=new ArrayList<user>();
	
}
