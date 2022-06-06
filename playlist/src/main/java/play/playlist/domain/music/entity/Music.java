package play.playlist.domain.music.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter @Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Music {
    @Id
    @GeneratedValue
    @Column(name = "music_id")
    private Long id;
    private String genre;
    private String artist;
    private String songName;

}
