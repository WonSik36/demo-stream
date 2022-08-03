package com.example.demo.stream.adapter.stream.consumer;

import com.example.demo.stream.application.LogService;
import com.example.demo.stream.domain.user.Gender;
import com.example.demo.stream.domain.user.User;
import io.confluent.kafka.streams.serdes.avro.SpecificAvroSerde;
import ksql.users;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.function.Consumer;

/**
 * @author 정원식 (wonsik.cheung)
 */
@Configuration
@RequiredArgsConstructor
public class UserConsumerConfig {

    private final LogService service;

    @Bean
    public Consumer<KStream<String, users>> userEventConsumer() {
        return input -> {
            input.mapValues(UserConsumerConfig::toUser)
                    .foreach((key, value) -> {
                        service.logUser(value);
                    });
        };
    }

    private static User toUser(users dto) {
        Instant instant = Instant.ofEpochMilli(dto.getRegistertime());

        return User.builder()
                .registerTime(LocalDateTime.ofInstant(instant, ZoneId.systemDefault()))
                .userId(dto.getRegionid())
                .regionId(dto.getRegionid())
                .gender(Gender.valueOf(dto.getGender()))
                .build();
    }


    @Bean
    public Serde<users> userDtoSerde() {
        return new SpecificAvroSerde<>();
    }
}
