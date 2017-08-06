package com.github.eldnine.pagerank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.eldnine.pagerank.model.Page;
import com.github.eldnine.pagerank.repo.PageRepo;
import com.github.eldnine.pagerank.service.CrawlerImpl;

@Controller
@RequestMapping(path="/demo")
public class MainController {
	@Autowired
	private PageRepo pageRepo;
	@Autowired
	CrawlerImpl crawler;
	
	@GetMapping(path="/all")
	public @ResponseBody Iterable<Page> getAllPages() {
		crawler.run();
		return pageRepo.findAll();
	}
}
