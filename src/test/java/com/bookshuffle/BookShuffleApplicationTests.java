package com.bookshuffle;

import com.bookshuffle.domain.Book;
import com.bookshuffle.domain.User;
import com.bookshuffle.repository.BookRepository;
import com.bookshuffle.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional // 테스트가 끝나면 DB를 다시 초기화
class BookShuffleApplicationTests {

    @Autowired UserRepository userRepository;
    @Autowired BookRepository bookRepository;

    @Test
    void user_book_connect_test() {
        // 1. 유저 생성
        User user = User.builder()
                .nickname("gma")
                .build();

        // 2. 책 생성 및 유저 연결
        Book book = Book.builder()
                .title("삼체 1부").user(user).build();
        bookRepository.save(book);

        // 3. 검증
        Book findBook = bookRepository.findById(book.getId()).get();
        assertThat(findBook.getUser().getNickname()).isEqualTo("gma");
    }
}