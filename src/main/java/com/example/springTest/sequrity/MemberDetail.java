package com.example.springTest.sequrity;

import com.example.springTest.entity.Member;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

@Getter
public class MemberDetail implements UserDetails {
    private Member member;

    public MemberDetail(Member member) {
        this.member = member;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // 특별한 권한 시스템을 사용하지 않을경우
        // return Collections.EMPTY_LIST;
        // 를 사용하면 된다.
        ArrayList<GrantedAuthority> auths = new ArrayList<>();
        for(String role : member.getHasRole()){
            auths.add(new GrantedAuthority() {
                @Override
                public String getAuthority() {
                    return role;
                }
            });
        }
        return auths;
    }

    @Override
    public String getPassword() {
        return member.getLoginPw();
    }

    @Override
    public String getUsername() {
        return member.getLoginId();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
