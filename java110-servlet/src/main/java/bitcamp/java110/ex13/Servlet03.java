// JSP 사용 전 - 로그인 폼 출력하기
// => 개발자가 직접 HTML 출력 코드를 작성해야 한다.
//
// JSP 
// => 개발자를 대신하여 서블릿 클래스를 정의하고,
//    자바 출력 코드를 작성한다.
// => 구동 원리
//    hello.jsp ===> [JSP 엔진] ===> hello_jsp.java 생성
//    - 생성된 자바 클래스는 HttpServlet 클래스의 하위 클래스이다.
//    - 클래스 이름은 JSP 엔진에 따라 다를 수 있다.
//    - JSP 파일을 직접 실행하는 것이 아니다.
//
package bitcamp.java110.ex13;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bitcamp.java110.Member;

@WebServlet("/ex13/servlet03")
public class Servlet03 extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(
            HttpServletRequest request, 
            HttpServletResponse response) 
                    throws ServletException, IOException {
        
        
        Inventory inventory = (Inventory) this.getServletContext().getAttribute("inventory");
        Member member= inventory.getMember();
        
        response.setContentType("text/plain;charset=UTF-8");
        PrintWriter out=response.getWriter();
        out.printf("스레드명=%s\n", Thread.currentThread().getName());
        out.printf(" no=%d\n", member.getNo());
        out.printf(" name=%s\n", member.getName());
    }
        
}













