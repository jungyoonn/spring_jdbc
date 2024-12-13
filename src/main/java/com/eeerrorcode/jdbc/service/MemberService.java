package com.eeerrorcode.jdbc.service;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.eeerrorcode.jdbc.dao.MemberDao;
import com.eeerrorcode.jdbc.dao.PostDao;
import com.eeerrorcode.jdbc.dao.ReplyDao;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class MemberService {
  private MemberDao memberDao;
  private PostDao postDao;
  private ReplyDao replyDao;
  private DataSourceTransactionManager manager;
  private TransactionDefinition definition;

  // 멤버 탈퇴 처리 (fk로 묶인 컬럼을 null로 초기화 후 멤버 삭제)
  // 일반적인 트랜잭션 구조이다
  public void quitMember2(String id) {
    TransactionStatus status = manager.getTransaction(definition);

    try{
      replyDao.updateToWriterNull(id);
      postDao.updateToWriterNull(id);
      memberDao.remove(id);
      manager.commit(status);
    } catch(DataAccessException e) {
      manager.rollback(status);
    }
  }

  // 위 메서드를 수정하면 아래와 같다
  @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
  public void quitMember(String id) {
    replyDao.updateToWriterNull(id);
    postDao.updateToWriterNull(id);
    memberDao.remove(id);
    // 트랜잭셔널 어노테이션 하나면 quitMember2()와 똑같이 동작한다
  }
}
