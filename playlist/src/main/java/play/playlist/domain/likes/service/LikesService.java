package play.playlist.domain.likes.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import play.playlist.domain.likes.dao.LikesRepository;
import play.playlist.domain.likes.entity.Likes;
import play.playlist.domain.music.dao.MusicRepository;
import play.playlist.domain.member.entity.Member;
import play.playlist.domain.music.entity.Music;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
@Transactional
@Slf4j
@Service
public class LikesService {


    private final LikesRepository likesRepository;
    private final MusicRepository musicRepository;
    private long likeCount;

    // 좋아요를 누를 시 실행
    public Long likeMusic(Member member, Long musicId) {
        Music music = Music.builder()
                .id(musicId)
                .build();

        Optional<Music> musicResult = musicRepository.findById(musicId);
        if (musicResult.isEmpty()) {
            throw new IllegalArgumentException("해당 포스트는 존재하지 않습니다. id=" + musicId);
        }
        // 좋아요를 이미 누른 상태인지 확인
        Optional<Likes> result = likesRepository.findByMemberAndMusic(member, music);

        // 좋아요를 처음 누른 경우
        if (!result.isPresent()) {
            Likes likes = Likes.builder()
                    .member(member)
                    .music(music)
                    .build();

            // 좋아요를 증가 시킴
            musicRepository.plusLikeCount(musicId);
            likesRepository.save(likes);
            likeCount =  1L;
            return likeCount;

        }
        // 좋아요를 이미 누른 경우 좋아요를 해제
        else {
            // Music의 likeCount 값 에서 1을 빼준다.
            musicRepository.minusLikeCount(musicId);
            Likes likes = result.get();
            likesRepository.delete(likes);
            likeCount = 0;
            return likeCount;
        }
    }

}