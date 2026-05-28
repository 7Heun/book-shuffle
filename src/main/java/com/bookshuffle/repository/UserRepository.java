package com.bookshuffle.repository;

import com.bookshuffle.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository; // 이 어노테이션을 추가해보세요!

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}