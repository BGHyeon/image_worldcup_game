package com.example.springTest.controllor.RestApi;

import com.example.springTest.entity.WorldCupGame;
import com.example.springTest.sequrity.MemberDetail;
import com.example.springTest.service.WorldCupGameService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class GameApi {

    private final WorldCupGameService gameService;


    @PostMapping(value = "/secure/world-cup-game")
    public String makeWorldCupGame(WorldCupGame game, @RequestPart List<MultipartFile> files, @AuthenticationPrincipal MemberDetail detail){
        game.build(files);
        game.setMakeMember(detail.getMember());
        gameService.getRepo().save(game);
        return "save";
    }

    @PostMapping(value="/world-cup-game/{winId}")
    public String wingames(@PathVariable("winId")String id){
        gameService.winGames(Long.parseLong(id));
        return "save";
    }

}
