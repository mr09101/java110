/* 서블릿 배치 정보 - XML 태그로 배치정보 설정하기
 *-> 서블릿의 배치정보를 설저하는데는 두가지 방법이있다
 *  1)애노테이션으로 배치하기
 *      @WebServlet 애노테이션을 서블릿 클래스에 선언한다
 *  2)XML태그로 배치하기
 *   /WEB-INF/web.xml에 서블릿 배치 정보를 설정한다.
 *   'web.xml'을 배치설명자(Deployment Descriptor 파일; DD파일)라 부른다
 *   
 *   -주의
 *   web.xml 파일을 사용할 떄 다음 속성 값을 false로 설정하지 않으면
 *   애노테이션으로 배치한 정보를 처리하지 못한다
 *   <web-app 
 *   ```
  metadata-complete="false">
 *   ```
 */
//@WebServlet("/ex06/servlet03")
package bitcamp.java110.ex06;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Servlet03 extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    public void service(
            HttpServletRequest req, 
            HttpServletResponse res) 
                    throws ServletException, IOException {


        res.setContentType("text/plain;charset=UTF-8");
        PrintWriter out = res.getWriter();

        out.println("/ex06/servlet03 실행!");
    }
}





























