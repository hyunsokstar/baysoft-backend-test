package org.zerock.apiserver.repository.file;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zerock.apiserver.domain.File;
import org.zerock.apiserver.domain.Post;
import org.zerock.apiserver.domain.Comment;

import java.util.List;

public interface FileRepository extends JpaRepository<File, Long> {
    List<File> findByPost(Post post);
    List<File> findByComment(Comment comment);
    List<File> findByFileType(String fileType);
    long countByPost(Post post);
    long countByComment(Comment comment);
}