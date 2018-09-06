package bitcamp.java110.cms.control;

import java.util.Scanner;

import bitcamp.java110.cms.domain.Teacher;
import bitcamp.java110.cms.util.LinkedList;

public class TeacherController {
    private  LinkedList<Teacher> teachers=new LinkedList<>();
    public TeacherController(Scanner keyIn) {
        this.keyIn=keyIn;
    }
    public  Scanner keyIn;
    
    public  void serviceTeacherMenu() {
        while (true) {
            System.out.print("강사 관리> ");
            String command = keyIn.nextLine();
            if (command.equals("list")) {
                printTeachers();
            } else if (command.equals("add")) {
                inputTeachers();
            } else if (command.equals("delete")) {
                deleteTeacher();
            } else if (command.equals("detail")) {
                detailTeacher();
            } else if (command.equals("quit")) {
                break;
            } else {
                System.out.println("유효하지 않는 명령입니다.");
            }
        }
    }
    
    private  void printTeachers() {
        for(int i=0;i<teachers.size();i++) {
            Teacher s=teachers.get(i);
            System.out.printf("%d: %s, %s, %s, %s, %d, [%s]\n", 
                    i,
                    s.getName(), 
                    s.getEmail(), 
                    s.getPassword(), 
                    s.getTel(),
                    s.getPay(),
                    s.getSubjects());
        }
    }
    
    private  void inputTeachers() {
        while (true) {
            Teacher m = new Teacher();
            
            System.out.print("이름? ");
            m.setName(keyIn.nextLine());
            
            System.out.print("이메일? ");
            m.setEmail(keyIn.nextLine());
            
            System.out.print("암호? ");
            m.setPassword(keyIn.nextLine());
            
            System.out.print("전화? ");
            m.setTel(keyIn.nextLine());
            
            System.out.print("시급? ");
            m.setPay(Integer.parseInt(keyIn.nextLine()));
            
            System.out.print("강의과목?(예: 자바,C,C++) ");
            m.setSubjects(keyIn.nextLine());
            
            teachers.add(m);
            
            System.out.print("계속 하시겠습니까?(Y/n) ");
            String answer = keyIn.nextLine();
            if (answer.toLowerCase().equals("n"))
                break;
        }
    }
    
    
    private  void deleteTeacher() {
        System.out.print("삭제할 번호? ");
        int no = Integer.parseInt(keyIn.nextLine());
        
        if (no < 0 || no >= teachers.size()) {
            System.out.println("무효한 번호입니다.");
            return;
        }
        
        teachers.remove(no);
        
        System.out.println("삭제하였습니다.");
    }
    
    private  void detailTeacher() {
        System.out.print("조회할 번호? ");
        int no = Integer.parseInt(keyIn.nextLine());
        
        if (no < 0 || no >= teachers.size()) {
            System.out.println("무효한 번호입니다.");
            return;
        }
        Teacher teacher=teachers.get(no);
        
        System.out.printf("이름: %s\n", teacher.getName());
        System.out.printf("이메일: %s\n", teacher.getEmail());
        System.out.printf("암호: %s\n", teacher.getPassword());
        System.out.printf("전화: %s\n", teacher.getTel());
        System.out.printf("시급: %d\n", teacher.getPay());
        System.out.printf("강의과목: %s\n", teacher.getSubjects());
    }
}
