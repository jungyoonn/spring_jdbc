package com.eeerrorcode.jdbc.dao;

import java.util.Date;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.eeerrorcode.jdbc.vo.Member;

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

  @Test
  public void testRegister() {
    Member member = Member.builder()
    .id("aa111aa")
    .pw("1234")
    .name("깔깔유머집")      
    .build();
    log.info(memberDao.register(member));
  }

  @Test
  public void testList() {
    // memberDao.selectList().forEach(map -> {
    //   if(map instanceof Map) {
    //     log.info(((Map)map).get("id"));
    //   }
    // });

    memberDao.selectList().forEach(log::info);
  }

  @Test
  public void testOne() {
    log.info(memberDao.selectOne("aaaa"));
  }

  @Test
  public void testUpdate() {
    Member m = memberDao.selectOne("aaaa");
    m.setPw("1212");
    m.setName("쿠키");
    m.setEmail("aa@aaa.com");
    m.setRoad_addr("도로명주소 수정수정");
    m.setDetail_addr("상세주소입니다 수정수정");

    log.info("수정 전 ::: " + memberDao.selectOne("aaaa"));
    memberDao.modify(m);
    log.info("수정 후 ::: " + memberDao.selectOne("aaaa"));
  }

  @Test
  public void testDelete() {
    Member m = Member
    .builder()
    .id("testId")
    .pw("testPw")
    .name("testName")
    .build();
    memberDao.register(m);

    log.info("삭제 테스트용 멤버 insert :: " + memberDao.selectOne("testId"));
    memberDao.remove("testId");
    log.info("삭제 후 :: " + memberDao.selectList());
  }

  @Test
  public void testObject() {
    Object o = 1234;
    Long result;

    try {
      if(o instanceof Long) {
        result = ((Long)o).longValue();
        log.info("Long 타입으로 받은 " + result);
      } else if(o instanceof String) {
        String num = (String)o;
        result = Long.valueOf(num);
        log.info("String 타입으로 받은 " + result);
      } else {
        log.info("문자가 섞인 숫자, 캐스팅 불가");
      }
    } catch(ClassCastException e) {
      log.info("캐스팅 불가");
    }

    // 강사님 코드
    Object ob = new Date();
    try{
      String s = (String)ob;
    } catch(ClassCastException e) {
      log.info("캐스팅 과정의 문제");
    } catch(NumberFormatException e) {
      log.info("문자가 포함되어 있음");
    }
  }
}
