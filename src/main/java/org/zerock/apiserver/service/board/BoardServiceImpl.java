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
import org.zerock.apiserver.dto.board.BoardDtoMini;
import org.zerock.apiserver.dto.board.BoardOperationResult;
import org.zerock.apiserver.dto.board.CreateBoardDto;
import org.zerock.apiserver.repository.board.BoardRepository;
import org.zerock.apiserver.repository.category.CategoryRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Log4j2
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;
    private final CategoryRepository categoryRepository;

    // Board 삭제 로직 추가 with id 배열
    
    
    @Override
    public PageResponseDtoMini<BoardDto> search(SearchRequestDTO searchRequestDTO) {
        return boardRepository.search(searchRequestDTO);
    }

    @Override
    @Transactional
    public BoardOperationResult saveOrUpdateBoards(List<CreateBoardDto> boardDtoList) {
        int updatedCount = 0;
        int createdCount = 0;

            log.info("boardDtoList to 저장 or 수정: "+ boardDtoList);

        for (CreateBoardDto dto : boardDtoList) {
            if (dto.getBoardId() == null) {
                createBoard(dto);
                createdCount++;
            } else {
                Board existingBoard = boardRepository.findById(dto.getBoardId())
                        .orElseThrow(() -> new NoSuchElementException("Board with ID " + dto.getBoardId() + " not found"));
                updateBoard(existingBoard, dto);
                updatedCount++;
            }
        }

        return new BoardOperationResult(updatedCount, createdCount);
    }

    private void createBoard(CreateBoardDto dto) {
        Board board = new Board();
        updateBoardFromDto(board, dto);
        board.setRegDt(LocalDateTime.now());
        boardRepository.save(board);
    }

    private void updateBoard(Board board, CreateBoardDto dto) {
        updateBoardFromDto(board, dto);
        board.setUptDt(LocalDateTime.now());
        boardRepository.save(board);
    }

    private void updateBoardFromDto(Board board, CreateBoardDto dto) {
        Optional.ofNullable(dto.getName()).ifPresent(board::setName);
        Optional.ofNullable(dto.getDescription()).ifPresent(board::setDescription);
        Optional.ofNullable(dto.getAllowComments()).ifPresent(board::setAllowComments);
        Optional.ofNullable(dto.getCommentLevel()).ifPresent(board::setCommentLevel);
        Optional.ofNullable(dto.getAllowAttachments()).ifPresent(board::setAllowAttachments);
        Optional.ofNullable(dto.getIsActive()).ifPresent(board::setIsActive);
        Optional.ofNullable(dto.getIsPrivate()).ifPresent(board::setIsPrivate);
        Optional.ofNullable(dto.getAdminOnlyWrite()).ifPresent(board::setAdminOnlyWrite);
        Optional.ofNullable(dto.getAllowOnlyAdminOrAuthorComments()).ifPresent(board::setAllowOnlyAdminOrAuthorComments);

        Optional.ofNullable(dto.getCategoryId()).ifPresentOrElse(
                categoryId -> {
                    Category category = categoryRepository.findById(categoryId)
                            .orElseThrow(() -> new NoSuchElementException("Category with ID " + categoryId + " not found"));
                    board.setCategory(category);
                },
                () -> board.setCategory(null)
        );
    }

    @Override
    @Transactional
    public int removeBoards(List<Long> boardIds) {
        int deletedCount = 0;
        for (Long boardId : boardIds) {
            if (boardRepository.existsById(boardId)) {
                boardRepository.deleteById(boardId);
                deletedCount++;
            } else {
                log.warn("삭제하려는 게시판이 존재하지 않습니다: BoardId = " + boardId);
            }
        }
        return deletedCount;
    }

    @Override
    public List<BoardDtoMini> getBoardIdAndNames() {
        return boardRepository.findAll().stream()
                .map(board -> new BoardDtoMini(board.getBoardId(), board.getName()))
                .collect(Collectors.toList());
    }

}