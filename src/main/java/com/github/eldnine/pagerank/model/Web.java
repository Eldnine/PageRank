package com.github.eldnine.pagerank.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Web {
	@Id
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
