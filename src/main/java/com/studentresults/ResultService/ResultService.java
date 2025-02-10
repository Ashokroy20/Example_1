package com.studentresults.ResultService;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.studentresults.MarkSheetPojo;
import com.studentresults.Results;
import com.studentresults.StudentPojo;
import com.studentresults.StudentDao.ResultDao;

@Service
public class ResultService {
	@Autowired
	ResultDao rd;
	@Autowired
	RestTemplate rt;
	
	
	public String Cal() {
		String url1 = "http://localhost:1141/Student/GetAll";
		ResponseEntity<List<StudentPojo>> student = rt.exchange(url1,HttpMethod.GET,null,
				new ParameterizedTypeReference<List<StudentPojo>>() {});
		List<StudentPojo> students = student.getBody();
				
		String url2 = "http://localhost:1111/Marks/GetAll";
		ResponseEntity<List<MarkSheetPojo>> marksheet = rt.exchange(url2,HttpMethod.GET,null,
				new ParameterizedTypeReference <List<MarkSheetPojo>> () {});
		List<MarkSheetPojo> marksheets = marksheet.getBody();
		
		List<Results> results = new ArrayList<>();
		
		
	   for(StudentPojo student1 : students) {
		  for(MarkSheetPojo marksheet1 : marksheets) {
			  if(student1.getRollNumber() == marksheet1.getRollNumber()) {
				  
				  Results result = new Results();
				  
				  result.setRollNumber(student1.getRollNumber());
				  result.setName(student1.getName());
				  int totalMarks = (marksheet1.getSem1Total()+marksheet1.getSem2total()) /2;
				  if(student1.getAttendance()>=90) {
					  totalMarks = totalMarks+5;
				  }
				  result.setTotal(totalMarks);
				  result.setPercentage(totalMarks*100/200);
				  results.add(result);
			  }
		  }
	   }
		  return rd.Cal(results);
	}
	public List<Results> getAll() {
		return rd.getAll();
	}
	public Results getTopper() {
		List<Results> get = rd.getAll();
		Results topper = get.stream().max(Comparator.comparing(Results::getTotal)).get();
		return topper;
	}
	public List<Results> top3() {
		List<Results> top = rd.getAll();
		List<Results> top3 = top.stream().sorted(Comparator.comparingInt(Results::getTotal).reversed()).limit(3).toList();
		return top3;
	}
	public List<Results> range() {
		List<Results> ran = rd.getAll();
		List<Results> rang = ran.stream().filter(x-> x.getPercentage()>50 && x.getPercentage()<90).collect(Collectors.toList());
		return rang;
	}
}

