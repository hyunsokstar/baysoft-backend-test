package org.zerock.apiserver.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

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
    private Long boardId;

    @Column(name = "name", nullable = false, length = 255)
    private String name;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "allow_comments", nullable = false)
    private Boolean allowComments;

    @Column(name = "comment_level")
    private Integer commentLevel;

    @Column(name = "allow_attachments", nullable = false)
    private Boolean allowAttachments;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive;

    @Column(name = "is_private", nullable = false)
    private Boolean isPrivate;

    @Column(name = "admin_only_write", nullable = false)
    private Boolean adminOnlyWrite;

    @Column(name = "allow_only_admin_or_author_comments", nullable = false)
    private Boolean allowOnlyAdminOrAuthorComments;

    @Column(name = "reg_dt", nullable = false)
    private LocalDateTime regDt;

    @Column(name = "upt_dt")
    private LocalDateTime uptDt;
}