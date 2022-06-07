package play.playlist.domain.likes.entity;

import lombok.*;
import play.playlist.domain.member.entity.Member;
import play.playlist.domain.music.entity.Music;

import javax.persistence.*;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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

    @Builder
    public Likes(long id, Member member, Music music) {
        this.id = id;
        this.member = member;
        this.music = music;
    }

}
