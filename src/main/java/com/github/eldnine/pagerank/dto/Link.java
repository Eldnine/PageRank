package com.github.eldnine.pagerank.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@Entity
@AllArgsConstructor
public class Link {
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;
	
	private long fromId;
	private long toId;
}
