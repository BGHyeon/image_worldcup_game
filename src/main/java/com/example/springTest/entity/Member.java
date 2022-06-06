package com.example.springTest.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class Member extends TimeStamp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;
    @Column
    private String name;
    @Column
    private String nickName;
    @Column(unique = true)
    private String loginId;
    @Column
    private String loginPw;

    @ElementCollection
    @CollectionTable(name="roles",joinColumns=@JoinColumn(name="member_id"))
    @Column(name="role")
    private List<String> hasRole = new ArrayList<>();
}
