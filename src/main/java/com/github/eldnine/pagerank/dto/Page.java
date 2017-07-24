package com.github.eldnine.pagerank.dto;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
public class Page {
	@Id
	private int id;
	private String url;
	private String html;
	private int error;
	private double oldRank;
	private double newRank;
}
