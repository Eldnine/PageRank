package com.github.eldnine.pagerank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.eldnine.pagerank.model.Page;
import com.github.eldnine.pagerank.repo.PageRepo;
import com.github.eldnine.pagerank.service.SpiderImpl;

@Controller
@RequestMapping(path="/spider")
public class MainController {
	@Autowired
	private PageRepo pageRepo;
	@Autowired
	SpiderImpl spider;
	
	@GetMapping(path="/crawl")
	public @ResponseBody Iterable<Page> getAllPages() {
		spider.run();
		return pageRepo.findAll();
	}
}
