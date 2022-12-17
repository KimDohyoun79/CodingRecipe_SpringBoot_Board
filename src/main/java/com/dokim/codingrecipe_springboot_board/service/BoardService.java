package com.dokim.codingrecipe_springboot_board.service;

import com.dokim.codingrecipe_springboot_board.DTO.BoardDTO;
import com.dokim.codingrecipe_springboot_board.Entity.BoardEntity;
import com.dokim.codingrecipe_springboot_board.Repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


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

    // 4_게시글 목록(boardlist)
    public List<BoardDTO> fillAll(){
         List<BoardEntity> boardEntitieList = boardRepository.findAll();
         List<BoardDTO> boardDTOList = new ArrayList<>();
         for(BoardEntity boardEntity : boardEntitieList){
             boardDTOList.add(BoardDTO.toBoardDTO(boardEntity));
         }
         return boardDTOList;
    }

    // 05_게시글 조회(Board Detail)
    @Transactional
    public void updateHits(Long id) {
        boardRepository.updateHits(id);
    }

    @Transactional
    public BoardDTO findById(Long id) {
        Optional<BoardEntity> optionalBoardEntity = boardRepository.findById(id);
        if (optionalBoardEntity.isPresent()) {
            BoardEntity boardEntity = optionalBoardEntity.get();
            BoardDTO boardDTO = BoardDTO.toBoardDTO(boardEntity);
            return boardDTO;
        } else {
            return null;
        }
    }


    // 6_게시글 수정(Board Update)
    public BoardDTO update(BoardDTO boardDTO) {
        BoardEntity boardEntity = BoardEntity.toUpdateEntity(boardDTO);
        boardRepository.save(boardEntity);
        return findById(boardDTO.getId());
    }
}
