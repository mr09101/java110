/*  서버측 객체 보관소
 * ->서블릿이나 필터,리스터를 실행하는 중에 필요한 값들을
 *  보관소에 저장하고 꺼내 쓸 수 있다.
 *  ->보관소
 *  1)ServletContext 보관소
 *      -생성시점: 웹애플리케이션이 시작될 때 각 웹앱플리케이션맏 ㅏ한 개의 보관소가 ㅐㅇ성됨
 *      -소멸시점: 웹애플리케이셔이 종료될 때
 *      -저장하는 대상
 *          -서블릿들이 공유하는 객체를 주로 보관한다.
 *          예)DAO,서비스 객체등
 *  2)HttpSession 보관소
 *      -생성시점: 각 클라이언트에 대해 세션을 시작할대 마다
 *      -소멸시점: 각 클라이언트에 대해 세션을 종료할때 마다
 *          -클라이언트 전용 정보를 보관한다
 *          예)로그인 정보, 각 페이지의 입력값을 임시로 유지할 때
 *  3)ServlerRequest 보관소
 *      -생성시점: 요청이 들어올 대 마다.
 *      -소멸시점: 응담을 완료할 때 마다.
 *          -요펑을 처리하는 서블릿끼리 객체를 공유할때
 *      
 *  4)PageContext 보관소
 *      -생성시점: JSP가 실행될 때 마다.
 *      -소멸시점: JSP 실행을 마쳤을 때 마다
 *          -JSP와 태그 핸들러가 공유하는 정보
 */
package bitcamp.java110.ex07;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(
        "/ex07/servlet02"
        )
public class Servlet02 extends HttpServlet {
    private static final long serialVersionUID = 1L;
    

    @Override
    public void service(
            HttpServletRequest req, 
            HttpServletResponse res) 
                    throws ServletException, IOException {


        res.setContentType("text/plain;charset=UTF-8");
        PrintWriter out = res.getWriter();

        out.println("/ex07/servlet02 실행!");
        //ServletContext 보관소에 저장된 값 꺼내기
        //->먼저 ServletContext 객체를 알아낸다.
        ServletContext sc=this.getServletContext();
        out.printf("ServletContext: aaa=%s\n", sc.getAttribute("aaa") );
        //Servlet01에서 ServletRequest 보관소에 저장한 값을 꺼낼 수 있는가
        //->없다!
        out.printf("ServletContext: bbb=%s\n", req.getAttribute("bbb") );

    }
}





























