package com.github.eldnine.pagerank.service;

@Deprecated
public interface Spider {
    void initCrawlerList();
    
    void doRun();

    default void run() {
        initCrawlerList();
        doRun();
    }
}
