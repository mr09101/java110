package bitcamp.java110.cms;
import java.lang.ModuleLayer.Controller;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import bitcamp.java110.cms.context.ApplicationContext;
import bitcamp.java110.cms.context.RequestMappingHandlerMapping;
import bitcamp.java110.cms.context.RequestMappingHandlerMapping.RequestMappingHandler;
import bitcamp.java110.cms.domain.Student;

public class App {
    public static List<Student> students=new ArrayList<>();

    static Scanner keyIn = new Scanner(System.in);

    public static void main(String[] args) throws Exception{
        HashMap<String,Controller> requestHandlerMapping = new HashMap<>();

        ApplicationContext iocContainer = 
                new ApplicationContext("bitcamp.java110.cms.control");

        //=>저장소에 보관된 객체 중에서 L

        RequestMappingHandlerMapping requestHandlerMap = 
                new RequestMappingHandlerMapping();
        //=>IoC컨테이너에 보관된 객체의 이름 목록을 가져온다.
        String[] names = iocContainer.getBeanDefinitionNames();
        for (String name:names) {
            //=>이름으로 객체를 꺼낸다.
            Object obj= iocContainer.getBean(name);

            //=>객체에서 @RequestMapping 이 붙은 메서드를 찾아 저장한다.
            requestHandlerMap.addMapping(obj);
        }

        while (true) {
            String menu = prompt();
            if (menu.equals("exit")){
                System.out.println("안녕히 가세요!");
                break;
            }
            RequestMappingHandler mapping=requestHandlerMap.getMapping(menu);
            if(mapping ==null) {
                System.out.println("해당메뉴가 없음");
                continue;
            }

            mapping.getMethod().invoke(mapping.getInstance(),keyIn);
        }

        keyIn.close();
    }


    private static String prompt() {
        System.out.print("메뉴> ");
        return keyIn.nextLine();




    }
}






















