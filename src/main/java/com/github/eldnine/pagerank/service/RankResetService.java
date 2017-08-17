package com.github.eldnine.pagerank.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.eldnine.pagerank.model.Page;
import com.github.eldnine.pagerank.repo.PageRepo;

@Component
public class RankResetService {
	private static final Logger logger = LoggerFactory.getLogger(RankResetService.class);

	@Autowired
	PageRepo pageRepo;

	public void reset() {
		long count = 0;
		for (Page page : pageRepo.findAll()) {
			page.setNewRank(1.0);
			page.setOldRank(0.0);
			pageRepo.save(page);
			count++;
		}
		logger.info("Reset " + count + "pages.");
	}
}
