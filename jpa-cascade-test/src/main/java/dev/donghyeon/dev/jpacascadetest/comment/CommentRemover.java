package dev.donghyeon.dev.jpacascadetest.comment;

import dev.donghyeon.dev.jpacascadetest.article.Article;
import dev.donghyeon.dev.jpacascadetest.article.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentRemover {

    private final CommentRepository commentRepository;
    private final ArticleRepository articleRepository; 

    @Transactional
    public void removeComment(Long articleId) {
        Article article = articleRepository.findById(articleId).get();

        for (Comment comment : article.getComments()) {
            commentRepository.delete(comment);
        }

    }

}
