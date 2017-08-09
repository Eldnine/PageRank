package com.github.eldnine.pagerank.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.github.eldnine.pagerank.Application;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class CrawlerImplTest {
	@Autowired
	SpiderService spider;
	
	@Test
	public void test() {
		spider.run();
	}
}
