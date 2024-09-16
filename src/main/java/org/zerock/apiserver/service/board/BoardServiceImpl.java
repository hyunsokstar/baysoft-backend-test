package org.zerock.apiserver.service.board;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.zerock.apiserver.domain.Board;
import org.zerock.apiserver.domain.Category;
import org.zerock.apiserver.dto.PageResponseDtoMini;
import org.zerock.apiserver.dto.SearchRequestDTO;
import org.zerock.apiserver.dto.board.BoardDto;
import org.zerock.apiserver.dto.board.CreateBoardDto;
import org.zerock.apiserver.dto.mapper.BoardMapper;
import org.zerock.apiserver.repository.board.BoardRepository;
import org.zerock.apiserver.repository.category.CategoryRepository;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;
    private final CategoryRepository categoryRepository; // 카테고리 레포지토리 추가

    @Override
    public PageResponseDtoMini<BoardDto> search(SearchRequestDTO searchRequestDTO) {
        return boardRepository.search(searchRequestDTO);
    }

    @Override
    public BoardDto createBoard(CreateBoardDto createBoardDto) {
        // 카테고리 설정 처리
        Category category = null;
        if (createBoardDto.getCategoryId() != null) {
            category = categoryRepository.findById(createBoardDto.getCategoryId())
                    .orElseThrow(() -> new IllegalArgumentException("Invalid category ID"));
        }

        // Board 엔티티 생성
        Board board = Board.builder()
                .name(createBoardDto.getName())
                .description(createBoardDto.getDescription())
                .allowComments(createBoardDto.getAllowComments())
                .commentLevel(createBoardDto.getCommentLevel())
                .allowAttachments(createBoardDto.getAllowAttachments())
                .isActive(createBoardDto.getIsActive())
                .isPrivate(createBoardDto.getIsPrivate())
                .adminOnlyWrite(createBoardDto.getAdminOnlyWrite())
                .allowOnlyAdminOrAuthorComments(createBoardDto.getAllowOnlyAdminOrAuthorComments())
                .regDt(LocalDateTime.now())
                .category(category) // 카테고리 설정
                .build();

        // 저장 후 반환
        Board savedBoard = boardRepository.save(board);
        return BoardMapper.entityToDto(savedBoard);
    }
}
