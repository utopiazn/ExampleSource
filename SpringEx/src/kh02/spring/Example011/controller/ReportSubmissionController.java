package kh02.spring.Example011.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Controller
public class ReportSubmissionController {

	@RequestMapping(value="/report/submission.do", method= RequestMethod.GET)
	public String form(){
		
		return "report/submissionForm";
	}
	
	@RequestMapping(value="/report/submitReport1.do",method= RequestMethod.POST)
	public String submitReport1(
				@RequestParam("studentNumber") String studentNumber,
				@RequestParam("report") MultipartFile report){
		
		printInfo(studentNumber, report);
		
		return "report/submissionComplete";
	}
	
	private void printInfo(String studentNumber,MultipartFile report){
		
		System.out.println(studentNumber+"가 업로드 한 파일"+ report.getOriginalFilename());
	}
	

	@RequestMapping(value = "/report/submitReport2.do", method = RequestMethod.POST)
	public String submitReport2(MultipartHttpServletRequest request) {
		String studentNumber = request.getParameter("studentNumber");
		MultipartFile report = request.getFile("report");
		printInfo(studentNumber, report);
		return "report/submissionComplete";
	}
	
	@RequestMapping(value = "/report/submitReport3.do", method = RequestMethod.POST)
	public String submitReport3(ReportCommand reportCommand){
		
		printInfo(reportCommand.getStudentNumber(), reportCommand.getReport());
		return "report/submissionComplete";
	}
}
