package com.example.springTest.service;

import com.example.springTest.entity.Member;
import com.example.springTest.repository.MemberRepo;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Getter
@RequiredArgsConstructor
public class MembersService {

    private final MemberRepo repo;

    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


    public Member save(Member member){
        member.setLoginPw(passwordEncoder().encode(member.getLoginPw()));
        return repo.save(member);
    }

    public boolean isPossibleId(String id){
        return repo.isPossibleId(id) == 0;
    }
}

