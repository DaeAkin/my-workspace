package dev.donghyeon.dev.jpacascadetest.article;

import dev.donghyeon.dev.jpacascadetest.comment.Comment;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Article {
    
    @Id @GeneratedValue(strategy = IDENTITY)
    private Long id;
    
    private String title;
    
    @OneToMany(mappedBy = "article",cascade = CascadeType.ALL)
    List<Comment> comments = new ArrayList<>();

    private Article(String title, List<Comment> comments) {
        this.title = title;
        this.comments = comments;
    }

    public static Article create(String title)  {
        return new Article(title, Collections.emptyList());
    }

    public static Article create(String title, List<Comment> comments)  {
        return new Article(title, comments);
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", comments=" + comments +
                '}';
    }
}
