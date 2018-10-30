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

	@RequestMapping(value = "/player/{playerId}")
	public String getPlayer(@PathVariable String playerId, ModelMap model){

		Player player = null;

		player = sp.findPlayerByID(Integer.parseInt(playerId));

		model.addAttribute("player", player);
		return "player";
	}
	@RequestMapping(value = "/search")
	public String Search(@ModelAttribute SearchCommand searchCommand,
								ModelMap model) {

		String searchString = searchCommand.getSearchString();

		List<Player> players = null;
		if(searchString != null){
			players = sp.findPlayersByName(searchString);
		}

		model.addAttribute("players", players);

		return "search";
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String indexForm(Model model){
		model.addAttribute("player", new Player());
		model.addAttribute("searchCommand", new SearchCommand());
		return "index";
	}

	@RequestMapping(value = "/addPlayer", method = RequestMethod.POST)
	public String addPlayer(@ModelAttribute Player player,
									 ModelMap model) {
		model.addAttribute("name", player.getName());
		model.addAttribute("offskills", player.getOffskills());
		model.addAttribute("defskills", player.getDefskills());

		return "player";
	}
}