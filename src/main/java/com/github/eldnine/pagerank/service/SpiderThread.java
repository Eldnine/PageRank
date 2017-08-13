package com.github.eldnine.pagerank.service;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.eldnine.pagerank.model.Link;
import com.github.eldnine.pagerank.model.Page;
import com.github.eldnine.pagerank.util.HtmlFetcher;
import com.github.eldnine.pagerank.util.HtmlParser;

public class SpiderThread implements Runnable{
	private static final Logger logger = LoggerFactory.getLogger(SpiderThread.class);  
	private final int NUM_PAGE = 3;

	private final SpiderService spider;

	public SpiderThread(SpiderService spider) {
		super();
		this.spider = spider;
	}
	
	@Override
	public void run() {
		Page page;
		int getUnCrawlPageTimes = 0;
		for (int i = 0; i < NUM_PAGE; i++) {
			page = spider.getUncrawledPage();
			// try 10 times
			if(page == null) {
				if(getUnCrawlPageTimes > 10) {
					break;
				} else {
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					getUnCrawlPageTimes++;
					continue;
				}
			}
			getUnCrawlPageTimes = 0;
			try {
				Thread.sleep(100);
				if (!HtmlFetcher.isUrlFine(page.getUrl())) {
					logger.warn("Invalid url: " + page.getUrl());
					continue;
				}
				Thread.sleep(100);
				logger.info("Parsing: " + page.getUrl());
				List<String> hrefs;
				try {
					hrefs = HtmlParser.getAllHrefs(page.getUrl());
				} catch (IOException e) {
					logger.warn(e.getMessage());
					page.setError(1);
					spider.savePage(page);
					i--;
					continue;
				}
				if (hrefs == null) {
					continue;
				}
				for (String href : hrefs) {
					if (href == null || href.endsWith(".png") || href.endsWith(".jpg") || href.endsWith(".gif")) {
						continue;
					}
					if (href.endsWith("/")) {
						href = href.substring(0, href.length() - 1);
					}
					if (href.length() < 1) {
						continue;
					}
					if (spider.isUrlExist(href)) {
						continue;
					}
					spider.savePage(new Page(href, false, 1.0));
					long toId = spider.getPageIdByUrl(href);
					spider.saveLink(new Link(page.getId(), toId));
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
