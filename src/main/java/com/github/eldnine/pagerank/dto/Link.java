package com.github.eldnine.pagerank.dto;

import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@Entity
@AllArgsConstructor
public class Link {
	private int fromId;
	private int toId;
}
