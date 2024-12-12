package com.eeerrorcode.jdbc.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class MemberTests {
  @Autowired
  private MemberDao memberDao;

  @Test
  public void testGetTime() {
    log.info(memberDao.getTime());
  }
}
