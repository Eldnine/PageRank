package com.github.eldnine.pagerank.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Page {
	@Id
	private long id;
	private String url;
	private boolean isCrawled;
	private Integer error;
	private double oldRank;
	private double newRank;
	
	public Page(long id, String url, boolean isCrawled, int error, double oldRank, double newRank) {
		super();
		this.id = id;
		this.url = url;
		this.isCrawled = isCrawled;
		this.error = error;
		this.oldRank = oldRank;
		this.newRank = newRank;
	}

	public Page (String url, boolean isCrawled, double newRank) {
		this.setUrl(url);
		this.setIsCrawled(isCrawled);
		this.setNewRank(newRank);
	}
	
	public long getId() {
		return id;
	}
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public boolean getIsCrawled() {
		return isCrawled;
	}

	public void setIsCrawled(boolean isCrawled) {
		this.isCrawled = isCrawled;
	}

	public int getError() {
		return error;
	}

	public void setError(int error) {
		this.error = error;
	}

	public double getOldRank() {
		return oldRank;
	}

	public void setOldRank(double oldRank) {
		this.oldRank = oldRank;
	}

	public double getNewRank() {
		return newRank;
	}

	public void setNewRank(double newRank) {
		this.newRank = newRank;
	}
}
