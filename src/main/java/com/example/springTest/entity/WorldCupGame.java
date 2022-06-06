package com.example.springTest.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@ToString
public class WorldCupGame extends TimeStamp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private long id;
    @Column
    private String name;
    @ManyToOne(fetch = FetchType.LAZY,targetEntity = Member.class,cascade = CascadeType.ALL)
    @JoinColumn
    private Member makeMember;
    @Column
    private int playCount = 0;
    @Column
    private String description;
    @OneToMany(fetch = FetchType.LAZY,targetEntity=WorldCupItem.class,cascade = CascadeType.ALL)
    private List<WorldCupItem> games = new ArrayList<WorldCupItem>();

    public void addItem(WorldCupItem item){
        this.games.add(item);
    }
    public void removeItem(WorldCupItem item){
        this.games.remove(item);
    }
    public void build(List<MultipartFile> files){
        for (int i = 0; i < files.size(); i++) {
            try {
                games.get(i).setImage(files.get(i).getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public void addPlayCount(){
        ++playCount;
    }
}
