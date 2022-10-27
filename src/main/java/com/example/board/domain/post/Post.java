package com.example.board.domain.post;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Table(name = "posts")
@Getter
@NoArgsConstructor
@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private Long views;
    private Integer likes;

    @Builder
    public Post(String title, String content, LocalDateTime createdAt, LocalDateTime modifiedAt, Long views, Integer likes) {
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
        this.views = views;
        this.likes = likes;
    }

    // 기존 데이터로 build
    public PostEditor.PostEditorBuilder toEditor() {
        return PostEditor.builder()
                .title(title)
                .content(content);
    }

    // 새로 변경된 데이터로 수정
    public void toEdit(PostEditor postEditor) {
        title = postEditor.getTitle();
        content = postEditor.getContent();
        modifiedAt = LocalDateTime.now();
    }
}
