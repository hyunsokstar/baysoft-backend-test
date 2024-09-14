package org.zerock.apiserver.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QBoard is a Querydsl query type for Board
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBoard extends EntityPathBase<Board> {

    private static final long serialVersionUID = 772013729L;

    public static final QBoard board = new QBoard("board");

    public final BooleanPath adminOnlyWrite = createBoolean("adminOnlyWrite");

    public final BooleanPath allowAttachments = createBoolean("allowAttachments");

    public final BooleanPath allowComments = createBoolean("allowComments");

    public final BooleanPath allowOnlyAdminOrAuthorComments = createBoolean("allowOnlyAdminOrAuthorComments");

    public final NumberPath<Long> boardId = createNumber("boardId", Long.class);

    public final NumberPath<Integer> commentLevel = createNumber("commentLevel", Integer.class);

    public final StringPath description = createString("description");

    public final BooleanPath isActive = createBoolean("isActive");

    public final BooleanPath isPrivate = createBoolean("isPrivate");

    public final StringPath name = createString("name");

    public final DateTimePath<java.time.LocalDateTime> regDt = createDateTime("regDt", java.time.LocalDateTime.class);

    public final DateTimePath<java.time.LocalDateTime> uptDt = createDateTime("uptDt", java.time.LocalDateTime.class);

    public QBoard(String variable) {
        super(Board.class, forVariable(variable));
    }

    public QBoard(Path<? extends Board> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBoard(PathMetadata metadata) {
        super(Board.class, metadata);
    }

}

