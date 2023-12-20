package com.yongin.complaint.Controller;

import com.yongin.complaint.Common.BoardTagEnum;
import com.yongin.complaint.DTO.Board.AdminPostDTO;
import com.yongin.complaint.DTO.Board.BoardWriteDTO;
import com.yongin.complaint.DTO.Board.UserPostDTO;
import com.yongin.complaint.JPA.Entity.Member;
import com.yongin.complaint.Service.Board.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/v1/board")
public class BoardController {

    private final BoardService boardService;

    @Autowired
    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping(value = "/tag")
    public List<String> getTagInfo() {
        return BoardTagEnum.getBoardTag();
    }

    @GetMapping(value = "/post")
    public List<UserPostDTO> getMyPost() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Member member = (Member) auth.getPrincipal();

        return boardService.getMyPost(member);
    }

    @GetMapping(value = "/Apost")
    public Map<String, List<UserPostDTO>> getAllPost() {
        return boardService.getAllPost();
    }

    @PutMapping(value = "/post")
    public String writePost(@RequestBody BoardWriteDTO params) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Member member = (Member) auth.getPrincipal();

        return boardService.writePost(params, member);
    }

    @GetMapping(value = "/Epost")
    public AdminPostDTO getSeqPost(@RequestParam Long seq) {
        System.out.println(seq);
        return boardService.getSeqPost(seq);
    }

    @PostMapping(value = "/comment")
    public String writeComment(@RequestBody Map<String, String> params) {
        try {
            Long seq = Long.parseLong(params.get("seq"));
            String comment = params.get("comment");
            return boardService.writeComment(seq, comment);
        } catch (NumberFormatException e) {
            return "일시적인 오류 입니다. 잠시후 다시 시도해주세요";
        }
    }

    @PostMapping(value = "/email")
    public String sendEmail(@RequestBody Map<String, String> params) {
        String coupon = params.get("coupon");
        String content = params.get("comment");
        String email = params.get("email");
        return boardService.sendEmail(coupon, content, email);
    }

}
