package play.playlist.domain.comment.service;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import play.playlist.domain.comment.dao.CommentRepository;
import play.playlist.domain.comment.dto.request.CommentSaveRequestDto;
import play.playlist.domain.comment.dto.response.CommentResponseDto;
import play.playlist.domain.comment.entity.Comment;
import play.playlist.domain.member.dao.MemberRepository;
import play.playlist.domain.member.entity.Member;
import play.playlist.domain.music.dao.MusicRepository;
import play.playlist.domain.music.entity.Music;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

/*
1. 상세 음악 정보에서 댓글 등록 o
- 댓글 작성시 상세 음악 정보가 없는 경우 테스트 o
- 댓글 작성 테스트 o

2. 댓글 수정 o
- 댓글 작성시 상세 음악 정보가 없는 경우 테스트 o
- 댓글 작성 테스트 o

3. 댓글 삭제 o

---
controllerTest에서 확인하기

4. 상세 음악 정보의 댓글 전체 조회
- 비로그인 시 댓글 조회 가능한지

5. 댓글 단건 조회
- 비로그인 시 댓글 조회 가능한지

 * */

@ActiveProfiles("test")
@SpringBootTest
@RunWith(MockitoJUnitRunner.Silent.class) // mock, InjectMocks 사용할 때 mock 모의객체를 초기화 ,silent ?
@ExtendWith(MockitoExtension.class)
//@MockitoSettings(strictness = Strictness.LENIENT)
public class CommentServiceTest {

    //member 필드
    private static Long memberId = 1L;
    private static final String uid = "abcd";
    private static final String email = "abcd@daum.com";
    private static final String nickname = "abcd";

    //music 필드
    private static Long musicId = 1L;
    private static String artist = "아이유";
    private static String genre = "Korean Ballad";
    private static String songName = "내 손을 잡아";

    // comment 필드
    private static Long commentId = 1L;
    private static String commentContent = "댓글";

    @Mock
    private MemberRepository memberRepository;

    @Mock
    private MusicRepository musicRepository;

    @Mock
    private CommentRepository commentRepository;

    @InjectMocks // mock으로 선언한 인스턴스를 injectMocks 인스턴스에 주입
    private CommentService commentService;

    @InjectMocks
    private Member member;

    @InjectMocks
    private Music music;

    @InjectMocks
    private Comment comment;

    @BeforeEach
    void beforeEach() {

        member = Member.builder()
                .id(memberId)
                .uid(uid)
                .email(email)
                .nickname(nickname)
                .build();

        music = Music.builder()
                .id(musicId)
                .genre(genre)
                .artist(artist)
                .songName(songName)
                .build();

        comment = Comment.builder()
                .id(commentId)
                .member(member)
                .music(music)
                .commentContent(commentContent)
                .build();

    }

    @Test
    @DisplayName("댓글 작성 시 상세 음악 정보가 없는 경우 테스트")
    public void save_comment_without_musicInfo() {

        //given
        Long musicId = 667L;

        //when
        //then
        assertThrows(Exception.class,
                () -> commentService.save(musicId, new CommentSaveRequestDto(commentContent), member));
    }

    @Test
    @DisplayName("댓글 작성 성공 테스트")
    public void save_comment() throws Exception {
        //given
        when(musicRepository.findById(any())).thenReturn(Optional.ofNullable(music));
        when(commentRepository.save(any())).thenReturn(comment);

        Long expected = new CommentResponseDto(comment).getId();

        //when
        Long actual = commentService.save(musicId, new CommentSaveRequestDto(commentContent), member);

        //then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("댓글 수정 시 수정할 댓글 없는 경우 테스트")
    public void update_comment_without_commentInfo() throws Exception {
        //given
        CommentSaveRequestDto requestDto = new CommentSaveRequestDto("수정 댓글");
        when(commentRepository.findById(any())).thenReturn(Optional.empty());

        //when
        //then
        assertThrows(Exception.class, () ->
                commentService.save(musicId, new CommentSaveRequestDto(commentContent), member));
    }

    @Test
    @DisplayName("권한 있는 사용자 댓글 수정 성공 테스트")
    public void update_comment() throws Exception {

        //given
        when(commentRepository.findById(any())).thenReturn(Optional.ofNullable(comment));

        String updateContent = "댓글 수정이요";
        CommentSaveRequestDto dto = new CommentSaveRequestDto(updateContent);
        Long expected = new CommentResponseDto(commentId, updateContent).getId();

        //when
        comment.setMember(new Member());
        comment.setMember(member);
        Long actual = commentService.update(commentId, dto, member);

        //then
        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("권한 있는 사용자 댓글 삭제 테스트")
    @Test
    public void delete_comment() throws Exception {

        // given
        when(commentRepository.findById(any())).thenReturn(Optional.ofNullable(comment));
        Long expected = commentId;

        // when
        comment.setMember(new Member());
        comment.setMember(member);
        Long actual = commentService.delete(commentId, member);

        // then
        assertThat(actual).isEqualTo(expected);
    }

}