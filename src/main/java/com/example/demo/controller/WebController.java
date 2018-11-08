package com.example.demo.controller;

import com.example.demo.model.Player;

import com.example.demo.model.SearchCommand;
import com.example.demo.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.List;

@Controller
public class WebController {
	private String appMode;

	@Autowired
	PlayerService sp;

	@Autowired
	public WebController(Environment environment){
		appMode = environment.getProperty("app-mode");
	}



	@RequestMapping(value="/changeSituation", method=RequestMethod.POST, params="situation=PP")
	public String powerplay(ModelMap model) {
		model.addAttribute("situation", "Power Play");
		return "index";
	}


	@RequestMapping(value="/changeSituation", method=RequestMethod.POST, params="situation=5V5")
	public String evenstrength(ModelMap model) {
		model.addAttribute("situation", "Even Strength 5 v 5");
		return "index";
	}

	@RequestMapping(value = "/player/{playerId}")
	public String getPlayer(@PathVariable String playerId, ModelMap model){

		Player player = null;

		player = sp.findPlayerByID(Integer.parseInt(playerId));

		model.addAttribute("player", player);
		return "player";
	}
	@ModelAttribute("searchCommand")
	public SearchCommand getSearchCommand(ModelMap model) {
		return new SearchCommand();
	}
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public String Search(@ModelAttribute("searchCommmand") SearchCommand searchCommand,
								ModelMap model) {

		String searchString = searchCommand.getSearchString();

		List<Player> players = null;
		if(searchString != null){
			players = sp.findPlayersByName(searchString);
		}

		model.addAttribute("players", players);

		return "search";
	}

	@RequestMapping(value = "/")
	public String indexForm(Model model){
		model.addAttribute("situation", "Even Strength 5 v 5");
		return "index";
	}

	@ModelAttribute("player")
	public Player getPlayer(ModelMap model) {
		return new Player();
	}

	@RequestMapping(value = "/addPlayer", method = RequestMethod.POST)
	public String addPlayer(@ModelAttribute("player") Player player,
									 ModelMap model) {
		model.addAttribute("name", player.getName());
		model.addAttribute("offskills", player.getOffskills());
		model.addAttribute("defskills", player.getDefskills());

		return "index";
	}
}