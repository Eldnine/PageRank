package com.github.eldnine.pagerank.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.github.eldnine.pagerank.Application;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@WebAppConfiguration
public class CrawlerImplTest {
	@Autowired
	CrawlerImpl crawler;
	
	@Test
	public void test() {
		crawler.run();
	}
}
