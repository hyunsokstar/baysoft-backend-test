package org.zerock.apiserver.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.zerock.apiserver.domain.Todo;
import org.zerock.apiserver.dto.PageResponseDTO;
import org.zerock.apiserver.dto.SearchRequestDTO;
import org.zerock.apiserver.dto.TodoDTO;
import org.zerock.apiserver.repository.todo.TodoRepository;

import java.util.List;
import java.util.Optional;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional
public class TodoServiceImpl implements TodoService {
    private final TodoRepository todoRepository;

    @Override
    public PageResponseDTO<TodoDTO> getList(SearchRequestDTO searchRequestDTO) {
        PageResponseDTO<TodoDTO> result = todoRepository.search(searchRequestDTO);
        List<TodoDTO> dtoList = result.getDtoList();

        return PageResponseDTO.<TodoDTO>withAll()
                .dtoList(dtoList)
                .pageRequestDTO(searchRequestDTO)
                .total(result.getTotalCount())
                .build();
    }

    @Override
    public TodoDTO get(long tno) {
        Optional<Todo> result = todoRepository.findById(tno);
        Todo todo = result.orElseThrow();
        return entityToDto(todo);
    }

    @Override
    public Long register(TodoDTO dto) {
        try {
            System.out.println("없어서 저장2");
            System.out.println("없어서 저장 data : " + dto);
            Todo todo = dtoToEntity(dto);
            Todo result = todoRepository.save(todo);
            log.info("result : ", result.getTno());
            return result.getTno();
        } catch (Exception e) {
            log.error("Error saving Todo: ", e);
            throw e;
        }
    }

    @Override
    public void modify(TodoDTO dto) {
        Optional<Todo> result = todoRepository.findById(dto.getTno());
        Todo todo = result.orElseThrow();

        todo.changeTitle(dto.getTitle());
        todo.changeContent(dto.getContent());
        todo.changeComplete(dto.isComplete());
        todo.changeDueDate(dto.getDueDate());

        todoRepository.save(todo);
    }

    @Override
    public void remove(Long tno) {
        todoRepository.deleteById(tno);
    }

    @Override
    public void removeTodos(List<Long> tnoList) {
        for (Long tno : tnoList) {
            if (todoRepository.existsById(tno)) {
                todoRepository.deleteById(tno);
            } else {
                log.warn("삭제하려는 Todo가 존재하지 않습니다: TNO = " + tno);
            }
        }
    }

    @Override
    public void registerTodos(List<TodoDTO> todoDTOList) {
        for (TodoDTO dto : todoDTOList) {
            if (dto.getTno() != null && todoRepository.existsById(dto.getTno())) {
                modify(dto);  // 이미 존재하는 경우 업데이트
            } else {
                System.out.println("없어서 저장 1");
                register(dto);  // 없는 경우 새로 저장
            }
        }
    }

    @Override
    public PageResponseDTO<TodoDTO> search(SearchRequestDTO searchRequestDTO) {

        // getCurrentPage와 getPageSize를 정의합시다.
        PageResponseDTO<TodoDTO> result = todoRepository.search(searchRequestDTO);
        List<TodoDTO> dtoList = result.getDtoList();

        return PageResponseDTO.<TodoDTO>withAll()
                .dtoList(dtoList)
                .pageRequestDTO(searchRequestDTO)
                .total(result.getTotalCount())
                .build();
    }

}
