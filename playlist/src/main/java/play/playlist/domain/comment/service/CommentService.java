package play.playlist.domain.comment.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import play.playlist.domain.comment.dao.CommentRepository;
import play.playlist.domain.comment.dto.response.CommentListResponseDto;
import play.playlist.domain.comment.dto.response.CommentResponseDto;
import play.playlist.domain.comment.dto.request.CommentSaveRequestDto;
import play.playlist.domain.comment.entity.Comment;
import play.playlist.domain.music.dao.MusicRepository;
import play.playlist.domain.member.entity.Member;
import play.playlist.domain.music.entity.Music;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final MusicRepository musicRepository;

    @Transactional(readOnly = true)
    public List<CommentListResponseDto> findAllComments(Long musicId) {

        Music music = musicRepository.findById(musicId)
                .orElseThrow(() -> new IllegalArgumentException("해당 포스트가 없습니다. " + musicId)
                );

        return commentRepository.findComment(music.getId()).stream()
                .map(CommentListResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public CommentResponseDto findCommentById(Long commentId) {

        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("해당 댓글은 존재하지 않습니다."+ commentId));
        CommentResponseDto dto = new CommentResponseDto(comment);

        return dto;
    }

    @Transactional
    public Long save(Long musicId, CommentSaveRequestDto dto, Member member) {

        Music music = musicRepository.findById(musicId)
                .orElseThrow(() -> new IllegalArgumentException("해당 포스트가 없습니다. " + musicId)
        );

        Comment comment = Comment.builder()
                .music(music)
                .member(member)
                .commentContent(dto.getCommentContent())
                .build();

        return commentRepository.save(comment).getId();
    }

    @Transactional
    public Long update(Long commentId, CommentSaveRequestDto dto, Member member) {

        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> {
                    throw new IllegalArgumentException("해당 댓글은 존재하지 않습니다."+ commentId);
                });

        if (comment.getMember().getId() != member.getId()) {
            throw new IllegalArgumentException("해당 회원의 리뷰가 아닙니다. ");
        }

        comment.update(dto.getCommentContent());
        return commentId;
    }

    @Transactional
    public void delete(Long commentId, Member member)
    {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("해당 댓글은 존재하지 않습니다."+ commentId));

        if (comment.getMember().getId() != member.getId()) {
            throw new IllegalArgumentException("해당 회원의 리뷰가 아닙니다. ");
        }

        commentRepository.delete(comment);
    }
}
