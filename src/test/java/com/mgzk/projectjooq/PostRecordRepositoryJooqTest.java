package com.mgzk.projectjooq;

import com.mgzk.projectjooq.domain.tables.records.PostRecord;
import com.mgzk.projectjooq.repository.PostRecordRepository;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jooq.JooqTest;
import org.springframework.context.annotation.Import;

@JooqTest
@Import(PostRecordRepository.class)
public class PostRecordRepositoryJooqTest {

  private static final String BODY = "body";

  private static final String TITLE = "title";

  private final PostRecordRepository repository;

  private final PostRecord postRecord;

  @Autowired
  public PostRecordRepositoryJooqTest(PostRecordRepository repository) {
    this.repository = repository;
    this.postRecord = create(BODY, TITLE);
  }

  @Test
  void insert() {
    Integer id = repository.insert(this.postRecord);

    Assertions.assertNotNull(id);
  }

  @Test
  void findById() {
    Integer id = repository.insert(postRecord);
    Optional<PostRecord> result = repository.findOne(id);

    Assertions.assertFalse(result.isEmpty());
    Assertions.assertEquals(id, result.get().getId());
    Assertions.assertEquals(BODY, result.get().getBody());
    Assertions.assertEquals(TITLE, result.get().getTitle());
  }

  private PostRecord create(String body, String title) {
    PostRecord postRecord = new PostRecord();
    postRecord.setBody(body);
    postRecord.setTitle(title);
    return postRecord;
  }
}
