package play.playlist.config.auth;

import com.google.firebase.auth.FirebaseAuth;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import play.playlist.domain.member.service.MemberService;
import play.playlist.filter.JwtFilter;


@Slf4j
@RequiredArgsConstructor
@Configuration
public class AuthConfig {

    private final MemberService memberService;

    private final FirebaseAuth firebaseAuth;


    @Bean
    @Profile({"prod", "default"})
    public AuthFilterContainer firebaseAuthFilter() {
        log.info("Initializing Firebase AuthFilter");
        AuthFilterContainer authFilterContainer = new AuthFilterContainer();
        authFilterContainer.setAuthFilter(new JwtFilter(memberService, firebaseAuth));
        return authFilterContainer;
    }
}