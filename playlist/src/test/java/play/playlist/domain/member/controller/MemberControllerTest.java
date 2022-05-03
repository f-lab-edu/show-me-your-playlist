package play.playlist.domain.member.controller;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import play.playlist.domain.member.service.MemberService;

import javax.servlet.Filter;
import java.nio.charset.StandardCharsets;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebAppConfiguration
//@TestPropertySource(properties = {"spring.config.location=classpath:application-test.yml"})
@ActiveProfiles("test")
//@Slf4j
@Transactional
@SpringBootTest
@AutoConfigureMockMvc
class MemberControllerTest {

    private static final String token = "eyJhbGciOiJSUzI1NiIsImtpZCI6ImM2NzNkM2M5NDdhZWIxOGI2NGU1OGUzZWRlMzI1NWZiZjU3NTI4NWIiLCJ0eXAiOiJKV1QifQ.eyJuYW1lIjoiWXVqaW4gSG9uZyIsInBpY3R1cmUiOiJodHRwczovL2xoMy5nb29nbGV1c2VyY29udGVudC5jb20vYS9BQVRYQUp6RzZoLU5UUGZsRmF0anFYejlQR05xYTZPLXZNWjJaRUE3al9vYz1zOTYtYyIsImlzcyI6Imh0dHBzOi8vc2VjdXJldG9rZW4uZ29vZ2xlLmNvbS9zaG93LW1lLXlvdXItcGxheWxpc3QiLCJhdWQiOiJzaG93LW1lLXlvdXItcGxheWxpc3QiLCJhdXRoX3RpbWUiOjE2NTEyMTAxODIsInVzZXJfaWQiOiJFdGNpQjZwNVNJZVFJc2JnUloyVDNDbmhrYWsyIiwic3ViIjoiRXRjaUI2cDVTSWVRSXNiZ1JaMlQzQ25oa2FrMiIsImlhdCI6MTY1MTIxMDE4MiwiZXhwIjoxNjUxMjEzNzgyLCJlbWFpbCI6Im9tbmlyZWFkZXIwQGdtYWlsLmNvbSIsImVtYWlsX3ZlcmlmaWVkIjp0cnVlLCJmaXJlYmFzZSI6eyJpZGVudGl0aWVzIjp7Imdvb2dsZS5jb20iOlsiMTA0NzkzMTk1NjQ3MDAyNTY2NDg4Il0sImVtYWlsIjpbIm9tbmlyZWFkZXIwQGdtYWlsLmNvbSJdfSwic2lnbl9pbl9wcm92aWRlciI6Imdvb2dsZS5jb20ifX0.MgnAJXx5kVV_I4tf8WUgeLdqP1ml7IkGkQAKnwTHweFOXqY_T3seE9m0usGjDof-9yhBWKcfX3F7_8ALBOJquQjvzBbLa0DBe9a94K1t-Hiw6IGy_IKlAJ0YOahSbJzlXQrSv06VCycF44fXLS6f6pYRZFrYFPCdvwRkWom6E7rQ_qyPkdEHulJN-dwN1MyWNN24eRvhG9CfZ9uiLhSDGysagPRIDzE7A1-I-0sKMlkOvk7cVRRj690Q-umnfVXAJ6kaxuvuVVSwKaYbmQ8ns-lWy-DpDmYz6s9oWhxjP7qgxooXFikudmShFBoYT7tO9712W6eUKST21lE9XBgjBA";
    private static final String uid = "EtciB6p5SIeQIsbgRZ2T3Cnhkak2";
    private static final String email = "omnireader0@gmail.com";
    private static final String nickname = "Yujin Hong";

    @Autowired
    private MemberService memberService;

    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private Filter springSecurityFilterChain;


    @DisplayName("로그인 시 발급 받은 토큰을 헤더에 넘겨서 로그인한 유저 정보 가져오기")
    @Test
    void get_logged_in_userInfo() throws Exception {
        memberService.register(uid, email, nickname);

        ResultActions resultActions = mockMvc.perform(
                get("/members/login")
                        .header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding(StandardCharsets.UTF_8)
                        .accept(MediaType.APPLICATION_JSON)
        )
                .andDo(print());

        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("uid").value(uid))
                .andExpect(jsonPath("email").value(email))
                .andExpect(jsonPath("nickname").value(nickname));
    }
}
