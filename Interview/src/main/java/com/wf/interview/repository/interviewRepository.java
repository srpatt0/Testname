/**
 * 
 */
package com.wf.interview.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.wf.interview.entity.interview;

/**
 * @author wellsfargofsd86
 *
 */
public interface interviewRepository extends JpaRepository<interview, Integer>{
	
	public interview findByInterviewName(String technology);
	public List<interview> findAllByInterviewerName(String name);
	public Integer deleteByInterviewName(String technology);
	@Query("SELECT COUNT(x) from interview x")
	public Integer interviewCount();
}
