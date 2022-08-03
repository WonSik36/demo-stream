package com.example.demo.stream.domain.user;

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
public class User {
    private final LocalDateTime registerTime;
    private final String userId;
    private final String regionId;
    private final Gender gender;
}
