package com.example.demo.stream.adapter.stream.consumer;


import com.example.demo.stream.domain.pageview.PageView;
import ksql.pageviews;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.test.context.ActiveProfiles;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;


@ActiveProfiles("test-pageview")
@SpringBootTest
class PageViewConsumerConfigTest {

    @Autowired
    private StreamBridge streamBridge;

    @Test
    void test() throws Exception {
        PageView pageView = new PageView(LocalDateTime.now(), "User_2", "Page_11");

        System.out.println("Send Data!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        streamBridge.send("supply-out-0", toPageViewDto(pageView));

        Thread.sleep(1000);
    }


    private pageviews toPageViewDto(PageView pageView) {
        Instant instant = pageView.getViewTime().atZone(ZoneId.systemDefault()).toInstant();

        return new pageviews(instant.toEpochMilli(), pageView.getUserId(), pageView.getPageId());
    }
}