package com.dokim.codingrecipe_springboot_board.controller;

import com.dokim.codingrecipe_springboot_board.DTO.BoardDTO;
import com.dokim.codingrecipe_springboot_board.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    // 05_게시글 조회(Board Detail)
    @GetMapping("/{id}")
    public String findById(@PathVariable Long id, Model model) {
        /*
            해당 게시글의 조회수를 하나 올리고
            게시글 데이터를 가져와서 detail.html에 출력
         */
        boardService.updateHits(id);
        BoardDTO boardDTO = boardService.findById(id);
        model.addAttribute("board", boardDTO);
        return "detail";
    }


    // 6_게시글 수정(Board Update)
    @GetMapping("/update/{id}")
    public String updateForm(@PathVariable Long id, Model model) {
        BoardDTO boardDTO = boardService.findById(id);
        model.addAttribute("boardUpdate", boardDTO);
        return "update";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute BoardDTO boardDTO, Model model) {
        BoardDTO board = boardService.update(boardDTO);
        model.addAttribute("board", board);
        return "detail";
//        return "redirect:/board/" + boardDTO.getId();
    }


    // 7_게시글 삭제(Board Delete)
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        boardService.delete(id);
        return "redirect:/board/";
    }

}
