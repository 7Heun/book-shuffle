package com.bookshuffle.service;

import com.bookshuffle.domain.Book;
import com.bookshuffle.dto.BookRequest;
import com.bookshuffle.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class BookService {

    private final BookRepository bookRepository;

    public void registerBook(BookRequest request) {
        Book book = Book.builder()
                .title(request.getTitle())
                .author(request.getAuthor())
                .build();

        bookRepository.save(book);
    }
}