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
        // 실제 요소
        this.dtoList = dtoList;
        // 요청 정보
        this.pageRequestDTO = pageRequestDTO;
        // 총 개수
        this.totalCount = (int) total;
        // 현재 페이지 설정
        this.current = pageRequestDTO.getPage();

        // 전체 페이지 수 계산
        int totalPages = (int) (Math.ceil(totalCount / (double) pageRequestDTO.getSize()));
        this.totalPage = totalPages;

        // 블록 크기를 pageRequestDTO.getSize()로 설정
        int blockSize = pageRequestDTO.getSize();

        // 총 페이지수 추정
        int end = (int) (Math.ceil(pageRequestDTO.getPage() / (double) blockSize)) * blockSize;

        // 현재 블록의 첫 페이지 번호
        int start = end - (blockSize - 1);

        // 마지막 페이지 번호 계산 (총 페이지수와 end를 비교하여 작은게 마지막 페이지)
        end = Math.min(end, totalPages);

        // 이전 페이지로 넘어 갈수 있는지 여부
        this.prev = start > 1;

        // 다음 페이지로 넘어갈 수 있는지 여부
        this.next = totalCount > end * pageRequestDTO.getSize();

        // 시작 페이지와 끝 페이지 번호를 이용해 페이지 번호 리스트 만들기
        this.pageNumList = IntStream.rangeClosed(start, end).boxed().collect(Collectors.toList());

        // 이전 페이지 설정 (이전 블록이 있을 경우)
        this.prevPage = prev ? start - 1 : 0;

        // 다음 블록이 있을 경우 end + 1, 마지막 블록이면 마지막 페이지 (totalPages)
        this.nextPage = next ? end + 1 : (end < totalPages ? totalPages : 0);
    }

}
