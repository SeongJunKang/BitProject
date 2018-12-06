//DBMS에 연결하기.
package step28$DBMS_JDBC;

import java.sql.Connection;
import java.sql.DriverManager;

import com.mysql.jdbc.Driver;

public class Test01 {

    public static void main(String[] args) {
      
      try {
        //1) java.sql.Driver 구현체 JDBC 드라이버 관리자에게 등록.
        DriverManager.deregisterDriver(new Driver());
        
        //2) JDBC 드라이버 관리자에게 java.sql.Connection 구현체 달라고 요청.
        // JDBC 드라이버 관리자 -> com.mysql.jdbc.Driver 객체에게 요구.
        // com.mysql.jdbc.Driver -> JDBC 드라이버 관리자에게 Connection 구현체 리턴
        // 즉, com.mysql.jdbc.ConnectionImpl 객체를 리턴.
        // JDBC 드라이버 관리자는 com.mysql.jdbc.Driver 가 리턴한 값을 그대로 리턴
        // 우리는 java.sql.Connection 인터페이스의 레퍼런스로 해당 객체를 받는다.
        Connection con = DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/java80db?user=java80&password=1111");
        System.out.println("연결성공 !");
        //3) 커넥션 객체를 사용한 후에 명시적으로 닫아야 한다.
        // 안닫는다면, 일정 시간동안 DBMS 서버 메모리에 연결 정보가 남아있다.
        // 연결정보가 남아 있다면 ? DBMS 는 최대 연결 수를 관리한다.
        // 연결 수가 최대값을 초과하면 더 이상 연결을 허락하지 않는다.
        //그래서 항상 커넥션을 다 사용한 다음에는 즉시 끊는 것이 중요하다.
        // 안닫는다면, 서버에 설정된 시간이 지나면 강제로 연결을 끊는다.(timeout)
        con.close();
      } catch (Exception e) {
        System.out.println("연결실패 !");
        e.printStackTrace();
      }
    } 
}



/*
JDBC API
-DBMS 와 연동할 떕 사용하는 도구들의 규약
-이 규약에 따라 DBMS 벤더는 클래스를 작서하여 배포한다.
-주요 규약
1) java.sql.Driver 인터페이스
  -> JDBC 드라이버 정보를 다루는 클래스가 지켜야할 규칙.
  -> DBMS와의 커넥션을 다루는 기능.
  -> 구현체
  mysql JDBC 드라이버 :com.mysql.jdbc.Driver 클래스
  Oracle JDBC 드라이버 : oracle.jdbc.OracleDriver 클래스

2) java.sql.Connection 인터페이스
  -> DBMS의 연결 정보를 다루고 질의 도구(SQL을 DBMS에 보내는 도구)를 준비하는 기능
  -> 구현체
  mysql JDBC 드라이버 :com.mysql.jdbc.Driver 클래스
  Oracle JDBC 드라이버 : oracle.jdbc.OracleConnectionWrapper 클래스
  
3) jajva.sql.Statement 인터페이스
  -> DBMS에 질의 하고 데이터를 가져오는 기능
  -> 구현체
  MYSQL JDBC 드라이버 : com.mysql.jdbc.StatementImpl 클래스
  Oracle JDBC 드라이버 : oracle.jdbc.driver.OracleConnectionWrapper 클래스
  
4) java.sql.ResultSet 인터페이스
  -> DBMS로부터 레코드를 가져오고, 레코드의 컬럼 값을 꺼내는 기능
  -> 구현체
  MYSQL JDBC 드라이버 : com.mysql.jdbc.ResultSetImpl
  Oracle JDBC 드라이버 : oracle.jdbc.driver.OracleReturnResultSet
  
  # JDBC API 사용
- 직접 구현체(인터페이스를 구현한 클래스)를 사용하지 않는다.
- 이유 ?
   -> 클래스 이름 때문에 특정 JDBC 드라이버 및 DBMS에 종속되기 때문이다.
- 프로그래밍 방식?
   -> 체인 방식을 통해 원하는 객체를 얻는다.
   -> 첫 번째 클래스의 이름만 알면 된다.
   -> 첫 번째 클래스 이름에만 종속된다.
*/

















