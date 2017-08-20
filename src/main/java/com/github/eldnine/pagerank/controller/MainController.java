package com.github.eldnine.pagerank.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.eldnine.pagerank.model.Link;
import com.github.eldnine.pagerank.model.Page;
import com.github.eldnine.pagerank.repo.LinkRepo;
import com.github.eldnine.pagerank.service.RankService;
import com.github.eldnine.pagerank.service.SpiderService;

@Controller
@RequestMapping(path = "/pagerank")
public class MainController {
	@Autowired
	private LinkRepo linkRepo;
	@Autowired
	SpiderService spiderService;
	@Autowired
	RankService rankService;

	@GetMapping
	public String getIndex() {
		return "pagerank";
	}

	@GetMapping(path = "/spider/currentStartUrl")
	@ResponseBody
	public String getCurrentStartUrl() {
		return spiderService.getCurrentStartUrl();
	}

	@GetMapping(path = "/spider/new")
	public @ResponseBody Iterable<Link> newSpider(
			@RequestParam(value = "startUrl", defaultValue = "http://sg.weibo.com") String startUrl, 
			@RequestParam(value = "numPage", defaultValue = "10") int numPage) {
		spiderService.run(startUrl, numPage);
		return linkRepo.findAll();
	}

	@GetMapping(path = "/spider/continue")
	public @ResponseBody Iterable<Link> continueSpider() {
		spiderService.spider();
		return linkRepo.findAll();
	}

	@GetMapping(path = "/reset")
	public @ResponseBody String resetRank() {
		rankService.reset();
		return "Reset successful!";
	}

	@GetMapping(path = "/rank")
	public @ResponseBody String rank() {
		rankService.run();
		return "Rank successful!";
	}

	@GetMapping(path = "/result")
	@ResponseBody
	public List<Page> getCurrentRankResult() {
		return rankService.getCurrentRankResult();
	}
}
