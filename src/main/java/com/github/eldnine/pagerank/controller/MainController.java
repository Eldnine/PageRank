package com.github.eldnine.pagerank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.eldnine.pagerank.model.Link;
import com.github.eldnine.pagerank.repo.LinkRepo;
import com.github.eldnine.pagerank.service.RankCalcService;
import com.github.eldnine.pagerank.service.RankResetService;
import com.github.eldnine.pagerank.service.SpiderService;

@Controller
@RequestMapping(path="/pagerank")
public class MainController {
	@Autowired
	private LinkRepo linkRepo;
	@Autowired
	SpiderService spider;
	@Autowired
	RankResetService rankReset;
	@Autowired
	RankCalcService rankCalcService;
	
	@GetMapping(path="/spider/new")
	public @ResponseBody Iterable<Link> newSpider() {
		spider.run();
		return linkRepo.findAll();
	}
	
	@GetMapping(path="/spider/continue")
	public @ResponseBody Iterable<Link> continueSpider() {
		spider.spider();
		return linkRepo.findAll();
	}
	
	@GetMapping(path="/reset")
	public @ResponseBody String resetRank() {
		rankReset.reset();
		return "Reset successful!";
	}
	
	@GetMapping(path="/rank")
	public @ResponseBody String rank() {
		rankCalcService.run();
		return "Rank successful!";
	}
}
