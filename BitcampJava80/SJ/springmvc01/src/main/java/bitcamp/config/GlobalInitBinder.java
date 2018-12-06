package bitcamp.config;

import java.text.SimpleDateFormat;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;

// @ControllerAdvice를 붙이면, 페이지 컨트롤러를 실행할 때 마다 참조한다.
// @InitBinder붙인 메서드,
// => 페이지 컨트롤러의 메서드를 호출할 때 마다 먼저 실행한다
@ControllerAdvice
public class GlobalInitBinder {
  CustomDateEditor dateEditor;
  
  public GlobalInitBinder() {
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    dateFormat.setLenient(false); 
    dateEditor = new CustomDateEditor(dateFormat, false);
  }
  
  @InitBinder
  public void initBinder(WebDataBinder binder) {
    System.out.println("GlobalInitBinder.initBinder()");
    binder.registerCustomEditor(java.util.Date.class, dateEditor);
  }
}
