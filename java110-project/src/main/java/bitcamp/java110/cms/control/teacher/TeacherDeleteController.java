package bitcamp.java110.cms.control.teacher;

import java.util.Scanner;

import bitcamp.java110.cms.annotation.Autowired;
import bitcamp.java110.cms.annotation.Component;
import bitcamp.java110.cms.annotation.RequestMapping;
import bitcamp.java110.cms.dao.TeacherDao;

@Component
public class TeacherDeleteController {
    TeacherDao teacherDao;
    @Autowired
    public void setTeacherDao(TeacherDao teacherDao) {
        this.teacherDao = teacherDao;
    }
    
    @RequestMapping("teacher/delete")
    public  void deleteTeacher(Scanner keyIn) {
        System.out.print("삭제할 이메일? ");
        String email = keyIn.nextLine();


        if(teacherDao.delete(email)>0)
        {
            System.out.println("삭제하였습니다.");
        }else {
            System.out.println("이메일에 해당하는 선생이 없습니다");
        }
    }
}
