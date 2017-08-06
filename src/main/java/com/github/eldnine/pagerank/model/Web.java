package com.github.eldnine.pagerank.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Web {
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;
	private String url;
	
	public Web(long id, String url) {
		super();
		this.id = id;
		this.url = url;
	}

	public Web(String url) {
		this.setUrl(url);
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
