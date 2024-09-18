package org.zerock.apiserver.dto.post;// 파일 경로: src/main/java/org/zerock/apiserver/dto/board/BoardOperationResult.java


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PostOperationResult {
    private int updatedCount;
    private int createdCount;
}
