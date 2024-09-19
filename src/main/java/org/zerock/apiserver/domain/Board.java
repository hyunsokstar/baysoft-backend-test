package org.zerock.apiserver.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "board")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    private Long boardId;  // 게시판 ID (자동 생성)

    @Column(name = "name", nullable = false, length = 255)
    private String name;  // 게시판 이름

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;  // 게시판 설명

    @Column(name = "allow_comments", nullable = false)
    private Boolean allowComments;  // 댓글 허용 여부

    @Column(name = "comment_level")
    private Integer commentLevel;  // 댓글 레벨 (Lv1, Lv2 등)

    @Column(name = "allow_attachments", nullable = false)
    private Boolean allowAttachments;  // 첨부파일 허용 여부

    @Column(name = "is_active", nullable = false)
    private Boolean isActive;  // 게시판 활성화 여부

    @Column(name = "is_private", nullable = false)
    private Boolean isPrivate;  // 비공개 여부

    @Column(name = "admin_only_write", nullable = false)
    private Boolean adminOnlyWrite;  // 관리자 전용 글쓰기 여부

    @Column(name = "allow_only_admin_or_author_comments", nullable = false)
    private Boolean allowOnlyAdminOrAuthorComments;  // 관리자 또는 작성자만 댓글 허용 여부

    @Column(name = "reg_dt", nullable = false)
    private LocalDateTime regDt;  // 등록일시

    @Column(name = "upt_dt")
    private LocalDateTime uptDt;  // 수정일시

    // Category와의 다대일(Many-to-One) 관계 설정
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "category_id", nullable = true)
    private Category category;

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Post> posts = new ArrayList<>();

}
