package play.playlist.domain.music.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import play.playlist.domain.music.dao.MusicRepository;
import play.playlist.domain.music.entity.Music;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;


@ActiveProfiles("test")
@SpringBootTest
public class MusicRepositoryTest {

    @Autowired
    MusicRepository musicRepository;

    @Test
    public void get_all_musicInfo() {

        // when
        List<Music> music = musicRepository.findAll();

        // then
        assertThat(music, hasSize(600));
    }

    @Test
    public void get_one_musicInfo() {

        // when
        Music one = musicRepository.findById(6L).orElseThrow(() -> new
                IllegalArgumentException("해당 정보는 없습니다."));

        // then
        assertThat(one.getArtist()).isEqualTo("아이유");
        assertThat(one.getSongName()).isEqualTo("금요일에 만나요 (Feat. 장이정 Of HISTORY)");
    }
}
