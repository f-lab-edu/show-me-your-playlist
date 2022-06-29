package play.playlist.domain.likes.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import play.playlist.domain.likes.entity.Likes;
import play.playlist.domain.music.entity.Music;
import play.playlist.domain.member.entity.Member;

import java.util.Optional;

public interface LikesRepository extends JpaRepository<Likes, Long> {


    Optional<Likes> findByMemberAndMusic(Member member, Music music);
}
