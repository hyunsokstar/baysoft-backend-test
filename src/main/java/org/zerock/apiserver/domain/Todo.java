package org.zerock.apiserver.domain;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@ToString
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tbl_todo")
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tno;

    @Column(length=500, nullable = false)
    private String title;

    private String content;

    private boolean complete;

    private LocalDate dueDate;

    // 제목 변경 메서드를 클래스 내부에 추가
    public void changeTitle(String title) {
        this.title = title;
    }

    // 내용 변경 메서드
    public void changeContent(String content) {
        this.content = content;
    }

    // 완료 상태 변경 메서드
    public void changeComplete(boolean complete) {
        this.complete = complete;
    }

    // 마감일 변경 메서드
    public void changeDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }
}