package org.zerock.apiserver.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@ToString(callSuper = true)
@SuperBuilder
public class PostingSearchRequestDTO extends PageRequestDTO {

    private String searchKeyword; // 검색어
    private String searchType;    // 검색 타입 (예: 제목, 내용 등)
    private Long boardId;         // 게시판 ID

    // 기본 생성자
    public PostingSearchRequestDTO() {
        super();
    }
}