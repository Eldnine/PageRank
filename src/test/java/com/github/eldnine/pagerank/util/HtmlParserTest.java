package com.github.eldnine.pagerank.util;

import org.junit.Test;

public class HtmlParserTest {
	@Test
    public void test() throws Exception {
    	HtmlParser.getAllHrefs("http://sg.weibo.com")
        .forEach(playlist -> System.out.println(playlist));
    }
}
