//GET 요청과 POST 프로토콜 상세 분석
package step04;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/step04/servlet16")
public class Servlet16 extends HttpServlet {

  private static final long serialVersionUID = 1L;
  @Override
  public void service(HttpServletRequest httpreq, HttpServletResponse httpres) 
      throws ServletException, IOException {
    httpres.setContentType("text/plain;charset=UTF-8");
    PrintWriter out = httpres.getWriter();
    out.println("Servlet16");
    
    out.printf("요청 method : %s\n",httpreq.getMethod());
//    GET 요청,  POST 요청에 상관 없이 파라미터 값에 꺼낸 방법은 같다
    out.printf("name : %s\n",httpreq.getParameter("name"));
    out.printf("email : %s\n",httpreq.getParameter("email"));
    out.printf("tel : %s\n",httpreq.getParameter("tel"));
    
  }
}

/*
# GET 요청의 파라미터
=> GET 요청은 Request-URI 에 파라미터 값을 붙여보낸다.
=> HTTP 요청 프로토콜

GET /web02/step04/servlet16?name=aaa&email=bbb&tel=ccc HTTP/1.1
Host: localhost:8080
Connection: keep-alive
Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*\\/*;q=0.8
Upgrade-Insecure-Requests: 1
User-Agent: Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.94 Safari/537.36
Referer: http://localhost:8080/web02/step04/getpost2.html
Accept-Encoding: gzip, deflate, sdch
Accept-Language: ko-KR,ko;q=0.8,en-US;q=0.6,en;q=0.4

=> 특징
  1) 대용량의 데이터를 보낼수 없다. 
     => Request-Line의 크기는 보통 8KB로 제한되어 있기 때문이다.
       (웹 서버마다 제한하는 크기가 조금씩 다르기는 하지만, 제한이 있고 크기가 작다.) 
     => 게시판의 글을 GET방식으로 보내는 것은 바람직하지 않다.
     
  2) 바이너리 데이터를 보내기 어렵다.
     => URL에 바이너리 데이터를 직접 포함할 수 없다.
     => 바이너리를 텍스트 형식으로 변환한(예: Base64로 인코딩) 후
        URL에 포함하여 보낼 수는 있다. 번거롭다.
        
  3) 즐겨찾기에 등록하거나 현재 페이지를 다른 사람에게 보낼 때 적합하다.
     => URL에 파라미터 값이 포함되어 있기 때문에,
        URL을 이용하여 즉시 해당 페이지로 이동할 수 있다.
        예) 특정 게시물을 가리킬 때
     => 조회 결과를 출력하는 요청에 맞다.
     
  4) 보안에 취약하다.
     => URL에 파라미터 값이 포함되어 있기 때문에
     
# POST 요청의 파라미터
=> POST 요청은 message-body에 파라미터 값을 붙여 보낸다.

POST /web02/step04/servlet16 HTTP/1.1
Host: localhost:8080
Connection: keep-alive
Content-Length: 26
Cache-Control: max-age=0
Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*\\/*;q=0.8
Origin: http://localhost:8080
Upgrade-Insecure-Requests: 1
User-Agent: Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.94 Safari/537.36
Content-Type: application/x-www-form-urlencoded
Referer: http://localhost:8080/web02/step04/getpost2.html
Accept-Encoding: gzip, deflate
Accept-Language: ko-KR,ko;q=0.8,en-US;q=0.6,en;q=0.4
(CRLF)
name=aaa&email=bbb&tel=ccc

=> 특징
  1) 대용량 데이터를 보낼 수 있다.
     => message-body에 붙여 보내기 때문에 서버가 허용하는 한에는
        무제한으로 데이터를 보낼 수 있다.
        
  2) 바이너리 데이터를 보내기 쉽다.    
     => 멀티파트 형식으로 바이너리 데이터를 보낸다.
     
  3) 즐겨찾기에 데이터를 저장해도 소용 없다.
     => URL에 파라미터 값이 포함되어 있지 않기 때문이다.
     => 조회 결과를 출력하는 요청에 적합하지 않다.
     
  4) 보안정보를 보낼 떄 GET 방식 보다 유리하다.
     => URL에 노출되지 않기 때문이다.
 */