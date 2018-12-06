package step25$MethodInfo;

public class Test01 {
  static class Member {
    String name;
    public Member() {
      System.out.println("Member()");
    }
    public String getName() {
      return name;
    }
    public void setName(String name) {
      this.name = name;
    }
    
  }
  public static void main(String[] args) {
    Member m = new Member();
    m.setName("홍길동");
    // 인스턴스의 클래스 정보 가져오기.
    Class<?> c = m.getClass();    // 해당 인스턴스의 클래스 정보를 다루는 객체를 리턴한다.
    
    
    Class superClass = c.getSuperclass();
    System.out.println(superClass.getName());
    
    }
}
