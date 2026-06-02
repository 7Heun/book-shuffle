package com.bookshuffle.controller;

import com.bookshuffle.domain.Book;
import com.bookshuffle.domain.User;
import com.bookshuffle.dto.BookResponse;
import com.bookshuffle.dto.RelayHistoryResponse;
import com.bookshuffle.repository.BookRepository;
import com.bookshuffle.repository.RelayChainRepository;
import com.bookshuffle.repository.UserRepository;
import com.bookshuffle.service.BookService;
import com.bookshuffle.service.RelayService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;
    private final RelayService relayService;
    private final BookRepository bookRepository;
    private final UserRepository userRepository;
    private final RelayChainRepository relayChainRepository;

    // 1. 책 등록 기능
    @PostMapping("/api/books/new")
    public String register(@RequestBody Book book) {
        bookService.registerBook(book);
        return "책 등록 성공";
    }

    // 2. 책 전달 기능
    @PostMapping("/api/books/relay")
    public String relayBook(@RequestParam Long bookId,
                            @RequestParam Long fromUserId,
                            @RequestParam Long toUserId) {

        Book book = bookRepository.findById(bookId).orElseThrow();
        User fromUser = userRepository.findById(fromUserId).orElseThrow();
        User toUser = userRepository.findById(toUserId).orElseThrow();

        relayService.passBook(book, fromUser, toUser);

        return "책 전달 성공";
    }

    // 3. 책 정보 조회
    @GetMapping("/api/books/{bookId}")
    public BookResponse getBook(@PathVariable Long bookId) {
        Book book = bookRepository.findById(bookId).orElseThrow();

        return BookResponse.builder()
                .title(book.getTitle())
                .ownerNickname(book.getUser().getNickname())
                .build();
    }

    // 4. 책 릴레이 이력 조회
    @GetMapping("/api/books/{bookId}/history")
    public List<RelayHistoryResponse> getHistory(@PathVariable Long bookId) {
        return relayChainRepository.findByBookIdOrderByCreatedAtAsc(bookId).stream()
                .map(relay -> RelayHistoryResponse.builder()
                        .fromNickname(relay.getFromUser().getNickname())
                        .toNickname(relay.getToUser().getNickname())
                        .relayedAt(relay.getCreatedAt())
                        .build())
                .collect(Collectors.toList());
    }
}