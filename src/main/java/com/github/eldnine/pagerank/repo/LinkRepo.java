package com.github.eldnine.pagerank.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.github.eldnine.pagerank.model.Link;

public interface LinkRepo extends JpaRepository<Link, String> {
	Long removeByFromId(long fromId);
}
