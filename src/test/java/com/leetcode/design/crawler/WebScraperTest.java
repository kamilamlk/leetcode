package com.leetcode.design.crawler;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

class WebScraperTest {
    @Test
    void shouldGetTotalPages() {
        WebScraper scraper = new WebScraper();
        int total = scraper.getTotalSize(List.of("10"));
        Assertions.assertThat(total).isEqualTo(10);
    }

    @ParameterizedTest
    @ValueSource(ints = {10, 100, 1000, 5000})
    void shouldGetTotalPagesAsync(int size) {
        WebScraper scraper = new WebScraper();
        List<String> pages = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            pages.add(UUID.randomUUID().toString());
        }
        int total = scraper.getTotalSize(pages);
        Assertions.assertThat(total).isEqualTo(10 * pages.size());
    }
}