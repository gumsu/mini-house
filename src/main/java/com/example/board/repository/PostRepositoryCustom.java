package com.example.board.repository;

import com.example.board.domain.post.Post;
import com.example.board.request.PostSearchRequest;
import com.example.board.response.PostResponse;

import java.util.List;

public interface PostRepositoryCustom {

    List<Post> getList(PostSearchRequest postSearchRequest);
}
