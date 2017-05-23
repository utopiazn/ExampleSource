package controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import logic.Item;
import logic.Shop;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;


public class IndexController implements Controller { 

	private Shop shopService;
	
	

	public void setShopService(Shop shopService) {
		this.shopService = shopService;
	}


	@Override
	public ModelAndView handleRequest(
				HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		
		//상품 목록 정보 취득
		List<Item> itemList = this.shopService.getItemList();
		
		//모델 생성
		Map<String,Object> model = new HashMap<String,Object>();
		model.put("itemList", itemList);
		
		//반환 값인 ModelAndView 인스턴스 생성
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.setViewName("/WEB-INF/jsp/index.jsp");
		modelAndView.addAllObjects(model);
		
		return modelAndView;
	}

}
