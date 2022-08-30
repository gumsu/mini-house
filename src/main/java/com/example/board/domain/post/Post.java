package com.example.board.domain.post;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Table(name = "Posts")
@Getter
@NoArgsConstructor
@Entity
public class Post {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String content;
    private String writer;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    @Builder
    public Post(String title, String content, String writer, LocalDateTime createdDate, LocalDateTime modifiedDate) {
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
    }

    public PostEditor.PostEditorBuilder toEditor() {
        return PostEditor.builder()
                .title(title)
                .content(content);
    }

    public void toEdit(PostEditor postEditor) {
        title = postEditor.getTitle();
        content = postEditor.getContent();
        modifiedDate = LocalDateTime.now();
    }
}
