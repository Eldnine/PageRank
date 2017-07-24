package com.github.eldnine.pagerank.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.github.eldnine.pagerank.dto.Link;

public interface LinkRepo extends JpaRepository<Link, String> {

}
