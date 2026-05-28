package com.bookshuffle.controller;

import com.bookshuffle.domain.Book;
import com.bookshuffle.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @PostMapping("/api/books/new")
    public String register(@RequestBody Book book) {
        bookService.registerBook(book);
        return "책 등록 성공";
    }
}