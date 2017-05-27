package smboard.board.controller;


import java.io.File;
import java.util.Date;
import java.util.List;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import smboard.board.model.BoardCommentModel;
import smboard.board.model.BoardModel;
import smboard.board.service.BoardService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/board")
public class BoardController {
	
	//ID
	private ApplicationContext context = new ClassPathXmlApplicationContext("/config/applicationContext.xml");
	private BoardService boardService=(BoardService)context.getBean("boardService");
	
	
	//user variable
	//article page variables
	
	private int currentPage=1;
	private int showArticleLimit= 10; 	// change value if want to show more articles by one page
	private int showPageLimit = 10;		// change value if want to show more page links
	private int startArticleNum =0;
	private int endArticleNum=0;
	private int totalNum=0;
	
	//file upload path
	private String uploadPath= getPath()+"SummerBoard/WebContent/img/";
	
	
	public String getPath(){
		String s = this.getClass().getResource("/").getPath();
		String sc = s.substring(0, s.indexOf(".metadata"));
		
		//System.out.println("프로젝트 전까지의 경로: "+sc);
		
		return sc;
	}
	
	@RequestMapping("/list.do")
	public ModelAndView bpardList (HttpServletRequest request, HttpServletResponse reponse){
		
		String type = null;
		String keyword = null;
		
		//set variables from request parameter
		
		if(request.getParameter("page")== null ||
			request.getParameter("page").trim().isEmpty() ||
			request.getParameter("page").equals("0")){
			
			currentPage =1;
		
		}else{
		
			currentPage = Integer.parseInt(request.getParameter("page"));
		}
		
		if(request.getParameter("type") != null){
			
			type = request.getParameter("type").trim();
		}
		
		if(request.getParameter("keyword") != null){
			
			keyword = request.getParameter("keyword").trim();
		}
		

		// expression article variables value
		startArticleNum =(currentPage -1)* showArticleLimit +1;
		endArticleNum = startArticleNum +showArticleLimit -1;
		
		//get boardList and get page html code
		List<BoardModel> boardList;
		
		if(type != null && keyword != null){
			
			boardList = boardService.searchArticle(type, keyword, startArticleNum, endArticleNum);
			
			
			
			totalNum = boardService.getSearchTotalNum(type, keyword);
		}else{
			
			System.out.println("startArticleNum222:"+startArticleNum  );
			System.out.println("endArticleNum222:"+endArticleNum  );
			
			boardList = boardService.getBoardList(startArticleNum, endArticleNum);
			
			totalNum=boardService.getTotalNum();
		}	
		System.out.println("dd"+boardList.size());
		
		StringBuffer pageHtml = getPageHtml(currentPage, totalNum, showArticleLimit, showPageLimit, type, keyword);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("boardList",boardList);
		mav.addObject("pageHtml",pageHtml);
		mav.setViewName("/board/list");
		
		return mav;
	}

	
	// A method for Creationg page html code
	private StringBuffer getPageHtml(int currentPage2, int totalNum2, int showArticleLimit2, int showPageLimit2,
			String type, String keyword) {
		// TODO Auto-generated method stub
		
		StringBuffer pageHtml = new StringBuffer();
		
		int startPage = 0;
		int lastPage =0;
		
		//expression page variables
		startPage = ((currentPage-1)/ showPageLimit)* showPageLimit +1 ;
		lastPage = startPage+showPageLimit -1;
		
		if(lastPage> totalNum/showArticleLimit){
			lastPage = (totalNum /showArticleLimit)+1;
		}
		
		//create page html code
		//if :when on search
		
		if( type==null && keyword ==null){
			
			if(currentPage==1){
				
				pageHtml.append("<span>");
			
			}else{
			
				pageHtml.append("<span><a href=\"list.do?page=" + (currentPage-1) + "\"><이전></a>&nbsp;&nbsp;");
			}
			
			for(int i= startPage ; i<= lastPage ; i++){
				
				if(1 == currentPage){
					
					pageHtml.append(".&nbsp;<strong>");
					pageHtml.append("<a href=\"list.do?page=" +i + "\" class=\"page\">" + i + "</a>");
					pageHtml.append("&nbsp;</strong>");
			
				}else{
				
					pageHtml.append(".&nbsp;<a href=\"list.do?page=" +i + "\" class=\"page\">" + i + "</a>&nbsp;");
				}
				
			}
			
			if(currentPage == lastPage){
				pageHtml.append(".</span>");
			} else {
				pageHtml.append(".&nbsp;&nbsp;<a href=\"list.do?page=" + (currentPage+1) + "\"><다음></a></span>");
			}
			// else: when search	
				
		}else{
				
			if(currentPage == 1){
				
				pageHtml.append("<span>");
			}else{
				pageHtml.append("<span><a href=\"list.do?page=" + (currentPage-1) + "&type=" + type + "&keyword=" + keyword + "\"><이전></a>&nbsp;&nbsp;");
			}
				

			for(int i = startPage ; i <= lastPage ; i++){
				
				if(i == currentPage){
					
					pageHtml.append(".&nbsp;<strong>");
					pageHtml.append("<a href=\"list.do?page=" +i + "&type=" + type + "&keyword=" + keyword + "\" class=\"page\">" + i + "</a>&nbsp;");
					pageHtml.append("&nbsp;</strong>");
				
				}else{
					
					pageHtml.append(".&nbsp;<a href=\"list.do?page=" +i + "&type=" + type + "&keyword=" + keyword + "\" class=\"page\">" + i + "</a>&nbsp;");
				}
				
			}
				
			if(currentPage == lastPage){
			
				pageHtml.append("</span>");
				
			}else{
				
				pageHtml.append(".&nbsp;&nbsp;<a href=\"list.do?page=" + (currentPage+1) + "&type=" + type + "&keyword=" + keyword + "\"><다음></a></span>");
			}	
			
		}
		
		
		return pageHtml;

	}
	
	
	@RequestMapping("/view.do")
	public ModelAndView boardView(HttpServletRequest request){
		
		
		System.out.println("/view.do 이동");
		
		int idx = Integer.parseInt(request.getParameter("idx"));
		
		BoardModel board = boardService.getOneArticle(idx); //get selected article model
		boardService.updateHitcount(board.getHitcount()+1, idx);
		
		List<BoardCommentModel> commentList = boardService.getCommentList(idx); //get comment list
		
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("board",board);
		mav.addObject("commentList",commentList);
		mav.setViewName("/board/view");
		
		return mav;
	}
	
	@RequestMapping("/write.do")
	public String boardWrite(
				@ModelAttribute("BoardModel") BoardModel boardModel){
		
		return "/board/write";
	}
	
	@RequestMapping(value="/write.do", method=RequestMethod.POST)
	public String voardWriteProc(
				@ModelAttribute("BoardModel") BoardModel boardModel,
				MultipartHttpServletRequest request){
		
		MultipartFile file = request.getFile("file");
		String fileName = file.getOriginalFilename();
		File uploadFile = new File(uploadPath+fileName);
		
		//when file exists as same name
		if(uploadFile.exists()){
			
			fileName = new Date().getTime() + fileName;
			uploadFile = new File(uploadPath+fileName);
		}
		
		
		//save upload file to uploadPath
		
		try{
			file.transferTo(uploadFile);
		
		}catch(Exception e){}
		
		boardModel.setFileName(fileName);
		
		//new line code change to <br/> tag
		String content  = boardModel.getContent().replaceAll("/r/n", "<br/>");
		boardModel.setContent(content);
		
		boardService.writeArticle(boardModel);
		
		return "redirect:list.do";
	}
	
	@RequestMapping("/commentWrite.do")
	public ModelAndView commentWriteProc(
				@ModelAttribute("commentModel") BoardCommentModel commentModel){
		
		//new list code change to <br/> tag
		
		
		String content = commentModel.getContent().replaceAll("\r\n", "<br/>");
		
		System.out.println(content);
		
		commentModel.setContent(content);
		
		boardService.writeComment(commentModel);
		
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("idx",commentModel.getLinkedArticleNum());
		mav.setViewName("redirect:view.do");
		
		return mav;
		
	}
	
	@RequestMapping("/modify.do")
	public ModelAndView boardModify(HttpServletRequest request, HttpSession session){
		
		String userId =(String) session.getAttribute("userId");
		int idx = Integer.parseInt(request.getParameter("idx"));
		
		BoardModel board = boardService.getOneArticle(idx);
		
		System.out.println(board);
		
		System.out.println("getContent():"+board.getContent());
		
		
		//<br/> tag change to new line code
		String content = board.getContent().replaceAll("<br/>", "\r\n");
		
		System.out.println("ddd "+content);
		board.setContent(content);
		
		ModelAndView mav = new ModelAndView();
		
		if(!userId.equals(board.getWriterId())){
			
			mav.addObject("errcode","1"); //forbidden connection
			mav.addObject("idx",idx);
			mav.setViewName("redirect:view.do");
		}else{
			
			mav.addObject("board",board);
			mav.setViewName("board/modify");
		}
		
		return mav;
	}

	@RequestMapping(value = "/modify.do",method=RequestMethod.POST)
	public ModelAndView boardModifyProc(
			@ModelAttribute("BoardModel") BoardModel boardModel,
				MultipartHttpServletRequest request){
		
		String orgFileName = request.getParameter("orgFile");
		MultipartFile newFile = request.getFile("newFile");
		String newFileName = newFile.getOriginalFilename();
		
		boardModel.setFileName(orgFileName);
		
		//if: when want to change upload file
		
		if(newFile != null && !newFileName.equals("")){
			
			if(orgFileName != null || !orgFileName.equals("") ){
				
				//remove uploaded file				
				File removeFile = new File(uploadPath + orgFileName);
				removeFile.delete();
				
			}
			
			//create new upload file
			File newUploadFile = new File(uploadPath + newFileName);
			
			try{
				newFile.transferTo(newUploadFile);
			}catch(Exception e){
				e.printStackTrace();
			}
			
			boardModel.setFileName(newFileName);
		}
		
		//new line code change to <br/> tag
		String content = boardModel.getContent().replaceAll("\r\n", "<br/>");
		boardModel.setContent(content);
		
		boardService.modifyArticle(boardModel);
		
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("idx",boardModel.getIdx());
		mav.setViewName("redirect:/board/view.do");
		
		return mav;
		
		
	}
	
	@RequestMapping("/delete.do")
	public ModelAndView boardDelete(
				HttpServletRequest request,
				HttpSession session){
				
	
		String userId=(String)session.getAttribute("userId");
		
		int idx = Integer.parseInt(request.getParameter("idx"));
		
		BoardModel board = boardService.getOneArticle(idx);
				
		
		ModelAndView mav = new ModelAndView();
		
		
		if(!userId.equals(board.getWriterId())){
			
			mav.addObject("errCode", "1");	// it's forbidden connection
			mav.addObject("idx", idx);
			mav.setViewName("redirect:view.do");
			
		}else{
			
			// if: when the article has upload file - remove that
			if(board.getFileName() != null){
				
				File removefile = new File(uploadPath + board.getFileName());
				removefile.delete();
			}
			
			boardService.deleteArticle(idx);
			
			mav.setViewName("redirect:list.do");
			
		}
		
		
		return mav;
		
	
	}
	
	@RequestMapping("/commentDelete.do")	
	public ModelAndView commendDelete(
				HttpServletRequest request, 
				HttpSession session){
		
		
		int idx = Integer.parseInt(request.getParameter("idx"));
		int linkedArticleNum = Integer.parseInt(request.getParameter("linkedArticleNum"));
		
		String userId = (String) session.getAttribute("userId");
		BoardCommentModel comment = boardService.getOneComment(idx);
		
		ModelAndView mav = new ModelAndView();
		
		if(!userId.equals(comment.getWriterId())){
			mav.addObject("errCode", "1");
		} else {
			boardService.deleteComment(idx);
		}		
				
		mav.addObject("idx", linkedArticleNum); // move back to the article
		mav.setViewName("redirect:view.do");
		
		return mav;
	}	
	
	@RequestMapping("/recommend.do")
	public ModelAndView udateRecommendCount(
				HttpServletRequest request, 
				HttpSession session){
		
		int idx = Integer.parseInt(request.getParameter("idx"));
		String userId = (String) session.getAttribute("userId");
		BoardModel board = boardService.getOneArticle(idx);
		
		ModelAndView mav = new ModelAndView();
		
		if(userId.equals(board.getWriterId())){
			
			mav.addObject("crrCode",1);
			
		}else{
			
			boardService.updateRecommendCount(board.getRecommendcount()+1,idx);
		}
		
		mav.addObject("idx",idx);
		mav.setViewName("redirect:/board/view.do");
		
		return mav;
	}
		
	
}




































