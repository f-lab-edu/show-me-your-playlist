package play.playlist.dto;
import lombok.Getter;
import lombok.Setter;
import play.playlist.domain.member.entity.Member;


@Getter
@Setter
public class UserInfo {
    private Long memberId;
    private String uid;
    private String email;
    private String nickname;

    public UserInfo(Member member) {
        this.memberId = member.getId();
        this.uid = member.getUid();
        this.email = member.getEmail();
        this.nickname = member.getNickname();
    }
}