package com.github.eldnine.pagerank.util;

import java.io.IOException;

import org.jsoup.Connection;
import org.jsoup.Jsoup;

public class HtmlFetcher {

    public static String fetch(String url) {
        try {
            Connection.Response response = Jsoup.connect(url).timeout(3000).execute();
            return response.statusCode() / 100 == 2 ? response.body() : null;
        } catch (Exception e) {
        	e.printStackTrace();
            return null;
        }
    }
    
    public static boolean isUrlFine(String url) {
		try {
			Connection.Response response = Jsoup.connect(url).timeout(3000).execute();
			return response.statusCode() / 100 == 2;
		} catch (IOException e) {
			// e.printStackTrace();
			return false;
		}
    }

}
