package com.bookshuffle.repository;

import com.bookshuffle.domain.RelayChain;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface RelayChainRepository extends JpaRepository<RelayChain, Long> {
    // 특정 책의 모든 릴레이 기록을 시간순으로 가져옴
    List<RelayChain> findByBookIdOrderByCreatedAtAsc(Long bookId);
}