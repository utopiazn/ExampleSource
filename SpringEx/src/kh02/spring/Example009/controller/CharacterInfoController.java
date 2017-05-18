package kh02.spring.Example009.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/game/user/{userId}")  //userId  변수이름 동일하게
public class CharacterInfoController {
	
	@RequestMapping("/characters/{characterId}")  //characterId  변수이름 동일하게
	public String characterInfo(
				@PathVariable String userId,
				@PathVariable int characterId,ModelMap model){
		
		
		model.addAttribute("userId",userId);
		model.addAttribute("characterId",characterId);
				
		return "character/users/info";
	}

}
