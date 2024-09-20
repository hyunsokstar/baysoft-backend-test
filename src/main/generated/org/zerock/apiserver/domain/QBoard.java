package org.zerock.apiserver.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBoard is a Querydsl query type for Board
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBoard extends EntityPathBase<Board> {

    private static final long serialVersionUID = 772013729L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QBoard board = new QBoard("board");

    public final BooleanPath adminOnlyWrite = createBoolean("adminOnlyWrite");

    public final BooleanPath allowAttachments = createBoolean("allowAttachments");

    public final BooleanPath allowComments = createBoolean("allowComments");

    public final BooleanPath allowOnlyAdminOrAuthorComments = createBoolean("allowOnlyAdminOrAuthorComments");

    public final NumberPath<Long> boardId = createNumber("boardId", Long.class);

    public final QCategory category;

    public final NumberPath<Integer> commentLevel = createNumber("commentLevel", Integer.class);

    public final ListPath<Comment, QComment> comments = this.<Comment, QComment>createList("comments", Comment.class, QComment.class, PathInits.DIRECT2);

    public final StringPath description = createString("description");

    public final BooleanPath isActive = createBoolean("isActive");

    public final BooleanPath isPrivate = createBoolean("isPrivate");

    public final StringPath name = createString("name");

    public final ListPath<Post, QPost> posts = this.<Post, QPost>createList("posts", Post.class, QPost.class, PathInits.DIRECT2);

    public final DateTimePath<java.time.LocalDateTime> regDt = createDateTime("regDt", java.time.LocalDateTime.class);

    public final DateTimePath<java.time.LocalDateTime> uptDt = createDateTime("uptDt", java.time.LocalDateTime.class);

    public QBoard(String variable) {
        this(Board.class, forVariable(variable), INITS);
    }

    public QBoard(Path<? extends Board> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QBoard(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QBoard(PathMetadata metadata, PathInits inits) {
        this(Board.class, metadata, inits);
    }

    public QBoard(Class<? extends Board> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.category = inits.isInitialized("category") ? new QCategory(forProperty("category")) : null;
    }

}

