package com.yongin.complaint.Service.Board.Impl;

import com.yongin.complaint.Component.emailSender.EmailSender;
import com.yongin.complaint.Component.fireBase.FireBasePush;
import com.yongin.complaint.DAO.BoardDAO;
import com.yongin.complaint.DTO.Board.AdminPostDTO;
import com.yongin.complaint.DTO.Board.BoardWriteDTO;
import com.yongin.complaint.DTO.Board.UserPostDTO;
import com.yongin.complaint.DTO.Email.EmailDTO;
import com.yongin.complaint.DTO.Firebase.FirebaseDTO;
import com.yongin.complaint.JPA.Entity.Board;
import com.yongin.complaint.JPA.Entity.Member;
import com.yongin.complaint.Service.Board.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BoardServiceImpl implements BoardService {

    private BoardDAO boardDAO;
    private EmailSender emailSender;
    private FireBasePush fireBasePush;

    @Autowired
    public BoardServiceImpl(BoardDAO boardDAO, EmailSender emailSender,FireBasePush fireBasePush){
        this.boardDAO = boardDAO;
        this.emailSender = emailSender;
        this.fireBasePush = fireBasePush;
    }
    @Override
    public String writePost(BoardWriteDTO boardWriteDTO, Member writer) {


        if(boardDAO.savePost(boardWriteDTO,writer) == null){
            return "일시적인 오류입니다. 잠시후 시도해주세요.";
        }else{
            fireBasePush.sendPush(FirebaseDTO.builder()
                            .title("["+boardWriteDTO.getTag()+"] 새로운 문의글입니다!")
                            .body(boardWriteDTO.getSubject())
                            .build());
            return "정상적으로 입력되었습니다. 메일로 답장드릴게요!";
        }
    }
    @Override
    public List<UserPostDTO> getMyPost(Member member) {
        return boardDAO.getUserPost(member);
    }

    @Override
    public Map<String,List<UserPostDTO>> getAllPost() {
        Map<String,List<UserPostDTO>> postData = new HashMap<>();

        postData.put("yes", boardDAO.getAllPostYes());
        postData.put("no", boardDAO.getAllPostNo());

        return postData;
    }

    @Override
    public AdminPostDTO getSeqPost(Long seq) {
        return boardDAO.getSeqPost(seq);
    }

    @Override
    public String writeComment(Long seq, String comment) {
        Board board = boardDAO.addComment(seq, comment);
        if(board == null){
            return "일시적인 오류입니다. 잠시후 시도해주세요";
        }
        return "정상적으로 입력되었습니다!";
    }

    @Override
    public String sendEmail(String coupon, String comment,String email) {
        EmailDTO emailDTO = EmailDTO.builder()
                .email(email)
                .subject("[Ycomplaint]문의글에 대한 답변입니다!")
                .content(coupon+"<br>"+comment)
                .mailFlag("post")
                .build();
        return emailSender.sendMail(emailDTO);
    }


}
