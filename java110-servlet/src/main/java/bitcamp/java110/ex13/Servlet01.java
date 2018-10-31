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

@WebServlet("/ex13/servlet01")
public class Servlet01 extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(
            HttpServletRequest request, 
            HttpServletResponse response) 
                    throws ServletException, IOException {
        
        Member member = new Member();
        member.setNo(Integer.parseInt(request.getParameter("no")));
        member.setName(request.getParameter("name"));
        
        Inventory inventory = (Inventory) this.getServletContext().getAttribute("inventory");
        inventory.setMember(member);
        
        RequestDispatcher rd = request.getRequestDispatcher("/ex13/servlet03");
        rd.forward(request, response);
    }
        
}













