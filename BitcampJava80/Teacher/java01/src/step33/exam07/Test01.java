// 주제: 팩토리 객체를 통해 인스턴스 준비하기
package step33.exam07;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test01 {

  public static void main(String[] args) {
    ApplicationContext context = new ClassPathXmlApplicationContext(
      "step33/exam07/application-context.xml");

    System.out.println(context.getBean("c1"));
  }

}





