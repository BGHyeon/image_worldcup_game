package com.example.springTest.service;

import com.example.springTest.entity.Comment;
import com.example.springTest.entity.WorldCupItem;
import com.example.springTest.repository.CommentRepo;
import com.example.springTest.repository.WorldCupItemRepo;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@Getter
@RequiredArgsConstructor
public class CommentsService {

    private final CommentRepo commentRepo;
    private final WorldCupItemRepo itemRepo;
    public Comment save(Comment comment, long itemId){
        comment = commentRepo.save(comment);
        WorldCupItem item = itemRepo.findById(itemId).get();
        item.addComment(comment);
        itemRepo.save(item);
        return comment;
    }

    public boolean delete(long commentId,long memberId){
        Comment comment = commentRepo.findById(commentId).get();
        if(comment.getMember().getId() == memberId) {
            commentRepo.delete(comment);
            return true;
        }
        return false;
    }

}
