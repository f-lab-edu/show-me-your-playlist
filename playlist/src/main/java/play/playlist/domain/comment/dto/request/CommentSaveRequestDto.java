package play.playlist.domain.comment.dto.request;

import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CommentSaveRequestDto {
    private String commentContent;
}
