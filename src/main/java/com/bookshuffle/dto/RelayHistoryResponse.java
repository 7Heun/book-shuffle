package com.bookshuffle.dto;

import lombok.Builder;
import lombok.Getter;
import java.time.LocalDateTime;

@Getter
@Builder
public class RelayHistoryResponse {
    private String fromNickname;
    private String toNickname;
    private LocalDateTime relayedAt;
}