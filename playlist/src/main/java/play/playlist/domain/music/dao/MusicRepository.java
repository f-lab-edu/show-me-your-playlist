package play.playlist.domain.music.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import play.playlist.domain.music.entity.Music;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public interface MusicRepository extends JpaRepository<Music, Long> {

}
