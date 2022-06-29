package play.playlist.domain.comment.entity;


import lombok.Builder;
import lombok.NoArgsConstructor;
import play.playlist.domain.member.entity.Member;
import play.playlist.domain.music.entity.Music;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
@NoArgsConstructor
public class Comment {

    @Id
    @GeneratedValue
    @Column(name = "comment_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member; // 작성자

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "music_id")
    private Music music; // 음악 포스트

    @Column(nullable = false)
    private String commentContent;

    @Builder
    public Comment(Long id, Member member, Music music, String commentContent) {
        this.id = id;
        this.member = member;
        this.music = music;
        this.commentContent = commentContent;
    }

    public void update(String commentContent) {
        this.commentContent = commentContent;
    }
}
