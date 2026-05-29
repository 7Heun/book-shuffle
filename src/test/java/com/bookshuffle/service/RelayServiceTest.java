package com.bookshuffle.service;

import com.bookshuffle.domain.Book;
import com.bookshuffle.domain.User;
import com.bookshuffle.repository.BookRepository;
import com.bookshuffle.repository.RelayChainRepository;
import com.bookshuffle.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class RelayServiceTest {

    @Autowired RelayService relayService;
    @Autowired UserRepository userRepository;
    @Autowired BookRepository bookRepository;
    @Autowired RelayChainRepository relayChainRepository;

    @Test
    void 책_릴레이_테스트() {
        // 1. 유저 2명 생성
        User gma = User.builder().nickname("gma").build();
        User friend = User.builder().nickname("testUser").build();
        userRepository.save(gma);
        userRepository.save(friend);

        // 2. 책 생성
        Book book = Book.builder().title("삼체 1부").user(gma).build();
        bookRepository.save(book);

        // 3. 릴레이 실행
        relayService.passBook(book, gma, friend);

        // 4. 검증
        Book updatedBook = bookRepository.findById(book.getId()).get();
        assertThat(updatedBook.getUser().getNickname()).isEqualTo("testUser"); // 주인 바뀜
        assertThat(relayChainRepository.count()).isEqualTo(1); // 이력 1개 생성
    }
}