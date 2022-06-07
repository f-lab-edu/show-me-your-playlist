package play.playlist.domain.music.entity;

import jdk.jshell.Snippet;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
public class Music {
    @Id
    @GeneratedValue
    @Column(name = "music_id")
    private Long id;
    private String genre;
    private String artist;
    private String songName;

    @Column(name = "like_count")
    private Long likeCount;
}
