package com.elice.boardproject.comment;

import com.elice.boardproject.board.Board;
import com.elice.boardproject.post.Post;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_Id")
    private Long commentId;

    public Comment(String content, Post post) {
        this.content = content;
        this.post = post;
    }

    private String content;

    @ManyToOne
    @JoinColumn(name ="post_id")
    private Post post;

}
