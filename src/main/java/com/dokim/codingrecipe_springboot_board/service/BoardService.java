package com.dokim.codingrecipe_springboot_board.service;

import com.dokim.codingrecipe_springboot_board.DTO.BoardDTO;
import com.dokim.codingrecipe_springboot_board.Entity.BoardEntity;
import com.dokim.codingrecipe_springboot_board.Repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;



@Service
// DTO => Entity
// Entity => DTO
@RequiredArgsConstructor
public class BoardService {

    // 3.2_게시글 작성_게시글 작성
    private final BoardRepository boardRepository;
    public void save(BoardDTO boardDTO) {
        boardRepository.save(BoardEntity.toSaveEntity(boardDTO));
    }
}
