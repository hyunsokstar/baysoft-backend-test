package org.zerock.apiserver.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "file")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class File {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "file_id")
    private Long fileId;  // 파일 ID (자동 생성)

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "comment_id")
    private Comment comment;

    @Column(name = "file_path", nullable = false)
    private String filePath;  // 파일 경로

    @Column(name = "file_name", nullable = false)
    private String fileName;  // 파일 이름

    @Column(name = "file_size", nullable = false)
    private Long fileSize;  // 파일 크기

    @Column(name = "file_type", nullable = false)
    private String fileType;  // 파일 타입

    @Column(name = "reg_dt", nullable = false)
    private LocalDateTime regDt;  // 등록일시

    @PrePersist
    public void prePersist() {
        this.regDt = LocalDateTime.now();
    }
}