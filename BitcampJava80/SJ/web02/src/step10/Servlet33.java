// 쿠키의 사용 범위를 지정하기 - 다른 경로에도 쿠키 보내는 방법
package step10;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/step10/servlet33")
public class Servlet33 extends HttpServlet {

  private static final long serialVersionUID = 1L;
  
  @Override
  protected void service(
      HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    // 1) 쿠키 생성하기.
    Cookie cookie1 = new Cookie("name2",URLEncoder.encode("경로:모든","UTF-8"));
    cookie1.setPath("/"); // 모든 Path(요청)에 대해 쿠키를 보내라.
    Cookie cookie2 = new Cookie("age2","path:other");
    cookie2.setPath("/web02/other"); 
    // "ohter" 경로(하위 경로 포함)에 대해서만 쿠키를 보내라고 지정한다.
    // 쿠키 경로에서 "/" 웹 서버 루트를 의미한다.
    // 웹 애플리케이션 루트 "/web02"가 아니다.
    Cookie cookie3 = new Cookie("tel2","path:null");
    // cookie3.setPath("/step10"); // 경로를 지정하지 않으면 해당 서블릿이 있는
                                  // 경로(하위 경로 포함)에 한정시킨다.
    
    // 2) 응답 프로토콜에 쿠키 정보 첨부하기.
    response.addCookie(cookie1);
    response.addCookie(cookie2);
    response.addCookie(cookie3);
    
    // 3) 클라이언트에게 응답하기.
    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
    out.println("쿠키 보냈음 servlet33 !");
  }
}
/* 
# 쿠키 경로 설정하기 
- 쿠키를 받은 클라이언트가 언제 다시 서버에 쿠키를 전달할지 설정할 수 있다.
- 쿠키 경로를 설정하지 않으면 기본으로 쿠키를 보낸 경로에 한정시킨다.
- 물론 하위 경로도 포함시킨다.
- "/"는 웹 서버 루트를 의미한다.
  
 */
