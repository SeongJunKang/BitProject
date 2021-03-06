package bitcamp.pet.service;

import java.util.List;

import bitcamp.pet.vo.PetSitter;

public interface PetSitterService {
  void add(PetSitter petsitter);
  void delete(int pno);
  PetSitter retrieveByNo(int pno);
  PetSitter retrieveByNick(String nick);
  void change(PetSitter petsitter);
  void setInfo(PetSitter petsitter);
  int countPage(int pageSize);
  boolean exist(int no);
//  List<PetSitter> list(String order, int index);
  List<PetSitter> list(String order, int pageNo, int pageSize);
  List<PetSitter> mainlist();
  List<PetSitter> search(String index, int pageNo, int pageSize);
  int searchCount(String index, int pageSize);
  
}
