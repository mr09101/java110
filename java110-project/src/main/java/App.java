import java.util.Scanner;

public class App {


    public static void main(String[] args) {
        String [] names = new String[100];
        String [] emails = new String[100];
        String [] passwords = new String[100];
        
        int index=0; //index 배열을 가르키는

        // 1) 키보드 입력을 처리할 객체 준비
        Scanner keyIn = new Scanner(System.in);

        // 2) 사용자로부터 회원정보 입력받기

        while(true) {
            System.out.print("이름? ");
           names [index] = keyIn.nextLine();

            System.out.print("이메일? ");
            emails [index] = keyIn.nextLine();

            System.out.print("암호? ");
            passwords [index] = keyIn.nextLine();
            
            index++;
            

            System.out.print("계속 하시곗습니까?(Y/n) ");
            String answer = keyIn.nextLine();
            if(answer.toLowerCase().equals("n"))
                break;

        }
        for(int i=0;i<index;i++) {
        System.out.printf("%s,%s,%s\n",names[i],emails[i],passwords[i]);
        }
        keyIn.close();
    }

}
