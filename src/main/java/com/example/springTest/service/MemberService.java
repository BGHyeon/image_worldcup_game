package com.example.springTest.service;

import com.example.springTest.entity.Member;
import com.example.springTest.repository.MemberRepo;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Getter
public class MemberService {
    private final MemberRepo repo;
    private final BCryptPasswordEncoder encoder;
    public Member saveMember(Member member){
        member.setLoginPw(encoder.encode(member.getLoginPw()));
        return repo.save(member);
    }
}
