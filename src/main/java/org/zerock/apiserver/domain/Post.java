package org.zerock.apiserver.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "post")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long postId;  // 게시글 ID (자동 생성)

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id", nullable = false)
    private Board board;  // 게시판 참조 (N:1 관계)

    @Column(name = "title", nullable = false, length = 255)
    private String title;  // 게시글 제목

    @Column(name = "content", columnDefinition = "TEXT")
    private String content;  // 게시글 내용

    @Column(name = "user_id", nullable = false)
    private Long userId;  // 작성자 ID

    @Column(name = "view_count", nullable = false)
    private Integer viewCount;  // 조회수

    @Column(name = "reg_dt", nullable = false)
    private LocalDate regDt;  // 등록일시

    @Column(name = "upt_dt")
    private LocalDate uptDt;  // 수정일시

    @OneToMany(mappedBy = "post")
    private List<Comment> comments = new ArrayList<>();

    @PrePersist
    public void prePersist() {
        this.regDt = LocalDate.now();
        this.viewCount = 0;
    }

    @PreUpdate
    public void preUpdate() {
        this.uptDt = LocalDate.now();
    }
}