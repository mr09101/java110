import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;

import bitcamp.java110.cms.context.ApplicationContext;
import bitcamp.java110.cms.control.Controller;
import bitcamp.java110.cms.control.ManagerController;
import bitcamp.java110.cms.control.StudentController;
import bitcamp.java110.cms.control.TeacherController;
import bitcamp.java110.cms.domain.Manager;
import bitcamp.java110.cms.domain.Student;
import bitcamp.java110.cms.domain.Teacher;

public class App {
    
    static Scanner keyIn = new Scanner(System.in);

    public static void main(String[] args) throws Exception{
        HashMap<String,Controller> requestHandlerMapping = new HashMap<>();
        
        ApplicationContext iocContainer = 
                new ApplicationContext("bitcamp.java110.cms.control");
        
        while (true) {
            String menu = promptMenu();
            if (menu.equals("0")){
                System.out.println("안녕히 가세요!");
                break;
            }
            Controller controller=(Controller)iocContainer.getBean(menu);
            
            if (controller !=null) {
                controller.service(keyIn);
              }else {
                System.out.println("해당 메뉴가 없습니다");
            }
        }
        
        keyIn.close();
    }

    private static String promptMenu() {
        System.out.println("[메뉴]");
        System.out.println("1.학생 관리");
        System.out.println("2.강사 관리");
        System.out.println("3.매니저 관리");
        System.out.println("0.종료");
        System.out.print("메뉴 번호> ");
        return keyIn.nextLine();
        



    }
}






















