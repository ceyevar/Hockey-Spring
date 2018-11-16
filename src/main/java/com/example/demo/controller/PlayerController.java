package com.example.demo.controller;

import com.example.demo.model.Player;
import com.example.demo.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
public class PlayerController {
	private String appMode;

	@Autowired
	PlayerService ps;

	@Autowired
	public PlayerController(Environment environment){
		appMode = environment.getProperty("app-mode");
	}

	@RequestMapping(value = "/player/{playerId}")
	public String getPlayer(@PathVariable String playerId, ModelMap model){

		Player player = null;

		player = ps.findPlayerByID(Integer.parseInt(playerId));

		model.addAttribute("player", player);
		return "player";
	}
}
