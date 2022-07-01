package play.playlist.domain.likes.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import play.playlist.domain.likes.service.LikesService;
import play.playlist.domain.member.entity.Member;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class LikesController {
    private final LikesService likesService;

    @GetMapping("/like/{musicId}")
    public Long likeMusic(
            @PathVariable Long musicId, Authentication authentication) {

        Member member = (Member)authentication.getPrincipal();
        return likesService.likeMusic(member, musicId);
    }
}
