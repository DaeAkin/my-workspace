package dev.donghyeon.dev.jpacascadetest.comment;

import dev.donghyeon.dev.jpacascadetest.article.Article;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Comment {
    @Id @GeneratedValue(strategy = IDENTITY)
    private Long id;
    
    private String content;
    
    @ManyToOne
    private Article article;

    private Comment(String content) {
        this.content = content;
    }
    
    public static Comment create(String content) {
        return new Comment(content);
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", content='" + content + '\'' +
                '}';
    }
}
