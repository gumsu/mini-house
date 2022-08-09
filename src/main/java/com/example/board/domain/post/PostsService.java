package com.example.board.domain.post;

import com.example.board.request.PostsSaveRequest;
import com.example.board.response.PostsResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostsService {

    private final PostsRepository postsRepository;

    // 게시글 등록
    public Long register(PostsSaveRequest request) {
        return postsRepository.save(request.toEntity()).getId();
    }

    // 게시글 전체 조회
    public List<PostsResponse> listAll() {
        List<Posts> postsList = postsRepository.findAll();

//        List<PostsResponse> responseList = new ArrayList<>();
//        for (Posts posts : postsList) {
//            responseList.add(new PostsResponse(posts));
//        }
//        return responseList;
        return postsList.stream().map(PostsResponse::new).collect(Collectors.toList());
    }
}
