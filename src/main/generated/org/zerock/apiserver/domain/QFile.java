package org.zerock.apiserver.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QFile is a Querydsl query type for File
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QFile extends EntityPathBase<File> {

    private static final long serialVersionUID = 1687585377L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QFile file = new QFile("file");

    public final QComment comment;

    public final NumberPath<Long> fileId = createNumber("fileId", Long.class);

    public final StringPath fileName = createString("fileName");

    public final StringPath filePath = createString("filePath");

    public final NumberPath<Long> fileSize = createNumber("fileSize", Long.class);

    public final StringPath fileType = createString("fileType");

    public final QPost post;

    public final DateTimePath<java.time.LocalDateTime> regDt = createDateTime("regDt", java.time.LocalDateTime.class);

    public QFile(String variable) {
        this(File.class, forVariable(variable), INITS);
    }

    public QFile(Path<? extends File> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QFile(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QFile(PathMetadata metadata, PathInits inits) {
        this(File.class, metadata, inits);
    }

    public QFile(Class<? extends File> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.comment = inits.isInitialized("comment") ? new QComment(forProperty("comment"), inits.get("comment")) : null;
        this.post = inits.isInitialized("post") ? new QPost(forProperty("post"), inits.get("post")) : null;
    }

}

