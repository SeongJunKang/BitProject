// collection API  - Hash 코드와 Hashmap
package step21$Collection_Hash;
import java.util.HashMap;
import java.util.Set;
import java.util.Collection;

public class Exam17 {

  static class Member {
    String name;
    int age;

    public Member(String name, int age) {
      this.name = name;
      this.age = age;
    }

    @Override
    public String toString() {
      // return Integer.toString(this.name.hashCode());// + ", " +this.age;
      return this.name + ", " +this.age;
    }

    @Override
    public int hashCode() {
      return this.toString().hashCode();
    }

    @Override
    public boolean equals(Object other) {
      if (other == null || other.getClass() != this.getClass()) {
        return false;
      }
     Member m = (Member)other;
      if(!this.name.equals(m.name)) {
        return false;
      }
      if(this.age != m.age) {
        return false;
      }
        return true;
      }
    }


  public static void main(String[] args) throws Exception {
    Member m1 = new Member("홍길동",20);
    Member m2 = new Member("임꺽정",30);
    Member m3 = new Member("유관순",16);
    // HashMap <키 , 값>
    HashMap<String, Member> map = new HashMap<>();
    map.put("aaa",m1);
    map.put("bbb",m2);
    map.put("ccc",m3);

    //  Map 에서 값 목록을 꺼낸다. 키 제외.
    Collection<Member> values = map.values();
    for(Member member : values) {
      System.out.println(member);
    }
  }
}


/*
*/
