package play.playlist.domain.comment.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import play.playlist.domain.comment.entity.Comment;
import play.playlist.domain.music.entity.Music;
import play.playlist.domain.member.entity.Member;

@Getter
@NoArgsConstructor
public class CommentResponseDto {

    private Long id;
    private Member member;
    private Music music;
    private String commentContent;

    public CommentResponseDto(Comment comment) {
        this.id = comment.getId();
        this.member = comment.getMember();
        this.music = comment.getMusic();
        this.commentContent = comment.getCommentContent();
    }
}