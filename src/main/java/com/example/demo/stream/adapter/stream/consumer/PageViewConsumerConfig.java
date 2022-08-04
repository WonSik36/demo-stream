package com.example.demo.stream.adapter.stream.consumer;

import com.example.demo.stream.domain.pageview.PageView;
import ksql.pageviews;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.function.Function;

/**
 * @author 정원식 (wonsik.cheung)
 */
@Slf4j
@Configuration
public class PageViewConsumerConfig {

    @Bean
    public Function<Flux<pageviews>, Mono<Void>> consumePageViews() {
        return flux -> flux.map(PageViewConsumerConfig::toPageView)
                .doOnNext(pageView -> log.warn("Page View Message Received: {}", pageView))
                .then();
    }

    private static PageView toPageView(pageviews pageviews) {
        Instant instant = Instant.ofEpochMilli(pageviews.getViewtime());
        LocalDateTime viewTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());

        return new PageView(viewTime, pageviews.getUserid(), pageviews.getPageid());
    }
}
