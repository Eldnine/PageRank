package com.github.eldnine.pagerank.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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
	
	public Page(long id, String url, String html, int error, double oldRank, double newRank) {
		super();
		this.id = id;
		this.url = url;
		this.html = html;
		this.error = error;
		this.oldRank = oldRank;
		this.newRank = newRank;
	}

	public Page (String url, String html, double newRank) {
		this.setUrl(url);
		this.setHtml(html);
		this.setNewRank(newRank);
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getHtml() {
		return html;
	}

	public void setHtml(String html) {
		this.html = html;
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
