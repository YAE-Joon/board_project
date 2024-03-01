package com.elice.boardproject.comment;

import com.elice.boardproject.post.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Long> {
}
