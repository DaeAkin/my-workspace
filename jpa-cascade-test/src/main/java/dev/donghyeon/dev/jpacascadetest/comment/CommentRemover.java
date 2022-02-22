package dev.donghyeon.dev.jpacascadetest.comment;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class CommentRemover {
    
    private final CommentRepository commentRepository;
    
    @Transactional
    public void removeComment(Long commentId) {
        commentRepository.deleteById(commentId);   
    }
    
}
