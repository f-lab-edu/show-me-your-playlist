package play.playlist.domain.music.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import play.playlist.domain.music.dto.response.MusicResponseDto;
import play.playlist.domain.music.entity.Music;
import play.playlist.domain.music.service.MusicService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MusicController {

    private final MusicService musicService;

    @GetMapping("/music")
    public List<Music> findAllMusic() {
        return musicService.findAllMusic();
    }

}
