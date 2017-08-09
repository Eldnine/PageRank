package com.github.eldnine.pagerank.service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.eldnine.pagerank.model.Link;
import com.github.eldnine.pagerank.model.Page;
import com.github.eldnine.pagerank.repo.LinkRepo;
import com.github.eldnine.pagerank.repo.PageRepo;

@Component
public class SpiderService {
	public static final Integer MAX_NUM_THREADS = 5;
	public final String START_URL = "http://sg.weibo.com";
	
	@Autowired
	LinkRepo linkRepo;
	@Autowired
	PageRepo pageRepo;

    public void initStartUrl() {
    	linkRepo.deleteAll();
    	pageRepo.deleteAll();
    	pageRepo.saveAndFlush(new Page(START_URL, false, 1.0));
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

	public void run() {
		initStartUrl();
        ExecutorService executorService = Executors.newFixedThreadPool(MAX_NUM_THREADS);
        for (int i = 0; i < MAX_NUM_THREADS; i++) {
            executorService.execute(new SpiderThread(this));
        }
        executorService.shutdown();
        try {
            executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.DAYS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
	}
}
