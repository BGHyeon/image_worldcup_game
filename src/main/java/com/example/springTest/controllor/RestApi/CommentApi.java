package com.example.springTest.controllor.RestApi;

import com.example.springTest.entity.Comment;
import com.example.springTest.sequrity.MemberDetail;
import com.example.springTest.service.CommentsService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;

@RestController
@RequiredArgsConstructor
public class CommentApi {
    private final CommentsService commentsService;
    @PostMapping("/secure/comment/{itemId}")
    @Transactional
    public Comment saveComment(@PathVariable Long itemId, Comment comment, @AuthenticationPrincipal MemberDetail detail){
        comment.setMember(detail.getMember());
        return commentsService.save(comment,itemId.longValue());
    }
    @DeleteMapping("/secure/comment/{commentId}")
    @Transactional
    public String deleteComment(@PathVariable Long commentId,@AuthenticationPrincipal MemberDetail detail){
        return commentsService.delete(commentId.longValue(),detail.getMember().getId())?"save":"fail";
    }
}
