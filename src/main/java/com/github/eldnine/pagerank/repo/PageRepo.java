package com.github.eldnine.pagerank.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.github.eldnine.pagerank.model.Page;

public interface PageRepo extends JpaRepository<Page, String> {
	Page findTopByIsCrawledAndError(boolean isCrawled, Integer error);
	Page findTopByUrl(String url);
	Page findTopById(long id);
	@Query("SELECT p.newRank FROM Page p WHERE p.id = ?1")
	double findNewRankById(long id);
}
