package org.zerock.apiserver.dto;

import lombok.*;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TodoDTO {
    private Long tno;
    private String title;
    private String content;
    private boolean complete;
    private LocalDate dueDate;

    public TodoDTO(Long tno, String title, String content) {
        this.tno = tno;
        this.title = title;
        this.content = content;
    }

    // Getters and Setters
}
