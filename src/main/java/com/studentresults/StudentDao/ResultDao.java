package com.studentresults.StudentDao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.studentresults.Results;
import com.studentresults.ResultRepository.ResultRepository;

@Repository
public class ResultDao {
	@Autowired
	ResultRepository rr;
	
	public String Cal(List<Results> res) {
		 rr.saveAll(res);
		 return "Saved";
	}
	public List<Results> getAll() {
		return rr.findAll();
	}
	public List<Results> getTopper() {
		return rr.findAll();
	}
}
