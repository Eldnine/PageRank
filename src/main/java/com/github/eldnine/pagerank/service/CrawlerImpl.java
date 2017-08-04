package com.github.eldnine.pagerank.service;

import java.util.Scanner;

import com.github.eldnine.pagerank.dao.LinkRepo;
import com.github.eldnine.pagerank.dao.PageRepo;
import com.github.eldnine.pagerank.dao.WebRepo;
import com.github.eldnine.pagerank.dto.Page;
import com.github.eldnine.pagerank.util.HtmlFetcher;
import com.github.eldnine.pagerank.util.HtmlParser;

public class CrawlerImpl {
	
	WebRepo webRepo;
	LinkRepo linkRepo;
	PageRepo pageRepo;
	
	HtmlFetcher htmlFetcher;
	HtmlParser htmlParser;
	int numPages;
	
	public void initCrawlerList() {
		Page page = pageRepo.findTopByUrlAndHtml(null, null);
		if (page != null) {
			System.out.println("Restarting existing crawl.");
		} else {
			Scanner sc = new Scanner(System.in);
			System.out.println("Enter a url: ");
			String startUrl = sc.nextLine();
			if (htmlFetcher.isUrlFine(startUrl)) {
				//webRepo.saveAndFlush(new Web(startUrl));
				//pageRepo.saveAndFlush(new Page(startUrl, null, 1.0));
			}
			
			System.out.println("How many pages do you want to crawl?");
			numPages = sc.nextInt();
			sc.close();
		}
	}
	
	public static void main(String[] args) {
		CrawlerImpl c = new CrawlerImpl();
		c.initCrawlerList();
		
	}
}
