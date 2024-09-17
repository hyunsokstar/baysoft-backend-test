package org.zerock.apiserver.dto.category;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class ResponseDtoForOptionsWithIdAndName<T> {
    private List<T> data;  // 실제 데이터 리스트
}
