package play.playlist.domain;

import lombok.Getter;
import lombok.Setter;
import play.playlist.domain.member.entity.Member;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Likes {
    @Id
    @GeneratedValue
    @Column(name = "like_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "music_id")
    private Music music;


}
