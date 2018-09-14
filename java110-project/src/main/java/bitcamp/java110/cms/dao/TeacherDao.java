package bitcamp.java110.cms.dao;

import java.util.List;

import bitcamp.java110.cms.dao.impl.DuplicationDaoException;
import bitcamp.java110.cms.dao.impl.MandatoryValueDaoException;
import bitcamp.java110.cms.domain.Manager;
import bitcamp.java110.cms.domain.Teacher;
public interface TeacherDao {
int insert(Teacher teacher)throws MandatoryValueDaoException,
DuplicationDaoException; ; 
List<Teacher> findAll(); 
Teacher findByEmail(String email); 
default Teacher findByNo(int no) {return null;} 
default int delete(String email) {return 0;}
default int deleteByNo(int no) {return 0;}

}
