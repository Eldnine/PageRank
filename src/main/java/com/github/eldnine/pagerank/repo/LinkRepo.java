package com.github.eldnine.pagerank.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.github.eldnine.pagerank.model.Link;

public interface LinkRepo extends JpaRepository<Link, String> {
	Long removeByFromId(long fromId);
	
	@Query("SELECT DISTINCT p.fromId FROM Link p")
	List<Long> findFromId();
}
