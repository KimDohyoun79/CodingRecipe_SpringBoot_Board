package com.dokim.codingrecipe_springboot_board.controller;

import com.dokim.codingrecipe_springboot_board.DTO.BoardDTO;
import com.dokim.codingrecipe_springboot_board.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
@Slf4j
public class BoardController {

    private final BoardService boardService;

    // 3.1_게시글 작성_작성 페이지
    @GetMapping("/save")
    public String saveForm() {
        return "saveForm";
    }

    // 3.2_게시글 작성_게시글 작성
    @PostMapping("/save")
    public String save(@ModelAttribute BoardDTO boardDTO) {
        log.info(boardDTO.toString());
        boardService.save(boardDTO);
        return "home";
    }

    // 4_게시글 목록(boardlist)
    @GetMapping("/")
    public String findAll(Model model) {
        // DB에서 전체 게시글 데이터를 가져와서 list.html에 보여준다.
        List<BoardDTO> boardDTOList = boardService.fillAll();
        model.addAttribute("boardList", boardDTOList);
        return "list";
    }
}
