package org.zerock.apiserver.service.board;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.zerock.apiserver.domain.Board;
import org.zerock.apiserver.domain.Category;
import org.zerock.apiserver.dto.PageResponseDtoMini;
import org.zerock.apiserver.dto.SearchRequestDTO;
import org.zerock.apiserver.dto.board.BoardDto;
import org.zerock.apiserver.dto.board.BoardOperationResult;
import org.zerock.apiserver.dto.board.CreateBoardDto;
import org.zerock.apiserver.dto.mapper.BoardMapper;
import org.zerock.apiserver.repository.board.BoardRepository;
import org.zerock.apiserver.repository.category.CategoryRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Log4j2
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;
    private final CategoryRepository categoryRepository; // 카테고리 레포지토리 추가

    @Override
    public PageResponseDtoMini<BoardDto> search(SearchRequestDTO searchRequestDTO) {
        return boardRepository.search(searchRequestDTO);
    }

    @Override
    @Transactional
    public BoardOperationResult saveOrUpdateBoards(List<CreateBoardDto> boardDtoList) {
        int updatedCount = 0;
        int createdCount = 0;

        for (CreateBoardDto dto : boardDtoList) {
            Long boardId = dto.getBoardId();

            if (boardId == null || boardId < 0) {
                createBoard(dto);
                createdCount++;
            } else {
                Optional<Board> existingBoard = boardRepository.findById(boardId);
                if (existingBoard.isPresent()) {
                    updateBoard(existingBoard.get(), dto);
                    updatedCount++;
                } else {
                    log.warn("Board with ID {} does not exist.", boardId);
                }
            }
        }

        return new BoardOperationResult(updatedCount, createdCount);
    }

    private void createBoard(CreateBoardDto dto) {
        Board board = new Board();
        updateBoardFromDto(board, dto);
        board.setRegDt(LocalDateTime.now());  // 새로운 보드 생성 시 현재 시간으로 regDt 설정
        boardRepository.save(board);
    }

    private void updateBoard(Board board, CreateBoardDto dto) {
        updateBoardFromDto(board, dto);
        board.setRegDt(LocalDateTime.now());  // 보드 업데이트 시 현재 시간으로 modDt 설정
        boardRepository.save(board);
    }

    private void updateBoardFromDto(Board board, CreateBoardDto dto) {
        board.setName(dto.getName());
        board.setDescription(dto.getDescription());
        board.setAllowComments(dto.getAllowComments());
        board.setCommentLevel(dto.getCommentLevel());
        board.setAllowAttachments(dto.getAllowAttachments());
        board.setIsActive(dto.getIsActive());
        board.setIsPrivate(dto.getIsPrivate());
        board.setAdminOnlyWrite(dto.getAdminOnlyWrite());
        board.setAllowOnlyAdminOrAuthorComments(dto.getAllowOnlyAdminOrAuthorComments());
        board.setUptDt(LocalDateTime.now());  // 업데이트 날짜를 현재 시간으로 설정

        if (dto.getCategoryId() != null) {
            Category category = categoryRepository.findById(dto.getCategoryId())
                    .orElseThrow(() -> new RuntimeException("Category not found"));
            board.setCategory(category);
        } else {
            board.setCategory(null);
        }
    }
}
