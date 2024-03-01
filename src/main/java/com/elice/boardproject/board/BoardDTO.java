package com.elice.boardproject.board;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class BoardDTO {

        private Long id;
        private String name;
        private String description;


   public class BoardMapper{
            public static BoardDTO mapToDTO (Board board){
                BoardDTO boardDTO = new BoardDTO();

                boardDTO.setId(board.getId());
                boardDTO.setName(board.getName());
                boardDTO.setDescription(board.getDescription());

                return boardDTO;
            }
   }
}


