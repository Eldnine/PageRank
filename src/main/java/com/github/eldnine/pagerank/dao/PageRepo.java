package com.github.eldnine.pagerank.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.github.eldnine.pagerank.dto.Page;

public interface PageRepo extends JpaRepository<Page, String> {
	
	Page findTopByUrlAndHtml(String url, String html);
	
}
