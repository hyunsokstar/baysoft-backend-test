package org.zerock.apiserver.repository.comment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zerock.apiserver.domain.Comment;
import org.zerock.apiserver.domain.Post;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByPost(Post post);
    List<Comment> findByPostOrderByRegDtDesc(Post post);
    List<Comment> findByUserIdOrderByRegDtDesc(Long userId);
    long countByPost(Post post);
}