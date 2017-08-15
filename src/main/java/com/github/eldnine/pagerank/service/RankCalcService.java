package com.github.eldnine.pagerank.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.eldnine.pagerank.model.Page;
import com.github.eldnine.pagerank.repo.LinkRepo;
import com.github.eldnine.pagerank.repo.PageRepo;

@Component
public class RankCalcService {
	private static final Logger logger = LoggerFactory.getLogger(RankCalcService.class);
	
	private static final int num_iter = 10;
	
	@Autowired
	LinkRepo linkRepo;
	@Autowired
	PageRepo pageRepo;
	
	public List<Long> findDistinctFromId() {
		return linkRepo.findDistinctFromId();
	}
	public void run() {
		List<Long> fromIds = findDistinctFromId();
		List<Long> toIds = new ArrayList<Long>();
		List<long[]> links = new ArrayList<long[]>();
		List<Object[]> rawLinks = linkRepo.findDistinctByFromIdAndToId();
		
		for (Object[] link : rawLinks) {
			long fromId = (long) link[0];
			
			long toId = (Long) link[1];
			if (fromId == toId || !fromIds.contains(fromId) || fromIds.contains(toId)) {
				continue;
			}
			links.add(new long[]{(long) link[0], (long) link[1]});
			if (!toIds.contains(toId)) {
				toIds.add(toId);
			}
		}
		Collections.sort(toIds);
		
		Map<Long, Double> prevRanks = new HashMap<Long, Double>();
		for (long node : fromIds) {
			double newRank = pageRepo.findNewRankById(node);
			prevRanks.put(node, newRank);
		}
		
		int numIter = num_iter;
		if (numIter < 1) {
			logger.warn("Wrong number of iterations.");
			return;
		}
		
		// do the pagerank in memory
		Map<Long, Double> nextRanks = new HashMap<Long, Double>();
		for (int i = 0; i < numIter; i++) {
			nextRanks = new HashMap<Long, Double>();
			double total = 0.0;
			for (Map.Entry<Long, Double> entry : prevRanks.entrySet()) {
				long node = entry.getKey();
				double oldRank = entry.getValue();
				total += oldRank;
				nextRanks.put(node, 0.0);
				
				List<Long> giveIds = new ArrayList<Long>();
				for (long[] link : links) {
					long fromId = link[0];
					long toId = link[1];
					if (fromId != node || toIds.contains(toId)) {
						continue;
					}
					giveIds.add(toId);
				}
				if (giveIds.size() < 1) {
					continue;
				}
				double weight = oldRank / (double) giveIds.size();
				for (long id : giveIds) {
					nextRanks.put(id, nextRanks.get(id) + weight);
				}
			}
			
			double newtot = 0.0;
			for (Map.Entry<Long, Double> entry : nextRanks.entrySet()) {
				newtot += entry.getValue();
			}
			double evap = (total - newtot) / (double) nextRanks.size();
			
			for (Map.Entry<Long, Double> entry : nextRanks.entrySet()) {
				nextRanks.put(entry.getKey(), nextRanks.get(entry.getKey()) + evap);
			}
			
			double totdiff = 0.0;
			for (Map.Entry<Long, Double> entry : prevRanks.entrySet()) {
				long node = entry.getKey();
				double oldRank = entry.getValue();
				double newRank = nextRanks.get(node);
				double diff = Math.abs(oldRank - newRank);
				totdiff += diff;
			}
			
			double avediff = totdiff / (double) prevRanks.size();
			logger.info(avediff + "");
			prevRanks = nextRanks;
		}
		//logger.info(nextRanks.toString());
		
		List<Page> pages = pageRepo.findAll();
		for (Page page : pages) {
			page.setOldRank(page.getNewRank());
			pageRepo.save(page);
		}
		for (Map.Entry<Long, Double> entry : nextRanks.entrySet()) {
			long id = entry.getKey();
			double newRank = entry.getValue();
			Page page = pageRepo.findTopById(id);
			page.setNewRank(newRank);
			pageRepo.save(page);
			//logger.info(page.toString());
		}
	}
}
