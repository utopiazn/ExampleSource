package kh02.spring.Example001.service;

/*
: SPRING MVC는 HTML 폼에 입력한 데이터를 Java빈 객체를 이용해서 전달 받을 수 있도록 하고 있다. 예을 들어, 다음과 같이 HTML 폼의
항목 이름과 Java빈 Class의 프로퍼티 이름이 일치할 경우 폼에 입력한 값을 해당 Java빈 Class의 프로퍼티 값으로 설정해주는 기능을
제공하고 있다.

*/
public class NewArticleCommand {
	
	private String title;
	private String content;
	private int parentId;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getParentId() {
		return parentId;
	}
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
	
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		
		return "NewArticleCommand [content="+content+",parentId="+parentId+",title="+title+"]";
		//return super.toString();
	}
	
	


}
