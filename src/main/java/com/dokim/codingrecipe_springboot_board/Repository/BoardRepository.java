package com.dokim.codingrecipe_springboot_board.Repository;

import com.dokim.codingrecipe_springboot_board.Entity.BoardEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<BoardEntity, Long> {
}
