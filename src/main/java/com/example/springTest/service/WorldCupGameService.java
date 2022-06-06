package com.example.springTest.service;

import com.example.springTest.entity.WorldCupGame;
import com.example.springTest.entity.WorldCupItem;
import com.example.springTest.repository.WorldCupGameRepo;
import com.example.springTest.repository.WorldCupItemRepo;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
@Getter
@RequiredArgsConstructor
public class WorldCupGameService {
    private final WorldCupGameRepo repo;
    private final WorldCupItemRepo itemRepo;
    public List<WorldCupGame> getGameList(){
        return repo.findAll();
    }

    public void winGames(long itemId){
        WorldCupGame game= repo.findByItemId(itemId);
        WorldCupItem item = itemRepo.findById(itemId).get();
        game.addPlayCount();
        item.addWincount();
        repo.save(game);
        itemRepo.save(item);
    }
    public List<WorldCupItem> getGameResultItems(long id){
        return itemRepo.getWorldCupItemsResultByItemsId(id);
    }
    public void sortByWincount(List<WorldCupItem> items){
        Collections.sort(items, new Comparator<WorldCupItem>() {
            @Override
            public int compare(WorldCupItem o1, WorldCupItem o2) {
                int ret = 0;
                if(o1.getWinCount() > o2.getWinCount())
                    ret = -1;
                else if(o1.getWinCount() < o2.getWinCount())
                    ret = 1;
                return ret;
            }
        });
    }
}
