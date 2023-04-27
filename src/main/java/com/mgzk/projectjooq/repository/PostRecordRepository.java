package com.mgzk.projectjooq.repository;

import com.mgzk.projectjooq.domain.tables.Post;
import com.mgzk.projectjooq.domain.tables.records.PostRecord;
import java.util.Optional;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

@Repository
public class PostRecordRepository {

  private final DSLContext context;

  public PostRecordRepository(DSLContext context) {
    this.context = context;
  }

  public Optional<PostRecord> findOne(Integer id) {
    PostRecord result = context
      .selectFrom(Post.POST)
      .where(Post.POST.ID.eq(id))
      .fetchOne();

    return Optional.ofNullable(result);
  }

  public Integer insert(PostRecord postRecord) {
    PostRecord record = context.insertInto(Post.POST)
      .set(Post.POST.TITLE, postRecord.getTitle())
      .set(Post.POST.BODY, postRecord.getBody())
      .returning(Post.POST.ID)
      .fetchOne();

    return Optional.ofNullable(record)
      .map(PostRecord::getId)
      .orElseThrow(() -> new IllegalStateException("Inserted record is null"));
  }
}
