//package org.zerock.apiserver.dto;
//
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import lombok.experimental.SuperBuilder;
//
//@SuperBuilder
//@NoArgsConstructor
//@AllArgsConstructor
//@Data
//public class SearchRequestDTO extends PageRequestDTO {
//
//    private String searchKeyword; // 검색어
//    private String searchType;    // 검색 타입 (예: 제목, 내용 등)
//}
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

    // 모든 필드를 포함한 생성자
    public SearchRequestDTO(int page, int size, String searchKeyword, String searchType) {
        super(page, size);
        this.searchKeyword = searchKeyword;
        this.searchType = searchType;
    }
}