package com.eeerrorcode.jdbc.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class MemberServiceTests {
  @Autowired
  private MemberService service;

  @Test
  public void testQuit() {
    try {
      service.quitMember("aaaa");
    } catch(DataAccessException e) {
      log.error(e.getMessage());
    }
  }
}
