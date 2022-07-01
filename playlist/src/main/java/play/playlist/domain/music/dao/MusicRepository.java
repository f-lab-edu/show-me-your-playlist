package play.playlist.domain.music.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import play.playlist.domain.member.entity.Member;
import play.playlist.domain.music.entity.Music;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
public interface MusicRepository extends JpaRepository<Music, Long> {

    @Modifying //select 제외 쿼리
    @Query("update Music a set a.likeCount = a.likeCount + 1 where a.id = :id")
    int plusLikeCount(@Param("id") Long id);

    @Modifying
    @Query("update Music a set a.likeCount = a.likeCount - 1 where a.id = :id")
    int minusLikeCount(@Param("id") Long id);

}
