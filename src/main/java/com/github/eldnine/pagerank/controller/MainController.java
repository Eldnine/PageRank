package com.github.eldnine.pagerank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.eldnine.pagerank.model.Link;
import com.github.eldnine.pagerank.repo.LinkRepo;
import com.github.eldnine.pagerank.service.RankResetService;
import com.github.eldnine.pagerank.service.SpiderService;

@Controller
@RequestMapping(path="/spider")
public class MainController {
	@Autowired
	private LinkRepo linkRepo;
	@Autowired
	SpiderService spider;
	@Autowired
	RankResetService rankReset;
	
	@GetMapping(path="/crawl")
	public @ResponseBody Iterable<Link> getAllPages() {
		spider.run();
		return linkRepo.findAll();
	}
	
	@GetMapping(path="/reset")
	public @ResponseBody String resetRank() {
		rankReset.reset();
		return "reset successful!";
	}
}
