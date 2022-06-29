package play.playlist.domain.comment.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import play.playlist.domain.comment.entity.Comment;

import java.util.List;


public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Query("select c from Comment c join fetch c.music where c.music.id = :musicId")
    List <Comment> findComment(@Param("musicId") Long id);
}

