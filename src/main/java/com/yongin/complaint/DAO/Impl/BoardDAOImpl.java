package com.yongin.complaint.DAO.Impl;

import com.yongin.complaint.Common.BoardTagEnum;
import com.yongin.complaint.DAO.BoardDAO;
import com.yongin.complaint.DTO.Board.AdminPostDTO;
import com.yongin.complaint.DTO.Board.BoardWriteDTO;
import com.yongin.complaint.DTO.Board.UserPostDTO;
import com.yongin.complaint.JPA.Entity.Board;
import com.yongin.complaint.JPA.Entity.Member;
import com.yongin.complaint.JPA.Repository.BoardRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class BoardDAOImpl implements BoardDAO {

    private final BoardRepository boardRepository;

    public BoardDAOImpl(BoardRepository boardRepository){
        this.boardRepository = boardRepository;
    }


    @Override
    @Transactional
    public Board savePost(BoardWriteDTO boardWriteDTO, Member writer) {

        return boardRepository.save(Board.builder()
                        .subject(boardWriteDTO.getSubject())
                        .content(boardWriteDTO.getContent())
                        .tag(BoardTagEnum.valueOf(boardWriteDTO.getTag()))
                        .writer(writer)
                        .regulationDate(LocalDateTime.now())
                        .status("no")
                .build()

        );
    }

    @Override
    @Transactional
    public List<UserPostDTO> getUserPost(Member member) {
        return boardRepository.getUserPost(member.getMemberSeq());
    }

    @Override
    public List<UserPostDTO> getAllPostNo() {
        return boardRepository.getAllPostNo();
    }

    @Override
    public List<UserPostDTO> getAllPostYes() {
        return boardRepository.getAllPostYes();
    }

    @Override
    public AdminPostDTO getSeqPost(Long seq) {
        return boardRepository.findToId(seq);
    }

    @Override
    @Transactional
    public Board addComment(Long seq, String comment) {
        Board post = boardRepository.getById(seq);
        post.setComment(comment);
        post.setCompleteDate(LocalDateTime.now());
        post.setStatus("yes");
        return boardRepository.save(post);
    }
}
