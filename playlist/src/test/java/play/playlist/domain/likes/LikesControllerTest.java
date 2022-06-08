package play.playlist.domain.likes;

import org.assertj.core.api.Assertions;
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
import play.playlist.domain.member.dao.MemberRepository;
import play.playlist.domain.member.entity.Member;
import play.playlist.domain.music.entity.Music;
import play.playlist.domain.likes.entity.Likes;
import play.playlist.domain.music.dao.MusicRepository;
import play.playlist.domain.likes.dao.LikesRepository;
import javax.servlet.Filter;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@SpringBootTest
public class LikesControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private Filter springSecurityFilterChain;

    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private MusicRepository musicRepository;

    @Autowired
    private LikesRepository likesRepository;

    @BeforeEach
    public void beforeEach() {
        Optional<Member> member = memberRepository.findByUid(member1.getUid());

        if(member.isEmpty())
            memberRepository.save(member1);

        mockMvc = MockMvcBuilders.webAppContextSetup(wac)
                .addFilter(springSecurityFilterChain)
                .build();
    }


    private static Member member1 = Member.builder()
            .uid("qwehwqrwe")
            .email("asdrwe@gmail.com")
            .nickname("asdrwe")
            .build();

    // pk 문제 나중에 해결하기
    @DisplayName("좋아요 등록 테스트")
    @Test
    void likeMusic() throws Exception {

        Music music1 = musicRepository.findById(8L).orElseThrow(() -> new
                IllegalArgumentException("해당 정보는 없습니다."));
        assertThat(music1.getArtist()).isEqualTo("로꼬");

        mockMvc.perform(
                get("/like/"+music1.getId())
                        .header("Authorization", "Bearer " + member1.getUid())
        )
                .andDo(print())
                .andExpect(status().isOk());


        Optional<Likes> likes = likesRepository.findByMemberAndMusic(member1, music1);
        Optional<Music> musicAfter = musicRepository.findById(music1.getId());

        Assertions.assertThat(musicAfter.get().getLikeCount()).isEqualTo(1);
        Assertions.assertThat(likes.get().getMember().getId()).isEqualTo(member1.getId());
    }
}
