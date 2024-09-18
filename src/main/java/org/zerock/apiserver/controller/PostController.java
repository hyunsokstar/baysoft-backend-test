package org.zerock.apiserver.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.zerock.apiserver.dto.PageResponseDtoMini;
import org.zerock.apiserver.dto.PostingSearchRequestDTO;
import org.zerock.apiserver.dto.post.PostDto;
import org.zerock.apiserver.dto.post.CreatePostDto;
import org.zerock.apiserver.dto.post.PostOperationResult;
import org.zerock.apiserver.service.post.PostService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
@Log4j2
public class PostController {

    private final PostService postService;

    @GetMapping("/search")
    public ResponseEntity<PageResponseDtoMini<PostDto>> searchPosts(PostingSearchRequestDTO postingSearchRequestDTO) {
        PageResponseDtoMini<PostDto> result = postService.search(postingSearchRequestDTO);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/save-or-update")
    public ResponseEntity<PostOperationResult> saveOrUpdatePosts(@RequestBody List<CreatePostDto> postDtoList) {
        PostOperationResult result = postService.saveOrUpdatePosts(postDtoList);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/multiple")
    public ResponseEntity<Map<String, Object>> removePosts(@RequestBody Map<String, List<Long>> request) {
        List<Long> postIds = request.get("postIds");
        log.info("Batch remove posts request: {}", postIds);
        int deletedCount = postService.removePosts(postIds);
        return ResponseEntity.ok(Map.of(
                "RESULT", "SUCCESS",
                "DELETED_COUNT", deletedCount,
                "MESSAGE", deletedCount + "개의 게시글이 삭제되었습니다."
        ));
    }
}