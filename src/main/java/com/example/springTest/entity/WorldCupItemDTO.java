package com.example.springTest.entity;

import lombok.ToString;


public class WorldCupItemDTO {
    private long id;
    private String title;

    private int winCount;
    public WorldCupItemDTO(WorldCupItem item){
        this.id = item.getId();;
        this.title = item.getTitle();
        this.winCount = item.getWinCount();
    }

    @Override
    public String toString() {
        return "{\"id\":"+this.id+",\"title\":\""+this.title+"\",\"winCount\":"+winCount+"}";
    }
}
