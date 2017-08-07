package com.github.eldnine.pagerank.service;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.eldnine.pagerank.model.Link;
import com.github.eldnine.pagerank.model.Page;
import com.github.eldnine.pagerank.repo.LinkRepo;
import com.github.eldnine.pagerank.repo.PageRepo;
import com.github.eldnine.pagerank.util.HtmlFetcher;
import com.github.eldnine.pagerank.util.HtmlParser;

@Component
public class CrawlerImpl {
	@Autowired
	LinkRepo linkRepo;
	@Autowired
	PageRepo pageRepo;
	
	String startUrl = "http://sg.weibo.com";
	int numPages = 20;

	public void run() {
		// initialize entry page and numbers of pages
		if (pageRepo.findTopByIsCrawledAndError(false, null)  != null) {
			System.out.println("Restarting existing crawl.");
			return;
		} else {
			System.out.println("Starting url: " + startUrl);
			if (!HtmlFetcher.isUrlFine(startUrl)) {
				System.out.println("Invalid url.");
				return;
			}
			pageRepo.saveAndFlush(new Page(startUrl, false, 1.0));
			System.out.println("Starting from " + startUrl);
		}
		System.out.println("Number of pages: " + numPages);
		// loop number of pages; add href inside those pages into pages table; update links
		for (int i = 0; i < numPages; i ++) {
			Page page = pageRepo.findTopByIsCrawledAndError(false, null);
			if (page == null) {
				System.out.println("No more url.");
				break;
			}
			long fromId = page.getId();
			String url = page.getUrl();
			System.out.println("From page: " + url);

			linkRepo.removeByFromId(fromId);
			if (!HtmlFetcher.isUrlFine(url)) {
				System.out.println("Invalid url: " + url);
				page.setIsCrawled(true);
				pageRepo.saveAndFlush(page);
				continue;
			}
			page.setIsCrawled(true);
			pageRepo.saveAndFlush(page);
			for (String href : HtmlParser.getAllHrefs(url)) {
				if (href == null || href.endsWith(".png") || href.endsWith(".jpg") || href.endsWith(".gif")) {
					continue;
				}
				if (href.endsWith("/")) {
					href = href.substring(0, href.length() - 1);
				}
				if (href.length() < 1) {
					continue;
				}
				if (pageRepo.findTopByUrl(href) != null) {
					continue;
				}
				System.out.println("To page: " + href);
				pageRepo.saveAndFlush(new Page(href, false, 1.0));
				long toId = pageRepo.findTopByUrl(href).getId();
				linkRepo.saveAndFlush(new Link(fromId, toId));
			}
		}
	}
}
