//주제 : 오버라이딩

package step14$SpecialGeneral.exam05;
public class Test {
  public static void main(String[] args) {

      // Car c= new Car();
      // c.printInfo();
      Sedan s1 = new Sedan();

      //수퍼클래스 보고 만든 메모리
      s1.model = "티코";
      s1.maker = "비트자동차";
      s1.cc = 2000;
      s1.capacity = 5;

      // 자신의 설계도를 보고 만든 메모리
      s1.isAutomatic = true;
      s1.isSunroof = false;

      //수퍼 클래스로 부터 사용 허가를 받은 printInfo() 실행
      // 서브 클래스 Sedan 에 추가한 속성값을 출력하지 못한다.
      s1.printInfo();

      //해결책 ?  서버로부터 상속받은 메서드를 재정의한다.
      // sedan 2와 test2 확인
  }
}
