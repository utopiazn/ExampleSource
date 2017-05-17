package kh02.spring.Example008.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class GameInfoController {

	@RequestMapping("/game/info")
	public String gameInfo() {
		return "game/users/info";
	}
	
	@RequestMapping("/game/list")
	public String gameList() {
		return "game/list";
	}
}