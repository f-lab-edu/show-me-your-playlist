package play.playlist.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Music {
    @Id
    @GeneratedValue
    @Column(name = "music_id")
    private Long id;
    private String genre;
    private String artist;
    private String songName;

}
