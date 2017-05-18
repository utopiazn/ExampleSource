package kh02.spring.Example011.controller;

import org.springframework.web.multipart.MultipartFile;


/*10.4 커맨드 객체를 통한 업로드 파일 접근

: 커맨드 객체를 이용해도 업로드 한 파일을 전달받을 수 있다. 단지 커맨드 Class에 파라미터와 동일한 이름의 MultipartFile 타입
  프로퍼티만 추가해주기만 하면 된다. 예를 들어, 업로드 파일의 파라미터 이름이 "report"인 경우, 다음과 같이 "report" 프로퍼티를
  커맨드 Class에 추가해 주면 된다.
*/
public class ReportCommand {
	
	private String studentNumber;
	private MultipartFile report;
	
	public String getStudentNumber() {
		return studentNumber;
	}
	public void setStudentNumber(String studentNumber) {
		this.studentNumber = studentNumber;
	}
	public MultipartFile getReport() {
		return report;
	}
	public void setReport(MultipartFile report) {
		this.report = report;
	}
	
	
}
