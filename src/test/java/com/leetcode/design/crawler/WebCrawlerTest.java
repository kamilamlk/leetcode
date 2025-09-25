package com.leetcode.design.crawler;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class WebCrawlerTest {
    @Test
    void shouldCrawl() throws InterruptedException {
        WebCrawler webCrawler = new WebCrawler(3);
        var urls = List.of("url1", "url2", "url3");
        webCrawler.crawl(urls);

        for (var url : urls) {
            Assertions.assertThat(webCrawler.getSubUrls(url))
                .isEmpty();
        }
        webCrawler.shutdown();
        for (var url : urls) {
            Assertions.assertThat(webCrawler.getSubUrls(url))
                .isNotEmpty()
                .hasSizeLessThan(7);
        }
    }
}