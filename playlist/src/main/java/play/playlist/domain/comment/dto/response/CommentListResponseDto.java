package play.playlist.domain.comment.dto.response;

import lombok.Getter;
import play.playlist.domain.comment.entity.Comment;


@Getter
public class CommentListResponseDto {

    private Long id;
    private Long memberId;
    private Long musicId;
    private String commentContent;

    public CommentListResponseDto(Comment comment) {
        this.id = comment.getId();
        this.memberId = comment.getMember().getId();
        this.musicId = comment.getMusic().getId();
        this.commentContent = comment.getCommentContent();
    }
}
