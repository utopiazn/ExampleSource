package controller;

import java.util.HashMap;

import java.util.Map;

import logic.Item;
import logic.Shop;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DetailController {
	
	private Shop shopService;
	             
	
	public void setShopService(Shop shop){
		this.shopService = shop;
	}
	
	@RequestMapping
	public ModelAndView detailTem(Integer itemId){
		
		
		//선택된 상품 ID에서 상품 정보를 취득
		Item item = this.shopService.getItemByItemId(itemId);
		
		//모델 생성
		Map<String,Object> model = new HashMap<String,Object>();
		model.put("item", item);
		
		//반환 값인 ModleandView 인스턴스를 생성		
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.setViewName("detail");
		modelAndView.addAllObjects(model);
		
		return modelAndView;
	}
	
}
