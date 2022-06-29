package play.playlist.domain.comment.dto.response;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class CommentResult<T> {
    private int count;
    private T comment;

}
