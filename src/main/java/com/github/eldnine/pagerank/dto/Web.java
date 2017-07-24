package com.github.eldnine.pagerank.dto;

import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@Entity
@AllArgsConstructor
public class Web {
	private String url;
}
