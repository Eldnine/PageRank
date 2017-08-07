package com.github.eldnine.pagerank.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Link {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
	private long fromId;
	private long toId;
	
	public Link(long id, long fromId, long toId) {
		super();
		this.id = id;
		this.fromId = fromId;
		this.toId = toId;
	}
	
	public Link(long fromId, long toId) {
		this.setFromId(fromId);
		this.setToId(toId);
	}
	
	public long getFromId() {
		return fromId;
	}
	public void setFromId(long fromId) {
		this.fromId = fromId;
	}
	public long getToId() {
		return toId;
	}
	public void setToId(long toId) {
		this.toId = toId;
	}
}
