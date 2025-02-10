package com.studentresults.ResultRepository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.studentresults.Results;

public interface ResultRepository extends JpaRepository<Results, Integer>{
	
	@Query(value = "select * from results", nativeQuery = true)
	public List<Results> getAll();
	@Query(value = "select total from results",nativeQuery = true) 
	public String Cal(List<Results> res);

}
