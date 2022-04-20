package play.playlist.domain.member.controller;

import org.springframework.security.core.Authentication;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import play.playlist.domain.member.service.MemberService;
import play.playlist.dto.RegisterInfo;
import play.playlist.dto.UserInfo;
import play.playlist.util.RequestUtil;
import play.playlist.domain.member.entity.Member;


@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    final private FirebaseAuth firebaseAuth;
    final private MemberService memberService;


    @PostMapping("")
    public UserInfo register(@RequestHeader("Authorization") String authorization,
                             @RequestBody RegisterInfo registerInfo) {
        // TOKEN을 가져온다.
        FirebaseToken decodedToken;
        try {
            String token = RequestUtil.getAuthorizationToken(authorization);
            decodedToken = firebaseAuth.verifyIdToken(token);
        } catch (IllegalArgumentException | FirebaseAuthException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,
                    "{\"code\":\"INVALID_TOKEN\", \"message\":\"" + e.getMessage() + "\"}");
        }
        // 사용자를 등록한다.
        Member registeredMember = memberService.register(
                decodedToken.getUid(), decodedToken.getEmail(), registerInfo.getNickname());
        return new UserInfo(registeredMember);
    }


    @GetMapping("/login")
    public UserInfo  login(Authentication authentication) {
        Member member = ((Member) authentication.getPrincipal());
        return new UserInfo(member);
    }
}
