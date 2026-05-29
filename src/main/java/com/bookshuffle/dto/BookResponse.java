package com.bookshuffle.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BookResponse {
    private String title;
    private String ownerNickname;
}