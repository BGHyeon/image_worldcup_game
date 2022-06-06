package com.example.springTest.controllor.RestApi;

import com.example.springTest.entity.Member;
import com.example.springTest.service.MembersService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberApi {
    private final MembersService service;

    @PostMapping(path = "/member")
    public Member saveMember(Member member){
        return service.save(member);
    }
    @PostMapping(path = "/member/{id}")
    public boolean isPossibleId(@PathVariable("id") String id){
        return service.isPossibleId(id);
    }

}
