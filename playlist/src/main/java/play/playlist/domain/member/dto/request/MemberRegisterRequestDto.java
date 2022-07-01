package play.playlist.dto.request;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class memberRegisterRequestDto {
    private String uid;
    private String email;
    private String nickname;
}