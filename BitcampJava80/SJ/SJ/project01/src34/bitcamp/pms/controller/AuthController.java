package bitcamp.pms.controller;

import java.util.HashMap;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import bitcamp.pms.annotation.RequestMapping;
import bitcamp.pms.dao.MemberDao;
import bitcamp.pms.domain.Member;
import bitcamp.pms.util.CommandUtil;
import bitcamp.pms.util.Session;

@Controller
public class AuthController {
  final static String telFormat = "^(\\d{2,4}-)?(\\d{3,4})-(\\d{4})";
  final static String passwordFormat = "^(?=.*\\d)(?=.*[a-zA-Z])(?=.*[!@?])[a-zA-Z0-9!@?]{4,10}$";
  final static String emailFormat = "[\\w\\.]*@[\\w]*\\.[a-zA-Z]*";
  @Autowired
  private MemberDao memberDao;
  @RequestMapping("logout")
  public void service(Session session,Scanner keyScan) {
    String input = "";
    while (true) {
      input = loginPrompt(keyScan);
      switch (input) {
      case "1":
        doLogin(session,keyScan);
        return;
      case "2":
        doSighUp(keyScan);
        break;
      case "3":
        System.out.println("안녕히 가세요!");
        System.exit(0);
      default:
        System.out.println("올바르지 않은 명령어입니다.");
      }
    }
  }

  private String loginPrompt(Scanner keyScan) {
    System.out.println("1) 로그인");
    System.out.println("2) 회원가입");
    System.out.println("3) 종료");
    System.out.print("선택> ");
    return keyScan.nextLine().toLowerCase();
  }

  private void doSighUp(Scanner keyScan) {
    Member member = new Member();
    String check = null;
    System.out.print("이름? ");
    member.setName(keyScan.nextLine());
    while (true) {
      System.out.print("이메일? ");
      check = keyScan.nextLine();
      HashMap<String, Object> paramMap = new HashMap<>();
      paramMap.put("email", check);
      if(memberDao.selectOne(paramMap) != null)
        System.out.println("중복된 이메일 입니다. 다른 이메일을 정해주세요.");
      else if (check.matches(emailFormat))
        break;
      else
        System.out.println("이메일 형식이 맞지 않습니다. (유효 형식: xxx@xxx.xxx)");
    }
    member.setEmail(check);
    while (true) {
      System.out.print("암호? ");
      check = keyScan.nextLine();
      if (check.length() < 4 || check.length() > 10 ) 
        System.out.println("암호는 4 ~ 10자 까지만 가능합니다. ");
      else if (check.matches(passwordFormat))
        break;
      else
        System.out.println("최소 알파벳1개, 숫자1개, 특수문자(?,!,@)만 1개 반드시 포함");
    }
    member.setPassword(check);
    while (true) {
      System.out.print("전화? ");
      check = keyScan.nextLine();
      if (check.matches(telFormat))
        break;
      else
        System.out.println("전화 번호 형식에 맞지 않습니다.(3~4-4자), (2~4-3~4-4)");
    }
    member.setTel(check);
    try {
      memberDao.insert(member);
      System.out.println("회원 등록했습니다.");
    } catch (Exception e) {
      System.out.println("회원 등록에 실패했습니다.");
    }
  }

  private void doLogin(Session session,Scanner keyScan) {
    String email = null;
    String password = null;
    Member member = new Member();
    while (true) {
      System.out.print("이메일: ");
      email = keyScan.nextLine();
      HashMap<String, Object> paramMap = new HashMap<>();
      paramMap.put("email", email);
      member = memberDao.selectOne(paramMap);
      if (member == null) {
        System.out.println("등록되지 않은 사용자입니다.");
      } else {
        break;
      }
    }
    while (true) {
      System.out.print("암호: ");
      password = keyScan.nextLine();
      if (member.getPassword().equals(password)) {
        System.out.printf("환영합니다. %s님!\n", member.getName());
        session.setAttribute("loginUser", member);
        break;
      } else {
        System.out.println("암호가 맞지 않습니다.");
      }
    }
  }

  @RequestMapping("unsubscribe")
  public void doUnSubscribe(Session session,Scanner keyScan) {
    try {
      if (CommandUtil.confirm(keyScan, "정말 탈퇴하시겠습니까?")) {
        memberDao.delete(((Member) session.getAttribute("loginUser")).getNo());
        System.out.println("탈퇴가 완료되었습니다. 안녕히가세요.");
        System.exit(0);
      } else {
        System.out.println("탈퇴를 취소하였습니다.");
      }
    } catch (Exception e) {
      System.out.println("데이터 처리에 실패했습니다.");
    }
  }
}
