package com.elice.boardproject.controller;

import com.elice.boardproject.board.Board;
import com.elice.boardproject.board.BoardDTO;
import com.elice.boardproject.board.Repository;
import com.elice.boardproject.post.Post;
import com.elice.boardproject.post.PostRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Enumeration;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/boards")
@RequiredArgsConstructor
public class BoardController {

    private final Repository boardRepository;
    private final PostRepository postRepository;
    @GetMapping
    public String boards(Model model){
        List<Board> boards = boardRepository.findAll();
        model.addAttribute("boards",boards);
        return "board/boards";
    }


    @GetMapping("/create")
    public String createBoard(){
        return"board/createBoard";
    }

    @DeleteMapping("/{boardId}/delete")
    public String deleteBoard(@PathVariable Long  boardId){
        Board deletedBoard = boardRepository.findById(boardId).orElse(null);
        boardRepository.delete(deletedBoard);
        return "redirect:/boards";
    }
    @PostMapping("/create")
    public String saveBoard(@RequestParam ("name")String name, @RequestParam ("description") String description, Model model){
        Board board = new Board(name,description);
        boardRepository.save(board);
        model.addAttribute("board",board);
        return "redirect:/boards";
    }

    @GetMapping("/{boardId}/edit")
    public String edtiBoard(@PathVariable Long boardId, Model model){
        Board board = boardRepository.findById(boardId).orElse(null);
       model.addAttribute("board",board);
       return "board/editBoard";
    }

    @PostMapping("/{boardId}/edit")
    public String edtiedBoard(@PathVariable Long boardId,@RequestParam String name,@RequestParam String description, Model model){
        Board board = boardRepository.findById(boardId).orElse(null);
        board.setName(name);
        board.setDescription(description);
        boardRepository.update(board);
        model.addAttribute("board",board);
        return "redirect:/boards";
    }


    @GetMapping("/{boardId}")
    public String getBoard(@PathVariable Long boardId,
                           @RequestParam(defaultValue = "0") int page,
                           @RequestParam(defaultValue = "10") int size,
                           @RequestParam(required = false) String keyword,
                           Model model) {
        Board board = boardRepository.findById(boardId).orElse(null);
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<Post> postPage;
        if (keyword != null && !keyword.isEmpty()) {
            postPage = postRepository.findAllByBoardAndTitleContaining(board, keyword, pageRequest);
        } else {
            postPage = postRepository.findAllByBoardOrderByPostIdDesc(board, pageRequest);
        }
        model.addAttribute("board", board);
        model.addAttribute("keyword", keyword);
        model.addAttribute("postPage", postPage);

        return "board/board";
    }





    @GetMapping("/boards/{id}")
    public String getBoard(@RequestParam(name = "page", defaultValue = "0") int page, @RequestParam(name = "size",  defaultValue = "5") int size, @RequestParam(name = "keyword", required = false) String keyword, @PathVariable(name = "id") Long id, Model model){
        Optional<Board> boardRepositoryById = boardRepository.findById(id);
         BoardDTO boardDTO = new BoardDTO();
        model.addAttribute("board", boardDTO);


        return "/board/board";

    }


}
