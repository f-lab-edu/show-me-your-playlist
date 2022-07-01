package play.playlist.domain.comment.controller;


import play.playlist.domain.comment.dto.response.CommentListResponseDto;
import play.playlist.domain.comment.dto.response.CommentResult;
import play.playlist.domain.member.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import play.playlist.domain.comment.dto.request.CommentSaveRequestDto;
import play.playlist.domain.comment.service.CommentService;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class CommentController {

    private final CommentService commentService;

    // 상세 음악 정보에서 댓글 등록
    @PostMapping("/music/{musicId}/comments")
    public Long save(@PathVariable Long musicId,
                     @RequestBody CommentSaveRequestDto dto,
                     Authentication authentication) {
        Member member = (Member) authentication.getPrincipal();
        return commentService.save(musicId, dto, member);
    }

    // 상세 음악 정보의 댓글 전체 조회
    @GetMapping("/music/{musicId}/comments")
    public CommentResult<List<CommentListResponseDto>> findAllComments(@PathVariable Long musicId) {
        List<CommentListResponseDto> dto = commentService.findAllComments(musicId);
        return new CommentResult<>(dto.size(), dto);
    }

    // 댓글 단건 조회
    @GetMapping("/comments/{commentId}")
    public Long findComment(@PathVariable Long commentId) {
        commentService.findCommentById(commentId);
        return commentId;
    }

     // 댓글 수정
    @PatchMapping("/comments/{commentId}")
    public Long update(@PathVariable Long commentId, @RequestBody CommentSaveRequestDto dto, Authentication authentication) {
        Member member = (Member)authentication.getPrincipal();
        return commentService.update(commentId, dto, member);
    }

    // 댓글 삭제
    @DeleteMapping("/comments/{commentId}")
    public Long delete(@PathVariable Long commentId , Authentication authentication) {
        Member member = (Member)authentication.getPrincipal();
        commentService.delete(commentId, member);
        return commentId;
    }


}
