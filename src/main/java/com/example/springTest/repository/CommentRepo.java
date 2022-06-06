package com.example.springTest.repository;

import com.example.springTest.entity.Comment;
import org.hibernate.annotations.Parameter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface CommentRepo extends JpaRepository<Comment, Long>{

    @Query("Select this_ from Comment this_ where this_.member.id=:id")
    public ArrayList<Comment> getCommentsByMemberId(@Param("id") long id);

}
