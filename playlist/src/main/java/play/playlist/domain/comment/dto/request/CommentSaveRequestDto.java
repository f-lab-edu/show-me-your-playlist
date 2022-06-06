package play.playlist.domain.comment.dto.request;

import lombok.*;
import play.playlist.domain.comment.entity.Comment;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CommentSaveRequestDto {
    private String commentContent;

}
