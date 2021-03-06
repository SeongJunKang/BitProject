/* 주제 : 파이널 활용*/

package step12$StaticInstance;

public class Exam11 {
  // 컴파일 오류
  // class Car2 extends Car8{}; //상속불가.

  public static void main(String[] args) {

  }
}

/*
* 클래스앞에 final 을 붙이는 경우
- 상속을 막고 싶을 떄.
- 왜 ?
  -> 다형적 변수의 특성에 따르면 상위 클래스를 요구하는 자리에
    하위 클래스를 대입할 수 있다.
  -> 보안이 필요한 업무에서는 특정 클래스가 다른 클래스로
    대체되는 것을 방지해야한다.
  -> 그런 경우 final 을 사용하여 하위 클래스를 만들지 못하도록 한다.
  -> 하위 클래스를 만들 수 없다면, 기존 클래스를 대체할 수 없다.
*/
