package com.bookshuffle.service;

import com.bookshuffle.domain.Book;
import com.bookshuffle.domain.RelayChain;
import com.bookshuffle.domain.User;
import com.bookshuffle.repository.BookRepository;
import com.bookshuffle.repository.RelayChainRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RelayService {

    private final BookRepository bookRepository;
    private final RelayChainRepository relayChainRepository;

    @Transactional
    public void passBook(Book book, User fromUser, User toUser) {
        // 1. 책의 소유자 변경
        book.setUser(toUser);
        bookRepository.save(book);

        // 2. 릴레이 이력 기록
        RelayChain relay = RelayChain.builder()
                .book(book)
                .fromUser(fromUser)
                .toUser(toUser)
                .build();
        relayChainRepository.save(relay);
    }
}