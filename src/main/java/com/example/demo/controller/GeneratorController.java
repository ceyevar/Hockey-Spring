package com.example.demo.controller;

import com.example.demo.components.generator.PlayerGenerator;
import com.example.demo.model.Player;
import com.example.demo.service.GeneratorService;
import com.example.demo.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

public class GeneratorController {

	private String appMode;

	@Autowired
	GeneratorService gs;

	@Autowired
	PlayerService ps;

	@Autowired
	public GeneratorController(Environment environment) {
		appMode = environment.getProperty("app-mode");
	}

	@RequestMapping(value = "/generateDb")
	public String getPlayer(ModelMap model) {
		PlayerGenerator pg = new PlayerGenerator();
		pg.setGeneratorService(gs);

		for(int i=0; i<50; ++i){
			Player p = pg.getNext();
			ps.addPlayer(p);
		}

		return "index";
	}
}
