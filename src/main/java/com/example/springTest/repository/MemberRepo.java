package com.example.springTest.repository;

import com.example.springTest.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MemberRepo extends JpaRepository<Member,Long> {
    @Query("select count(this_) from Member this_ where this_.loginId=:loginid")
    public int isPossibleId(String loginid);
    // Select * from table  -> Database에서 사용하는 SQL
    // Select name from EntityClass name  -> Hibernate Java에서 사용하는 SQL
    @Query("Select this_ from Member this_ where this_.loginId=:id")
    public Member findByLoginId(@Param("id") String id);
}
