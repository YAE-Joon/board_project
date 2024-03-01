package com.elice.boardproject.post;

import com.elice.boardproject.board.Board;
import jakarta.persistence.EntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post,Long> {


    Page<Post> findAllByBoardAndTitleContaining(Board board, String keyword, Pageable pageable);
    Page<Post> findAllByBoardOrderByPostIdDesc(Board board, Pageable pageable);
    }
