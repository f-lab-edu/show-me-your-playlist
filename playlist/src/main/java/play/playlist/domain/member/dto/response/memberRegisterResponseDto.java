package play.playlist.domain.member.dto.response;
import lombok.Getter;
import lombok.Setter;
import play.playlist.domain.member.entity.Member;


@Getter
@Setter
public class memberRegisterResponseDto {
    private Long memberId;
    private String uid;
    private String email;
    private String nickname;

    public memberRegisterResponseDto(Member member) {
        this.memberId = member.getId();
        this.uid = member.getUid();
        this.email = member.getEmail();
        this.nickname = member.getNickname();
    }
}