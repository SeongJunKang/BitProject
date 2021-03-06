/* 주제: 음수의 표현 */
package step01$JavaClassMemory;

public class Exam09 {
  public static void main(String[] args) {
    System.out.println(Integer.toBinaryString(10));
    System.out.println(Integer.toBinaryString(-7));
  }
}

/*
이진수의 음수 표현
10(10진수) => 0000 1010(2진수)
-10(10진수) => 0110
0000 1010 (+10)
1111 0101 (1의 보수)
  1111 0101
+ 0000 0001
------------
  1111 0110 (1의 보수 + 1 = 2의 보수)

음수를 2의 보수로 표현한 이유?
- 덧셈을 통해 빼기를 수행하기 위함.
예) 10 - 7 = 10 + (-7) = 3
10      => 0000 1010
7       => 0000 0111
-7      => 1111 1000 + 1 = 1111 1001

  0000 1010 (10)
+ 1111 1001 (-7)
-----------------
1 0000 0011 (3)


*/
