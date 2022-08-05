package com.example.demo.stream.adapter.presentation;

import com.example.demo.stream.domain.pageview.PageView;
import ksql.pageviews;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * @author 정원식 (wonsik.cheung)
 */
@RestController
@RequiredArgsConstructor
public class PageViewController {

    private final StreamBridge streamBridge;

    @GetMapping("/test")
    public String test() {
        PageView pageView = new PageView(LocalDateTime.now(), "User_2", "Page_11");

        streamBridge.send("supply-out-0", toPageViewDto(pageView));

        return "hello world";
    }

    private static pageviews toPageViewDto(PageView pageView) {
        Instant instant = pageView.getViewTime().atZone(ZoneId.systemDefault()).toInstant();

        return new pageviews(instant.toEpochMilli(), pageView.getUserId(), pageView.getPageId());
    }
}
