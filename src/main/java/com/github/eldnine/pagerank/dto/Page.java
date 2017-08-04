package com.github.eldnine.pagerank.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Page {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	private String url;
	private String html;
	private int error;
	private double oldRank;
	private double newRank;
}
