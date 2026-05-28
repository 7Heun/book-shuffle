package com.bookshuffle.service;

import com.bookshuffle.domain.Book;
import com.bookshuffle.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BookService {

    private final BookRepository bookRepository;

    @Transactional
    public Long registerBook(Book book) {
        bookRepository.save(book);
        return book.getId();
    }
}