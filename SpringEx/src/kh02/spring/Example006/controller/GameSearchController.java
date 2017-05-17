package kh02.spring.Example006.controller;

import java.util.ArrayList;
import java.util.List;

import kh02.spring.Example006.service.SearchCommand;
import kh02.spring.Example006.service.SearchResult;
import kh02.spring.Example006.service.SearchService;
import kh02.spring.Example006.service.SearchType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class GameSearchController {
	
	@Autowired
	private SearchService  searchService;
	
	@ModelAttribute("searchTypeList")
	public List<SearchType> referenceSearchTypeList() {
		List<SearchType> options = new ArrayList<SearchType>();
		options.add(new SearchType(1, "전체"));
		options.add(new SearchType(2, "아이템"));
		options.add(new SearchType(3, "캐릭터"));
		return options;
	}
	
	@ModelAttribute("popularQueryList")
	public String[] getPopularQueryList(){
		
		return new String[] {"게임","창천2","위메이드"};
	}
	
	@RequestMapping("/search/main.do")
	public String main(){
		return "search/main";
	}
	
	@RequestMapping("/search/game.do")
	public ModelAndView search(
			@ModelAttribute("command") SearchCommand command){
		
				
		ModelAndView mav  = new ModelAndView("search/game");
		
		System.out.println("검색어="+ command.getQuery());
			
		SearchResult result = searchService.search(command);
		
		mav.addObject("searchResult",result);
		
		return mav;
	}
	
	@ExceptionHandler(NullPointerException.class)
	public String handleNullPointerException(NullPointerException ex){
		
		return "error/nullException";
	}
	
	public void setSearchService(SearchService searchService) {
		this.searchService = searchService;
	}
	
	
}
