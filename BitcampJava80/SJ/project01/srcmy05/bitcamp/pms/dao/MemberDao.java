package bitcamp.pms.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import bitcamp.pms.annotation.Component;
import bitcamp.pms.domain.Member;

@Component
public class MemberDao {
  SqlSessionFactory sqlSessionFactory;

  public MemberDao() {
  }

  public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
    this.sqlSessionFactory = sqlSessionFactory;
  }

  public List<Member> selectList() throws Exception {
    SqlSession sqlSession = sqlSessionFactory.openSession();
    try {
      return sqlSession.selectList("MemberDao.selectList");
    } finally {
      sqlSession.close();
    }
  }

  public Member selectOne(int no) throws Exception {
    SqlSession sqlSession = sqlSessionFactory.openSession();
    try {
      return sqlSession.selectOne("MemberDao.selectOne", no);
    } finally {
      sqlSession.close();
    }
  }
  
  public int insert(Member member) throws Exception {
    SqlSession sqlSession = sqlSessionFactory.openSession(true);
    try {
      return sqlSession.insert("MemberDao.insert", member);
    } finally {
      sqlSession.close();
    }
  }

  public int update(Member member) throws Exception {
    SqlSession sqlSession = sqlSessionFactory.openSession(true);
    try {
      return  sqlSession.update("MemberDao.update", member);
    } finally {
      sqlSession.close();
    }

  }

  public int delete(int no) throws Exception {
    SqlSession sqlSession = sqlSessionFactory.openSession(true);
    try {
      return sqlSession.update("MemberDao.delete", no);
    } finally {
      sqlSession.close();
    }
  }

}
