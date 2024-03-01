package com.elice.boardproject.controller;

import com.elice.boardproject.board.Board;
import com.elice.boardproject.board.Repository;
import com.elice.boardproject.comment.Comment;
import com.elice.boardproject.comment.CommentRepository;
import com.elice.boardproject.post.Post;
import com.elice.boardproject.post.PostRepository;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/posts")
@Transactional
public class PostController {

    private final PostRepository postRepository;
    private final com.elice.boardproject.board.Repository boardrepository;
    private final CommentRepository commentRepository;

    public PostController(PostRepository postRepository, Repository boardrepository, CommentRepository commentRepository) {
        this.postRepository = postRepository;
        this.boardrepository = boardrepository;
        this.commentRepository = commentRepository;
    }

    @GetMapping("/create")
    public String createPost(@RequestParam Long boardId, HttpSession session){
        Board board = boardrepository.findById(boardId).orElse(null);
        session.setAttribute("board",board);
        return "/post/createPost";
    }
    @PostMapping("/create")
    public String createdPost(HttpSession session, @RequestParam String title, @RequestParam String content, Model model){
        Board board = (Board)session.getAttribute("board");
        Post post = new Post(board,title,content);
        postRepository.save(post);
        return "redirect:/boards/"+board.getId();
    }

    @GetMapping("/{postId}")
    public String currentPost(@PathVariable Long postId, Model model){
        Post post = postRepository.getById(postId);

       List<Comment> comments = post.getComments();
        model.addAttribute("comments",comments);
        model.addAttribute("post",post);

       return "/post/post";
    }

    @GetMapping("/{postId}/edit")
    public String editPost(@PathVariable Long postId,HttpSession session, Model model){
        Post post = postRepository.getById(postId);
        session.setAttribute("post",post);
        model.addAttribute("post",post);

        return "/post/editPost";
    }


    @PostMapping("/{postId}/edit")
    public String editedPost(@RequestParam String title, @RequestParam String content, HttpSession session, Model model){
        Post post = (Post)session.getAttribute("post");
        post.setTitle(title);
        post.setContent(content);
        postRepository.save(post);
        model.addAttribute("post",post);

        return "redirect:/posts/{postId}";
    }

    @DeleteMapping("/{postId}")
    public String deletePost(@PathVariable Long postId){
        postRepository.delete(postRepository.getById(postId));
        return "/redirect:/boards/{boardId}";
    }

    @PostMapping("/comments")
    public String addComment(@RequestParam Long postId, @RequestParam String content){
        Post post =postRepository.findById(postId).orElse(null);
        Comment comment = new Comment(content,post);
        commentRepository.saveAndFlush(comment);
        return "redirect:/posts/{postId}";
    }
}
