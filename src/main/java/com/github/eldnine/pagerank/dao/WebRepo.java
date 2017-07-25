package com.github.eldnine.pagerank.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.github.eldnine.pagerank.dto.Page;
import com.github.eldnine.pagerank.dto.Web;

public interface WebRepo extends JpaRepository<Web, String> {
	Page findTopByUrl(String url);
}
