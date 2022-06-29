package play.playlist.domain.music.dto;

import lombok.Getter;
import play.playlist.domain.music.entity.Music;

@Getter
public class MusicResponseDto {
    private Long id;
    private String genre;
    private String artist;
    private String songName;

    public MusicResponseDto(Music entity) {
        this.id = entity.getId();
        this.genre = entity.getGenre();
        this.artist = entity.getArtist();
        this.songName = entity.getSongName();
    }
}
