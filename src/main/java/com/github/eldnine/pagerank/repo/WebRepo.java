package com.github.eldnine.pagerank.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.github.eldnine.pagerank.model.Web;

public interface WebRepo extends JpaRepository<Web, String> {
	Web findTopByUrl(String url);
	public List<Web> findAllByOrderByIdAsc();
}
