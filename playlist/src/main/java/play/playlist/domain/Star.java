package play.playlist.domain;
import lombok.Getter;
import lombok.Setter;
import play.playlist.domain.member.entity.Member;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Star {
    @Id
    @GeneratedValue
    @Column(name = "star_id")
    private Long id;
    private Integer score;
    private String preferSongName;
    private String preferArtist;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "music_id")
    private Music music;
}
