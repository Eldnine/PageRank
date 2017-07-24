package com.github.eldnine.pagerank.dto;

import javax.persistence.Entity;

import lombok.Data;

@Data
@Entity
public class Link {
	private int fromId;
	private int toId;
}
