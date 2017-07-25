package com.github.eldnine.pagerank.dto;

import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@Entity
@AllArgsConstructor
public class Page {
	@Id
	private int id;
	private String url;
	private String html;
	private int error;
	private double oldRank;
	private double newRank;
	
	public Page(String url, String html, double newRank) {	
	}
}
