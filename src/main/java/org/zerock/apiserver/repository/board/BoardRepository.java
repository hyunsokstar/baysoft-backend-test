package org.zerock.apiserver.repository.board;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zerock.apiserver.domain.Board;
import org.zerock.apiserver.domain.Category;

public interface BoardRepository extends JpaRepository<Board, Long> , BoardSearch{
}
