package org.zerock.apiserver.repository;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import lombok.extern.log4j.Log4j2;
import org.springframework.test.annotation.Rollback;
import org.zerock.apiserver.domain.Board;
import org.zerock.apiserver.repository.board.BoardRepository;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
@Log4j2
public class BoardRepositoryTest {

    @Autowired
    BoardRepository boardRepository;

    @Test
    @Rollback(true)
    public void testCreateBoard() {
        Board board = createSampleBoard();
        Board savedBoard = boardRepository.save(board);

        assertThat(savedBoard.getBoardId()).isNotNull();
        assertThat(savedBoard.getName()).isEqualTo("Test Board");

        log.info("Created Board: " + savedBoard);
    }

    @Test
    @Rollback(true)
    public void testReadBoard() {
        Board board = boardRepository.save(createSampleBoard());

        Board foundBoard = boardRepository.findById(board.getBoardId()).orElse(null);
        assertThat(foundBoard).isNotNull();
        assertThat(foundBoard.getName()).isEqualTo("Test Board");

        log.info("Read Board: " + foundBoard);
    }

    @Test
    @Rollback(true)
    public void testUpdateBoard() {
        Board board = boardRepository.save(createSampleBoard());

        board.setName("Updated Board Name");
        board.setUptDt(LocalDateTime.now());
        Board updatedBoard = boardRepository.save(board);

        assertThat(updatedBoard.getName()).isEqualTo("Updated Board Name");
        assertThat(updatedBoard.getUptDt()).isNotNull();

        log.info("Updated Board: " + updatedBoard);
    }

    @Test
    @Rollback(true)
    public void testDeleteBoard() {
        Board board = boardRepository.save(createSampleBoard());

        boardRepository.delete(board);

        Board deletedBoard = boardRepository.findById(board.getBoardId()).orElse(null);
        assertThat(deletedBoard).isNull();

        log.info("Deleted Board ID: " + board.getBoardId());
    }

    @Test
    @Rollback(true)
    public void testListBoards() {
        for (int i = 0; i < 5; i++) {
            boardRepository.save(createSampleBoard("List Test Board " + i));
        }

        List<Board> boards = boardRepository.findAll();
        assertThat(boards).isNotEmpty();
        assertThat(boards.size()).isGreaterThanOrEqualTo(5);

        log.info("Total boards: " + boards.size());
        boards.forEach(board -> log.info("Board: " + board));
    }

    private Board createSampleBoard() {
        return createSampleBoard("Test Board");
    }

    private Board createSampleBoard(String name) {
        return Board.builder()
                .name(name)
                .description("This is a test board")
                .allowComments(true)
                .commentLevel(0)
                .allowAttachments(true)
                .isActive(true)
                .isPrivate(false)
                .adminOnlyWrite(false)
                .allowOnlyAdminOrAuthorComments(false)
                .regDt(LocalDateTime.now())
                .build();
    }
}