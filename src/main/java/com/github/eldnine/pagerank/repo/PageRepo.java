package com.github.eldnine.pagerank.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.github.eldnine.pagerank.model.Page;

public interface PageRepo extends JpaRepository<Page, String> {
	Page findTopByUrlAndHtml(String url, String html);
}
