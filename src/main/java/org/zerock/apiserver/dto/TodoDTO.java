package org.zerock.apiserver.dto;

import lombok.Getter;

@Getter
public class TodoDTO {
    private Long tno;
    private String title;
    private String content;

    public TodoDTO(Long tno, String title, String content) {
        this.tno = tno;
        this.title = title;
        this.content = content;
    }

    // Getters and Setters
}
