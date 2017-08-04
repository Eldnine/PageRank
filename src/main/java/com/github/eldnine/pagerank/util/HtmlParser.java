package com.github.eldnine.pagerank.util;

import java.util.List;
import java.util.stream.Collectors;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class HtmlParser {
	private static final HtmlFetcher htmlFetcher = new HtmlFetcher();
	
	public List<String> getAllHrefs(String url) {
		Document document = Jsoup.parse(htmlFetcher.fetch(url));
		return document.select("a").stream().map(e -> e.absUrl("href")).collect(Collectors.toList());
	}
	
    public static <T> void main(String[] args) throws Exception {
        HtmlParser htmlParser = new HtmlParser();
        htmlParser.getAllHrefs("http://sg.weibo.com")
        .forEach(playlist -> System.out.println(playlist));
    }
}
