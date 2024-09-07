package org.zerock.apiserver.dto;


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

    public PageResponseDTO(List<E> dtoList, PageRequestDTO pageRequestDTO, long total) {
        this.dtoList = dtoList;
        this.pageRequestDTO = pageRequestDTO;
        this.totalCount = (int)total;

        // 끝 페이지 end
        int end = (int) (Math.ceil(pageRequestDTO.getPage() / 10.0)) * 10;
        int start = end - 9;

        int last = (int)(Math.ceil(totalCount/(double)pageRequestDTO.getSize()));

        end = end > last ? last : end;

        this.prev = start > 1;
        this.next = (int)totalCount > end * pageRequestDTO.getSize();

        // 시작 페이지와 끝 페이지 번호를 이용해 페이지 번호 리스트 만들기
        this.pageNumList = IntStream.rangeClosed(start, end).boxed().collect(Collectors.toList());

        this.prevPage = prev ? start - 1 : 0;
        this.nextPage = next ? end + 1 : 0;
    }


}
