package play.playlist.domain.member.service;
import org.springframework.test.context.ActiveProfiles;
import play.playlist.domain.member.entity.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;
import play.playlist.dto.UserInfo;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@WebAppConfiguration
@TestPropertySource(properties = {"spring.config.location=classpath:application-test.yml"})
@ActiveProfiles("test")
@SpringBootTest
class MemberServiceTest {

    private static final String uid = "11";
    private static final String email = "omni11@gmail.com";
    private static final String nickname = "Yujinnn1";

    @Autowired
    private MemberService memberService;

    @Test
    void 유저_저장_테스트(){
        UserInfo dto = memberService.register(uid, email, nickname);

        List<Member> members = memberService.findAll();
        //assertThat(members.size()).isEqualTo(1);
        for(int i=0; i<members.size(); i++)
        {
            assertThat(members.get(i).getUid()).isEqualTo(uid);
            assertThat(members.get(i).getEmail()).isEqualTo(email);
            assertThat(members.get(i).getNickname()).isEqualTo(nickname);
        }
        Member member = memberService.findByUid(uid);

        assertThat(member.getUid()).isEqualTo(uid);

        assertThat(dto.getUid()).isEqualTo(uid);
        assertThat(dto.getEmail()).isEqualTo(email);
        assertThat(dto.getNickname()).isEqualTo(nickname);

    }

}