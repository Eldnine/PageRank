package com.github.eldnine.pagerank.util;

import java.util.List;
import java.util.stream.Collectors;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

public class HtmlParser {
	private HtmlFetcher htmlFetcher = new HtmlFetcher();
	
	public static List<String> getAllHrefs(String url) {
		Document document = Jsoup.parse(HtmlFetcher.fetch(url));
		return document.select("a").stream().map(e -> e.absUrl("href")).collect(Collectors.toList());
	}
	

}
