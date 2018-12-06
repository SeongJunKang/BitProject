// @RequestMapping 사용법 - GET 요청과 POST 요청 구분2
package bitcamp.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/controller06")
public class Controller06 {

  @RequestMapping(value="/test",method=RequestMethod.GET)
  public String hello() {
    return "/hello.jsp";
  }

  @RequestMapping(value="/test",method=RequestMethod.POST)
  public String hello2() {
    return "/hello2.jsp";
  }

}

/*
# GET/POST 구분
- @RequestMapping()의 method 프로퍼티로 구분한다.
- 항상 요청 URL은 클래스에 선언된 @RequestMapping과
  
 */
