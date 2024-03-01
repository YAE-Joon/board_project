package com.elice.boardproject.board;

import jakarta.persistence.EntityManager;
import org.hibernate.query.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.util.*;

@org.springframework.stereotype.Repository
@Transactional
public class Repository {

    private final JdbcTemplate jdbcTemplate;



    public Repository(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    public Board save(Board board) {
        String sql = "insert into board(name,description) values(?,?)";
        KeyHolder keyHolder = new GeneratedKeyHolder(); //db에서 받은 id값을 넣음
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id"});
            ps.setString(1, board.getName());
            ps.setString(2, board.getDescription());
            return ps;
        }, keyHolder);

        long key = keyHolder.getKey().longValue();
        board.setId(key);
        return board;
    }

    public void delete(Board board){
        String sql = "delete from board where id =?";
        jdbcTemplate.update(sql,Long.valueOf(board.getId()));
    }

    public Board update(Board board){
        String sql = "update board set name=?, description=? where id=?";
        jdbcTemplate.update(sql,board.getName(),board.getDescription(),Long.valueOf(board.getId()));
        return board;
    }

    public List<Board> findAll(){
        String sql = "select * from board";
        return jdbcTemplate.query(sql,boardRowMapper());
    }
    public Optional<Board> findById(long id){
        String sql = "select * from board where id = ?";
        return jdbcTemplate.query(sql, boardRowMapper(),id).stream().findAny();//RowMapper() -->id조회 결과를 Board 객체로 변경
    }

    private RowMapper<Board> boardRowMapper(){
        return (rs, rowNum) ->{
            Board board = new Board(rs.getString("name"),rs.getString("description"));
            board.setId(rs.getLong("id"));
            return board;
        };
    }


}
