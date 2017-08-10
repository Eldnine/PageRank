package com.github.eldnine.pagerank.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.eldnine.pagerank.repo.LinkRepo;
import com.github.eldnine.pagerank.repo.PageRepo;

@Component
public class RankCalcService {
	private static final Logger logger = LoggerFactory.getLogger(RankCalcService.class);  
	@Autowired
	LinkRepo linkRepo;
	@Autowired
	PageRepo pageRepo;
	
	public List<Long> findDistinctFromId() {
		return linkRepo.findDistinctFromId();
	}
	public void run() {
		List<Long> fromIds = findDistinctFromId();
		List<Long[]> rows = linkRepo.findDistinctFromIdAndToId();
		logger.info(rows.toString());
		logger.info(fromIds.toString());
		
	}
}
