package dev.donghyeon.dev.jpacascadetest.api;

import dev.donghyeon.dev.jpacascadetest.article.ArticleCreateRequest;
import dev.donghyeon.dev.jpacascadetest.article.ArticleCreator;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/article")
public class ArticleApi {
    
    private final ArticleCreator articleCreator;
    
    @PostMapping
    public void createArticle(ArticleCreateRequest request) {
        articleCreator.createArticle(request);    
    }
}
