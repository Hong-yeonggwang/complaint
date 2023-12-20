package com.yongin.complaint.JPA.Repository;

import com.yongin.complaint.DTO.Board.AdminPostDTO;
import com.yongin.complaint.DTO.Board.UserPostDTO;
import com.yongin.complaint.JPA.Entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {
    @Query("select new com.yongin.complaint.DTO.Board.UserPostDTO(b.subject,b.content,b.tag,b.boardSeq,b.writer.name, b.status,b.regulationDate,b.completeDate) from Board b where b.writer.memberSeq = :SEQ")
    List<UserPostDTO> getUserPost(@Param(value = "SEQ") Long seq);

    @Query("select new com.yongin.complaint.DTO.Board.UserPostDTO(b.subject,b.content,b.tag,b.boardSeq,b.writer.name, b.status,b.regulationDate,b.completeDate) from Board b where b.status = 'no'")
    List<UserPostDTO> getAllPostNo();

    @Query("select new com.yongin.complaint.DTO.Board.UserPostDTO(b.subject,b.content,b.tag,b.boardSeq,b.writer.name, b.status,b.regulationDate,b.completeDate) from Board b where b.status = 'yes'")
    List<UserPostDTO> getAllPostYes();

    @Query("select new com.yongin.complaint.DTO.Board.AdminPostDTO(b.subject,b.content,b.tag,b.boardSeq,b.writer.name,b.writer.phoneNumber,b.writer.email, b.status,b.regulationDate,b.completeDate,b.comment) from Board b where b.boardSeq = :SEQ")
    AdminPostDTO findToId(@Param(value = "SEQ") Long seq);
}
