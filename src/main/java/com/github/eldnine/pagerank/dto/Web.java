package com.github.eldnine.pagerank.dto;

import javax.persistence.Entity;

import lombok.Data;

@Data
@Entity
public class Web {
	private String url;
}
