package play.playlist.domain.member.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import play.playlist.domain.member.entity.Member;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;


import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import play.playlist.domain.member.service.MemberService;
import play.playlist.dto.RegisterInfo;
import play.playlist.dto.UserInfo;
import play.playlist.util.RequestUtil;


@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {

    private final FirebaseAuth firebaseAuth;

    private final MemberService memberService;

    @PostMapping("")
    public ResponseEntity<UserInfo> register(@RequestHeader("Authorization") String authorization,
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
        UserInfo registeredUser = memberService.register(
                decodedToken.getUid(), decodedToken.getEmail(), registerInfo.getNickname());
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(registeredUser);
    }

    @GetMapping("/login")
    public UserInfo  login(Authentication authentication) {
        Member member = ((Member)authentication.getPrincipal());
        return new UserInfo(member);
    }
}
