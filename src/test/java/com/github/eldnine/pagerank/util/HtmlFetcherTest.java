package com.github.eldnine.pagerank.util;

import org.junit.Test;

public class HtmlFetcherTest {
	@Test
    public void test() throws Exception {
        System.out.println(HtmlFetcher.fetch("http://sg.weibo.com"));
    }
}
