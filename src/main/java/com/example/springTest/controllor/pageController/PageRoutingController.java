package com.example.springTest.controllor.pageController;

import com.example.springTest.entity.WorldCupGame;
import com.example.springTest.entity.WorldCupItem;
import com.example.springTest.entity.WorldCupItemDTO;
import com.example.springTest.service.WorldCupGameService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class PageRoutingController {

    private final WorldCupGameService service;

    @GetMapping(value = "/")
    public String index(Model model){
        model.addAttribute("games",service.getRepo().findAll());
        return "main";
    }
    @GetMapping(value= "/secure/worldcup-add")
    public String addWorldcupPage(){
        return "worldcup_add";
    }

    @GetMapping(value = "/worldcup/{id}")
    @Transactional(readOnly = true)
    public String toGamePage(@PathVariable("id") String id, Model model){
        WorldCupGame game = service.getRepo().getById(Long.parseLong(id));
        List<String> tmp = new ArrayList<>();
        for(WorldCupItem item : game.getGames())
            tmp.add(new WorldCupItemDTO(item).toString());
        game.getGames().clear();
        model.addAttribute("data",game);
        model.addAttribute("items",tmp);
        return "worldcup_play";
    }
    @GetMapping("/world-cup-game/result/{id}")
    @Transactional(readOnly = true)
    public String gameResultPage(@PathVariable("id")String itemid,Model model){
        long id = Long.parseLong(itemid);
        WorldCupGame game = service.getRepo().findByItemId(id);
        List<WorldCupItem> results = game.getGames();
        WorldCupItem winItem = results.stream().filter(e->e.getId() == id).findFirst().get();
        results.remove(winItem);
        service.sortByWincount(results);
        model.addAttribute("playcount",(double)game.getPlayCount());
        model.addAttribute("winitem",winItem);
        model.addAttribute("results",results);
        return "worldcup_result";
    }
    @GetMapping("/member/join")
    public String toJoinPage(){
        return "join";
    }
    @GetMapping("/login")
    public String toLoginPage(){
        return "login";
    }
}
