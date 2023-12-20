package com.yongin.complaint.Service.Board;

import com.yongin.complaint.DTO.Board.AdminPostDTO;
import com.yongin.complaint.DTO.Board.BoardWriteDTO;
import com.yongin.complaint.DTO.Board.UserPostDTO;
import com.yongin.complaint.JPA.Entity.Member;

import java.util.List;
import java.util.Map;

public interface BoardService {

    String writePost(BoardWriteDTO boardWriteDTO, Member writer);

    List<UserPostDTO> getMyPost(Member member);

    Map<String, List<UserPostDTO>> getAllPost();

    AdminPostDTO getSeqPost(Long seq);

    String writeComment(Long seq, String comment);

    String sendEmail(String coupon, String comment, String email);
}
