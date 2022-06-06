package com.example.springTest.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Type;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.io.IOException;
import java.util.List;

@Getter
@Setter
@Entity
@ToString
public class WorldCupItem extends TimeStamp{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private long id;
    @Column
    private String title;
    @Lob
    @Basic(fetch = FetchType.LAZY)
    private byte[] image;
    @Column
    private int winCount = 0;

    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL,targetEntity = Comment.class)
    private List<Comment> comments;
    public void addWincount(){
        ++winCount;
    }

    public void addComment(Comment comment){
        comments.add(comment);
    }
}
