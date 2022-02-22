package dev.donghyeon.dev.jpacascadetest.article;

import dev.donghyeon.dev.jpacascadetest.comment.Comment;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ArticleCreator {
    
    private final ArticleRepository articleRepository;
    
    @Transactional
    public void createArticle(ArticleCreateRequest request) {
        Comment comment = Comment.create("1");
        Article article = Article.create(request.getTitle(), List.of(comment));
        articleRepository.save(article);
        log.info("article created : {}",article);
    }
}
