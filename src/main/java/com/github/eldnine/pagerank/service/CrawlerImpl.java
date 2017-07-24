package com.github.eldnine.pagerank.service;

import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;

import com.github.eldnine.pagerank.dao.LinkRepo;
import com.github.eldnine.pagerank.dao.PageRepo;
import com.github.eldnine.pagerank.dao.WebRepo;
import com.github.eldnine.pagerank.dto.Page;

public class CrawlerImpl implements Crawler {
	
	@Autowired
	WebRepo webRepo;
	@Autowired
	LinkRepo linkRepo;
	@Autowired
	PageRepo pageRepo;
	
	@Override
	public void initCrawlerList() {
		Page page = pageRepo.findTopByUrlAndHtml(null, null);
		if (page != null) {
			System.out.println("Restarting existing crawl.  "
					+ "Remove spider.sqlite to start a fresh crawl.");
		} else {
			Scanner sc = new Scanner(System.in);
			System.out.println("Enter a url: ");
			String startUrl = sc.nextLine();
		}
	}

	@Override
	public void doRun() {
		// TODO Auto-generated method stub
		
	}

}
