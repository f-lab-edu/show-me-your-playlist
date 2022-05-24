package play.playlist.domain.music.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import play.playlist.domain.music.dao.MusicRepository;
import play.playlist.domain.music.dto.MusicResponseDto;
import play.playlist.domain.music.entity.Music;


import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor // 롬북 초기화 되지 않은 필드 생성 -> 의존성 주입 가능
public class MusicService {

    private final MusicRepository musicRepository;

    // 음악 전체 조회
    public List<Music> findAllMusic() {
        return musicRepository.findAll();
    }

    // 음악 상세 조회
    public MusicResponseDto findById(Long id) {
        Music entity = musicRepository.findById(id).orElseThrow(() -> new
                IllegalArgumentException("해당 정보는 없습니다. id="+id));
        return new MusicResponseDto(entity);
    }

}