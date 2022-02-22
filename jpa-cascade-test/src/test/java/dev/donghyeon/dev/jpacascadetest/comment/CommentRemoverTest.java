package dev.donghyeon.dev.jpacascadetest.comment;

import dev.donghyeon.dev.jpacascadetest.article.Article;
import dev.donghyeon.dev.jpacascadetest.article.ArticleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import javax.transaction.Transactional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
class CommentRemoverTest {

    @PersistenceContext
    EntityManager entityManager;
    
    @Autowired
    private ArticleRepository articleRepository;
    
    @Autowired
    private CommentRemover commentRemover;        

    Comment comment1;
    Comment comment2;
    
    Article article;
    
    @BeforeEach
    void setUp() {
        createArticle();
    }
    @Transactional
    void createArticle() {
        comment1 = Comment.create("1");
        comment2 = Comment.create("2");

        article = Article.create("게시글", List.of(comment1, comment2));
        article = articleRepository.save(article);
        entityManager.flush();
        entityManager.clear();
    }
    @Test
    @Transactional
    void removeComment() {
        commentRemover.removeComment(comment1.getId());
        
        entityManager.flush();
    }
}
