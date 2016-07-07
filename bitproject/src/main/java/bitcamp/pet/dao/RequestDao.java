package bitcamp.pet.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bitcamp.pet.vo.Request;

/*PetSitterMapper.xml에 선언도니 SQl 에 맞추어 메서드를 변경한다.
 * SQL id 와 메서드 이름을 같게 한다.
 * SQL parameterType과 메서드파라미터 타입이 같게 한다.
 * SQL resultType과 리턴타입이 같아야 한다.
 * mybatis는 RuntimeException 예외를 던지기 때문에
 * 각 메서드 선언부에 throws 절을 작성할 필요가 없다.
 */

public interface RequestDao {
  List<Request> selectOne(Map<String, Object> paramMap);
  List<Request> selectList(Map<String, Object> paramMap); 
  List<Request> selectLit(Map<String,Object> paramMap);
  int insert(Request request);

  int delete(int pno);

  int isPetSitter(Map<String,Object> paramMap);

  void update(Request Request);
  Request select(HashMap<String, Object> paramMap);
}
