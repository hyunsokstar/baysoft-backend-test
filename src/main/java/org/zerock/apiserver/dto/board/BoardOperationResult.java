// 파일 경로: src/main/java/org/zerock/apiserver/dto/board/BoardOperationResult.java

package org.zerock.apiserver.dto.board;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BoardOperationResult {
    private int updatedCount;
    private int createdCount;
}
