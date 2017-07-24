package com.github.eldnine.pagerank.service;

import java.util.List;

import com.github.eldnine.pagerank.dto.Web;

public interface Crawler {
    void initCrawlerList();
    
    void doRun();

    default void run() {
        initCrawlerList();
        doRun();
    }
}
