package com.yongin.complaint.DAO;

import com.yongin.complaint.DTO.Board.AdminPostDTO;
import com.yongin.complaint.DTO.Board.BoardWriteDTO;
import com.yongin.complaint.DTO.Board.UserPostDTO;
import com.yongin.complaint.JPA.Entity.Board;
import com.yongin.complaint.JPA.Entity.Member;

import java.util.List;

public interface BoardDAO {

    Board savePost(BoardWriteDTO boardWriteDTO, Member writer);

    List<UserPostDTO> getUserPost(Member member);

    List<UserPostDTO> getAllPostNo();

    List<UserPostDTO> getAllPostYes();

    AdminPostDTO getSeqPost(Long seq);

    Board addComment(Long seq, String comment);
}
