package play.playlist.domain.comment.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import play.playlist.domain.comment.dao.CommentRepository;
import play.playlist.domain.comment.dto.request.CommentSaveRequestDto;
import play.playlist.domain.comment.entity.Comment;
import play.playlist.domain.member.dao.MemberRepository;
import play.playlist.domain.music.dao.MusicRepository;
import play.playlist.domain.music.entity.Music;
import play.playlist.domain.member.entity.Member;
import javax.servlet.Filter;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@SpringBootTest
public class CommentControllerTest {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private MusicRepository musicRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Autowired
    private Filter springSecurityFilterChain;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void beforeEach() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac)
                .addFilter(springSecurityFilterChain)
                .build();
    }

    private static Music music1 = Music.builder()
            .id(8L)
            .genre("Korean Ballad")
            .artist("로꼬")
            .songName("우연히 봄")
            .build();

    private static Member member1 = Member.builder()
            .uid("qwehwq")
            .email("asd@gmail.com")
            .nickname("asd")
            .build();

    private static Member member2 = Member.builder()
            .uid("werert")
            .email("asdf@gmail.com")
            .nickname("asdf")
            .build();

    private static Comment comment1 = Comment.builder()
            .id(19L) // db에 저장된 값
            .member(member1)
            .music(music1)
            .commentContent("댓글 달았습니다")
            .build();

    @BeforeEach
    void setUp() {
        Optional<Member> member = memberRepository.findByUid(member1.getUid());
        Optional<Music> music = musicRepository.findById(music1.getId());
        Optional<Comment> comment = commentRepository.findById(comment1.getId());

        if(member.isEmpty())
            memberRepository.save(member1);
        if(music.isEmpty())
            musicRepository.save(music1);


        if(comment.isEmpty()) {
            Optional<Member> member2 = memberRepository.findByUid(member1.getUid());
            Optional<Music> music2 = musicRepository.findById(music1.getId());
            Comment comment2 = Comment.builder()
                    .member(member2.get())
                    .music(music2.get())
                    .commentContent("댓글 달았습니다 2")
                    .build();
            commentRepository.save(comment2);
        }
    }

    @DisplayName("댓글 등록 테스트")
    @Test
    void saveComment() throws Exception {
        Optional<Music> music = musicRepository.findById(music1.getId());

        CommentSaveRequestDto dto =
                CommentSaveRequestDto.builder()
                        .commentContent("안녕하세요 반가워요 잘있어요 다시만나요")
                        .build();


        mockMvc.perform(
                post("/music/" + music.get().getId() + "/comments")
                        .header("Authorization", "Bearer " + member1.getUid())
                        .content(objectMapper.writeValueAsString(dto))
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding(StandardCharsets.UTF_8)
                        .accept(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isOk());
    }

    @DisplayName("댓글 수정 테스트")
    @Test
    void updateComment() throws Exception {
        Optional<Comment> comment = commentRepository.findById(comment1.getId());

        CommentSaveRequestDto dto =
                CommentSaveRequestDto.builder()
                        .commentContent("안녕하세요 반가워요!!")
                        .build();

        mockMvc.perform(
                patch("/comments/"+ comment.get().getId())
                        .header("Authorization", "Bearer " + member1.getUid())
                        .content(objectMapper.writeValueAsString(dto))
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding(StandardCharsets.UTF_8)
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk());
    }

    @DisplayName("댓글 삭제 테스트")
    @Test
    void deleteComment() throws Exception {

        Optional<Comment> comment = commentRepository.findById(comment1.getId());

        mockMvc.perform(
                delete("/comments/"+ comment.get().getId())
                        .header("Authorization", "Bearer " + member1.getUid())
        )
                .andDo(print())
                .andExpect(status().isOk());
    }

    @DisplayName("댓글 전체 조회 테스트")
    @Test
    void findAllComments() throws Exception {
        Optional<Music> music = musicRepository.findById(music1.getId());

        mockMvc.perform(
                get("/music/" + music.get().getId() + "/comments")
        )
                .andDo(print())
                .andExpect(status().isOk());
    }

    @DisplayName("댓글 단건 조회 테스트")
    @Test
    void findCommentById() throws Exception {
        Optional<Comment> comment = commentRepository.findById(comment1.getId());

        mockMvc.perform(
                get("/comments/"+ comment.get().getId())
        )
                .andDo(print())
                .andExpect(status().isOk());
    }
}