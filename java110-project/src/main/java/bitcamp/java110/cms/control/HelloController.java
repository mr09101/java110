package bitcamp.java110.cms.control;

import java.util.Scanner;

import bitcamp.java110.cms.annotation.Component;
import bitcamp.java110.cms.annotation.RequestMapping;
// 일반주석!
@Component("hello")
public class HelloController{
    

   @RequestMapping
    public void hello(Scanner keyIn) {
         System.out.println("안녕하세여!!");
    }
    
    

}
