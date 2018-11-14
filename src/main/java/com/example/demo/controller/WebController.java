package com.example.demo.controller;

import com.example.demo.model.Player;

import com.example.demo.model.SearchCommand;
import com.example.demo.model.Team;
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
@SessionAttributes("team")
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
		return indexForm(model);
	}


	@RequestMapping(value="/changeSituation", method=RequestMethod.POST, params="situation=5V5")
	public String evenstrength(ModelMap model) {
		model.addAttribute("situation", "Even Strength 5 v 5");
		return indexForm(model);
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
	@RequestMapping(value = "/confirmLines", method = RequestMethod.POST)
		public String saveLines(@ModelAttribute("team") Team team, ModelMap model){
		model.addAttribute("team", team);
		return indexForm(model);
	}

	@RequestMapping(value = "/")
	public String indexForm(ModelMap model){
		List<Player> players = null;
		players = sp.findPlayersByTeam(-1);

		model.addAttribute("players", players);
		if(!model.containsAttribute("situation")){
			model.addAttribute("situation", "Even Strength 5v5");
		}
		return "index";
	}

	@ModelAttribute("team")
	public Team getTeam(@ModelAttribute Team team, ModelMap model){
		Team t = new Team();

		Player C1 = sp.findPlayerByID(t.getC1());
		Player C2 = sp.findPlayerByID(t.getC2());
		Player C3 = sp.findPlayerByID(t.getC3());
		Player C4 = sp.findPlayerByID(t.getC4());

		Player LW1 = sp.findPlayerByID(t.getLW1());
		Player LW2 = sp.findPlayerByID(t.getLW2());
		Player LW3 = sp.findPlayerByID(t.getLW3());
		Player LW4 = sp.findPlayerByID(t.getLW4());

		Player RW1 = sp.findPlayerByID(t.getRW1());
		Player RW2 = sp.findPlayerByID(t.getRW2());
		Player RW3 = sp.findPlayerByID(t.getRW3());
		Player RW4 = sp.findPlayerByID(t.getRW4());

		if(C1 != null)
			t.setsC1(C1.getName());
		if(LW1 != null)
			t.setsLW1(LW1.getName());
		if(RW1 != null)
			t.setsRW1(RW1.getName());

		if(C2 != null)
			t.setsC2(C2.getName());
		if(LW2 != null)
			t.setsLW2(LW2.getName());
		if(RW2 != null)
			t.setsRW2(RW2.getName());

		if(C3 != null)
			t.setsC3(C3.getName());
		if(LW3 != null)
			t.setsLW3(LW3.getName());
		if(RW3 != null)
			t.setsRW3(RW3.getName());

		if(C4 != null)
			t.setsC1(C4.getName());
		if(LW4 != null)
			t.setsLW1(LW4.getName());
		if(RW4 != null)
			t.setsRW1(RW4.getName());

		return t;
	}

	@ModelAttribute("player")
	public Player getPlayer(ModelMap model) {
		Player p = new Player();
		return p;
	}

	@RequestMapping(value = "/addPlayer", method = RequestMethod.POST)
	public String addPlayer(@ModelAttribute("player") Player player,
									 ModelMap model) {

		player.setTeamID(-1);
		player.setGoalieskills(0);
		sp.addPlayer(player);

		List<Player> players = null;
		players = sp.findPlayersByTeam(-1);


		model.addAttribute("players", players);
		return indexForm(model);
	}
}