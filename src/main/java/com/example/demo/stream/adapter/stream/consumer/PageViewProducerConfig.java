package com.example.demo.stream.adapter.stream.consumer;

import com.example.demo.stream.domain.pageview.PageView;
import io.confluent.kafka.streams.serdes.avro.SpecificAvroSerde;
import ksql.pageviews;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.serialization.Serde;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.function.Supplier;

/**
 * @author 정원식 (wonsik.cheung)
 */
@Slf4j
@Configuration
public class PageViewProducerConfig {

    @Bean
    public Supplier<pageviews> supply() {
        return () -> {
            PageView pageView = new PageView(LocalDateTime.now(), "User_1", "Page_55");
            log.info("Page View Event Occurred: {}", pageView);

            return toPageViewDto(pageView);
        };
    }

    private static pageviews toPageViewDto(PageView pageView) {
        Instant instant = pageView.getViewTime().atZone(ZoneId.systemDefault()).toInstant();

        return new pageviews(instant.toEpochMilli(), pageView.getUserId(), pageView.getUserId());
    }

    @Bean
    public Serde<pageviews> pageViewDtoSerde() {
        return new SpecificAvroSerde<>();
    }
}
