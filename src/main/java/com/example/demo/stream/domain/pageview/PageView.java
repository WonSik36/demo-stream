package com.example.demo.stream.domain.pageview;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * @author 정원식 (wonsik.cheung)
 */
@Getter
@Builder
@ToString
@RequiredArgsConstructor
public class PageView {
    private final LocalDateTime viewTime;
    private final String userId;
    private final String pageId;
}
