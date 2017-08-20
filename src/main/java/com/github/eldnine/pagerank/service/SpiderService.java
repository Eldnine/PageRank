package com.github.eldnine.pagerank.service;

import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import com.github.eldnine.pagerank.model.Link;
import com.github.eldnine.pagerank.model.Page;
import com.github.eldnine.pagerank.repo.LinkRepo;
import com.github.eldnine.pagerank.repo.PageRepo;

@Component
public class SpiderService {
	private static final Logger logger = LoggerFactory.getLogger(SpiderService.class);  
	
	public static final Integer MAX_NUM_THREADS = 10;
	
	public int numPage;

	@Autowired
	LinkRepo linkRepo;
	@Autowired
	PageRepo pageRepo;
	
	public String getCurrentStartUrl() {
		Iterator<Page> page = pageRepo.findAll(new PageRequest(0, 1)).iterator();
		if (page.hasNext()) {
			return page.next().getUrl();
		} else {
			return "[Start url has not been set yet]";
		}
	}

	private synchronized void init(String startUrl) {
		linkRepo.deleteAll();
		pageRepo.deleteAll();
		pageRepo.saveAndFlush(new Page(startUrl, false, 1.0));
	}

	public synchronized Page getUncrawledPage() {
		Page page = pageRepo.findTopByIsCrawledAndError(false, null);
		if(page == null) {
			return null;
		}
		page.setIsCrawled(true);
		return pageRepo.saveAndFlush(page);
	}

	public boolean isUrlExist(String url) {
		return pageRepo.findTopByUrl(url) != null;
	}

	public synchronized Page savePage(Page page) {
		return pageRepo.saveAndFlush(page);
	}

	public synchronized Link saveLink(Link link) {
		return linkRepo.saveAndFlush(link);
	}

	public long getPageIdByUrl(String url) {
		return pageRepo.findTopByUrl(url).getId();
	}

	public void spider() {
		ExecutorService executorService = Executors.newFixedThreadPool(MAX_NUM_THREADS);
		for (int i = 0; i < MAX_NUM_THREADS; i++) {
			executorService.execute(new SpiderThread(this, numPage));
		}
		executorService.shutdown();
		try {
			executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.DAYS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		logger.info("Spider has finished its job.");
	}
	
	public void run(String startUrl, int numPage) {
		init(startUrl);
		this.numPage = numPage;
		spider();
	}
}
