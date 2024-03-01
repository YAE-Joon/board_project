package com.elice.boardproject.post;

import com.elice.boardproject.board.Board;
import com.elice.boardproject.comment.Comment;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.id.factory.spi.GenerationTypeStrategy;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Post {

    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name= "post_id")
    private long postId;

    @ManyToOne
    @JoinColumn(name ="id")
    private Board board;

    @OneToMany(mappedBy = "post")
    private List<Comment> comments;

    private String title;

    private String content;

    private LocalDateTime createdAt;

    public Post(Board board, String title, String content) {
        this.board = board;
        this.title = title;
        this.content = content;
    }
}
