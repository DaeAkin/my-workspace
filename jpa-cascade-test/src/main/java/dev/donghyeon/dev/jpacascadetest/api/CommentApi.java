package dev.donghyeon.dev.jpacascadetest.api;

import dev.donghyeon.dev.jpacascadetest.comment.CommentRemover;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/comment")
public class CommentApi {
    
    private final CommentRemover commentRemover;
    
    @DeleteMapping("/{commentId}")
    public void deleteComment(@PathVariable Long commentId) {
        commentRemover.removeComment(commentId);
    }
}
