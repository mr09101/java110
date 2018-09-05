import java.util.Scanner;


public class ManagerController {

    static Manager[] managers = new Manager[100];
    static int managerIndex = 0;
    static Scanner keyIn;

    static class Manager extends Member{
        protected String name;
        protected String email;
        protected String password;
        protected String tel;
        protected String position;
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
        public String getEmail() {
            return email;
        }
        public void setEmail(String email) {
            this.email = email;
        }
        public String getPassword() {
            return password;
        }
        public void setPassword(String password) {
            this.password = password;
        }
        public String getTel() {
            return tel;
        }
        public void setTel(String tel) {
            this.tel = tel;
        }
        public String getPosition() {
            return position;
        }
        public void setPosition(String position) {
            this.position = position;
        }
    }
    static void serviceManagerMenu() {
        while(true)
        {            System.out.print("매니저 관리> ");
        String command =keyIn.nextLine();
        if (command.equals("list")) {
            printManagers();
        }else if(command.equals("add")){
            inputManagers();
        }else if(command.equals("quit")) {
            break;
        }else {
            System.out.println("유효하지 않는 명령");
        }
        }
    }
    static void printManagers() {
        //for (int i = 0; i < index; i++)
        int count=0;
        for (Manager s : managers)
        {
            if (count++ == managerIndex)
                break;
            System.out.printf("%s,%s,%s,%s,[%s]\n", 
                    s.getName(), 
                    s.getEmail(), 
                    s.getPassword(),
                    s.getTel(),
                    s.getPosition());
                    
    }


}
    static void inputManagers() {
        while (true) {
            Manager m = new Manager();

            System.out.print("이름? ");
            m.setName(keyIn.nextLine());

            System.out.print("이메일? ");
            m.setEmail(keyIn.nextLine());

            System.out.print("암호? ");
            m.setPassword(keyIn.nextLine());
            
            System.out.print("전화? ");
            m.setTel(keyIn.nextLine());
            
            System.out.print("직책?(사장,부장,과장) ");
            m.setPosition(keyIn.nextLine());
            
            

            managers[managerIndex++] = m;

            System.out.print("계속 하시겠습니까?(Y/n) ");
            String answer = keyIn.nextLine();
            if (answer.toLowerCase().equals("n"))
                break;
        }
    }

}