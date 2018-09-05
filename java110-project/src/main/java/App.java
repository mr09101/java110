import java.util.Scanner;

public class App {

    static Scanner keyIn = new Scanner(System.in);

    public static void main(String[] args) {
        //사용자로부터 메뉴를 입력받기
        StudentController.keyIn=keyIn;
        TeacherController.keyIn=keyIn;
        ManagerController.keyIn=keyIn;
        
        while(true) {
            String menu= promptMenu();
            if(menu.equals("1")) {
                StudentController.serviceStudentMenu();
            }else if(menu.equals("2")){
                TeacherController.serviceTeacherMenu();
            }else if(menu.equals("3")){
                ManagerController.serviceManagerMenu();
            }else if(menu.equals("0")){
                System.out.println("안녕히 가세요!");
                break;
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

        while(true) {
            System.out.print("메뉴 번호> ");

            String menu=keyIn.nextLine();

            switch (menu) {
            case "1":
            case "2":
            case "3":
            case "0":
                return menu;
            default:
                System.out.println("메뉴 번호가 유요하지 않습니다");
            }
        }
    }



}






















