//
package step10;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/step10/servlet36")
public class Servlet36 extends HttpServlet {

  private static final long serialVersionUID = 1L;
  
  @Override
  protected void service(
      HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    // 1) 쿠키 받기
    Cookie[] cookies = request.getCookies();
    // 2) 클라이언트로 부터 받은 쿠키 출력하기
    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
    out.println("쿠키 받았음! servlet 36<br>");
    if (cookies == null) {
      return;
    }
    for (Cookie cookie : cookies) {
      out.printf("%s = %s<br>",cookie.getName(),
          URLDecoder.decode(cookie.getValue(),"UTF-8"));
    }
  }
}
/* 
#/step10/servlet35 에서 받을 수 있는 쿠키
- name2(/)
- tel2(/web02/step10)

# HTTP 요청 프로토콜과 쿠키
GET /web02/step10/servlet36 HTTP/1.1
Host: localhost:8080
Connection: keep-alive
Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*\/*;q=0.8
Upgrade-Insecure-Requests: 1
User-Agent: Mozilla/5.0 (Windows NT 10.0) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.102 Safari/537.36
Accept-Encoding: gzip, deflate, sdch
Accept-Language: ko-KR,ko;q=0.8,en-US;q=0.6,en;q=0.4
Cookie: name=%ED%99%8D%EA%B8%B8%EB%8F%99; age=20; tel=010-1234-4321; tel2="path:null"; name2=%EA%B2%BD%EB%A1%9C%3A%EB%AA%A8%EB%93%A0
 */
