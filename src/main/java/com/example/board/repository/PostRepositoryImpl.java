package com.example.board.repository;

import com.example.board.domain.post.Post;
import com.example.board.domain.post.QPost;
import com.example.board.response.PostResponse;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class PostRepositoryImpl implements PostRepositoryCustom{

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Post> getList(int page) {
        return jpaQueryFactory.selectFrom(QPost.post)
                .limit(5)
                .offset((long) (page - 1) * 10)
                .orderBy(QPost.post.id.desc())
                .fetch();
    }
}
