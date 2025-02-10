package com.studentresults.ResultController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.studentresults.Results;
import com.studentresults.ResultService.ResultService;

@RestController
@RequestMapping(value = "/Result")
public class ResultController {
	@Autowired
	ResultService rs;
	
	
	@PostMapping(value = "/CalculateMarks")
	public String Cal() {
		return rs.Cal();
	}
	@GetMapping(value = "/GetAll") 
	public List<Results> getAll() {
		return rs.getAll();
	}
	@GetMapping(value = "/Topper") 
	public Results getTopper() {
		return rs.getTopper();
	}
	@GetMapping(value = "/Top3")
	public List<Results> top3() {
		return rs.top3();
	}
	@GetMapping(value ="/Range")
	public List<Results> range() {
		return rs.range();
	}
  }
