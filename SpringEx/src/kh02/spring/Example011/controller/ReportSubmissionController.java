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
	
	/*   10.2 @RequestParam Annotation을 이용한 업로드 파일 접근
	    : 업로드한 파일을 전달받는 첫 번째 방법은 @RequestParam Annotation이 적용된 MultipartFile 타입의 파라미터를 사용하는 것이다.
	*/
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
	
/*
	  10.3 MultipartHttpServletRequest를 이용한 업로드 파일 접근
	    : 업로드한 파일을 전달 받는 두 번째 방법은 MultipartHttpServletRequest Interface를 사용하는 것이다.
*/	
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
