package com.github.eldnine.pagerank.service;

@Deprecated
public interface Crawler {
    void initCrawlerList();
    
    void doRun();

    default void run() {
        initCrawlerList();
        doRun();
    }
}
