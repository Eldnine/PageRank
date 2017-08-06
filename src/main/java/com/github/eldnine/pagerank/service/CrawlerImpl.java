package com.github.eldnine.pagerank.service;

import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.eldnine.pagerank.model.Page;
import com.github.eldnine.pagerank.model.Web;
import com.github.eldnine.pagerank.repo.LinkRepo;
import com.github.eldnine.pagerank.repo.PageRepo;
import com.github.eldnine.pagerank.repo.WebRepo;
import com.github.eldnine.pagerank.util.HtmlFetcher;
import com.github.eldnine.pagerank.util.HtmlParser;

@Service
public class CrawlerImpl {
	@Autowired
	WebRepo webRepo;
	@Autowired
	LinkRepo linkRepo;
	@Autowired
	PageRepo pageRepo;
	@Autowired
	HtmlFetcher htmlFetcher;
	@Autowired
	HtmlParser htmlParser;
	int numPages;
	
	public synchronized Web saveWeb(Web web) {
		return webRepo.saveAndFlush(web);
	}
	
	public synchronized Page savePage(Page page) {
		return pageRepo.saveAndFlush(page);
	}
	
	 public synchronized List<Web> getAllWeb() {
		 
		 return webRepo.findAll();
	 }
	
	public void initCrawlerList() {
		Page page = pageRepo.findTopByUrlAndHtml(null, null);
		if (page != null) {
			System.out.println("Restarting existing crawl.");
			return;
		}

		Scanner sc = new Scanner(System.in);
		System.out.println("Enter a url: ");
		String startUrl = sc.nextLine();
		if (!htmlFetcher.isUrlFine(startUrl)) {
			System.out.println("Invalid url.");
			sc.close();
			return;
		}
		System.out.println("Saving " + startUrl);
		saveWeb(new Web(startUrl));
		savePage(new Page(startUrl, null, 1.0));
		webRepo.findTopByUrl(startUrl);
		//getAllWeb();
		System.out.println("How many pages do you want to crawl?");
		numPages = sc.nextInt();
		sc.close();
	}
}
