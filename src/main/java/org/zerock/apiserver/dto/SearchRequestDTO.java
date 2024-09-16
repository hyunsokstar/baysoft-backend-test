package org.zerock.apiserver.dto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@ToString(callSuper = true)
@SuperBuilder
public class SearchRequestDTO extends PageRequestDTO {

    private String searchKeyword; // 검색어
    private String searchType;    // 검색 타입 (예: 제목, 내용 등)

    // 기본 생성자
    public SearchRequestDTO() {
        super();
    }

    // 모든 필드를 포함한 생성자는 제거함
}
