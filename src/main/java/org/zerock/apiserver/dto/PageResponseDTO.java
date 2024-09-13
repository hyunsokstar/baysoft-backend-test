package org.zerock.apiserver.dto;


import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Data
public class PageResponseDTO<E> {

    private List<E> dtoList;

    private List<Integer> pageNumList;

    private PageRequestDTO pageRequestDTO;

    private boolean prev, next;
    private int totalCount, prevPage, nextPage, totalPage, current;

    @Builder(builderMethodName = "withAll")
    public PageResponseDTO(List<E> dtoList, PageRequestDTO pageRequestDTO, long total) {
        this.dtoList = dtoList;
        this.pageRequestDTO = pageRequestDTO;
        this.totalCount = (int)total;

        // 현재 페이를 기준으로 끝 페이지 계산
        int end = (int) (Math.ceil(pageRequestDTO.getPage() / 10.0)) * 10;

        // end 를 기준으로 첫페이지 계산
        int start = end - 9;

        // 마지막 페이지
        // 총개수를 기준으로 계산
        int last = (int)(Math.ceil(totalCount/(double)pageRequestDTO.getSize()));

        end = Math.min(end, last);
        
        // 이전 블록으로 넘어 갈 수 있는지 여부
        this.prev = start > 1;

        // 다음 블록으로 넘어 갈 수 있는지 여부
        this.next = (int)totalCount > end * pageRequestDTO.getSize();

        // 시작 페이지와 끝 페이지 번호를 이용해 페이지 번호 리스트 만들기
        this.pageNumList = IntStream.rangeClosed(start, end).boxed().collect(Collectors.toList());

        this.prevPage = prev ? start - 1 : 0;
        this.nextPage = next ? end + 1 : 0;
    }


}
