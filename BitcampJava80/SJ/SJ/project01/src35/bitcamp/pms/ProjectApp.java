/*목표
 * -spring framework 적용
 * 1) 스프링 및 마이바티스 의존 라이브러리 추가
 * => build.gradle 파일 변경
 * 
 * 2) 스프링 설정 파일 추가
 * => conf/applicaton-context.xml
 * 
 * 3) 마이바티스 맵퍼 파일 변경
 * => 맵퍼 파일 네임스페이스를 인터페이스 이름(패키지명 포함) 과 같게 한다.
 * => 마이바티스 설정 파일(conf/mybatis-config.xml)을 제거한다
 * 
 * 4) DAO 클래스를 인터페이스로 변경한다.
 * => BoardDao, MemberDao, ProjectDao
 * => 인터페이스의 메서드 시그너처(메서드 이름과 파라미터, 리턴 타입)는
 *    맵퍼 파일의 SQL id, parameterType, resultType과 같아야 한다.  
 * 
 * 5) 컨트롤러 클래스를 개정한다.
 * =>DAO 인터페이스에 선언된 메서드가 변경되었기 때문에 그에 맞추어
 *   Controller 클래스의 코드를 변경한다.
 *   => DAO 의존 객체를 주입하려면 @Autowired 애노테이션을 붙여야 한다.
 * =>기존의 @Controller 애노테이션을 스프링 애노테이션으로 교체한다.
 *   =>import 변경
 * =>기존의 @controller 애노테이션을 제거한다.
 *   bitcamp.pms.annotation.Component
 *   bitcamp.pms.annotation.Controller
 *    
 * 6) 빈 컨테이너를 스프링 IoC 컨테이너로 교체한다.
 * => 기존의 ApplicationContext를 제거한다.
 *    bitcamp.pms.context.ApplicationContext
 * => 기존의 ApplicationContext를 스프링 ApplicationContext로 바꾼다.
 */
package bitcamp.pms;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.Scanner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import bitcamp.pms.context.request.RequestHandler;
import bitcamp.pms.context.request.RequestHandlerMapping;
import bitcamp.pms.controller.AuthController;
import bitcamp.pms.util.Session;

public class ProjectApp {

  ApplicationContext appContext;
  RequestHandlerMapping requestHandlerMapping;
  Scanner keyScan = new Scanner(System.in);
  Session session = new Session();
  AuthController authController;

  public void run() throws Exception {
    
        
    appContext = new ClassPathXmlApplicationContext("conf/application-context.xml");
    requestHandlerMapping = new RequestHandlerMapping(appContext);

    authController = (AuthController) appContext.getBean(AuthController.class);
    authController.service(session,keyScan);

    String input;
    do {
      input = commandPrompt();
      processCommand(input);
    } while (!input.equals("quit"));
    keyScan.close();
  }

  public static void main(String[] args) {
    ProjectApp projectApp = new ProjectApp();
    try {

      projectApp.run();

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  void processCommand(String input) {
    if (input.equals("quit")) {
      doQuit();
    } else if (input.equals("about")) {
      doAbout();
    } else {
      RequestHandler requestHandler = (RequestHandler) requestHandlerMapping.getRequestHandler(input);
      if (requestHandler == null) {
        doError();
        return;
      }
      Method method = requestHandler.getMethod();
      try {
        ArrayList<Object> args = new ArrayList<>();
        Object arg = null;
        for (Parameter param : method.getParameters()) {
          if(param.getType() == Scanner.class) {
            arg = keyScan;
          } else if (param.getType() == Session.class) {
            arg = session;
          } else {
            arg = appContext.getBean(param.getType());
          }
          args.add(arg);
        }
        method.invoke(requestHandler.getObj(), args.toArray());
      } catch (Exception e) {
        System.out.println("명령 처리 중에 오류가 발생했습니다!");
      }
    }
  }

  String commandPrompt() {
    System.out.print("명령> ");
    return keyScan.nextLine().toLowerCase();
  }

  public void doQuit() {
    System.out.println("안녕히 가세요!");
  }

  public void doError() {
    System.out.println("올바르지 않은 명령어입니다.");
  }

  void doAbout() {
    System.out.println("비트캠프 80기 프로젝트 관리 시스템!");
  }

}
