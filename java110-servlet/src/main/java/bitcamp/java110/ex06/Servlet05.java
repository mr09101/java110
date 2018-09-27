/* 서블릿 배치 정보 - 컨텍스트 초기화 파라미터
 * =>컨텍스트 초기화 파라미터는 모든 서블릿이 참조할수있다
 *   즉 모든 서블릿이 공유해야 하는 정보를 컨텍스트 초기화 파라미터로 선언하라!
 * ->활용
 *  DB 연결정보, 파일 업로드 설정 정보 등
 */
package bitcamp.java110.ex06;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*@WebServlet(
        value="/ex06/servlet04",
        loadOnStartup=1,
        initParams= {
                @WebInitParam(name="aaa",value="hello"),
                @WebInitParam(name="bbb",value="hello2"),
                @WebInitParam(name="ccc",value="hello3")
        })*/
        
public class Servlet05 extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    public Servlet05() {
        System.out.println("ex06.ser04 생성자 호출됭!");
    }
    @Override
    public void init() throws ServletException {
        System.out.println("ex06.ser04.init 호출됨");
    }

    @Override
    public void service(
            HttpServletRequest req, 
            HttpServletResponse res) 
                    throws ServletException, IOException {


        res.setContentType("text/plain;charset=UTF-8");
        PrintWriter out = res.getWriter();

        out.printf("aaa=%s\n",this.getInitParameter("aaa"));
        out.printf("bbb=%s\n",this.getInitParameter("bbb"));
        out.printf("ccc=%s\n",this.getInitParameter("ccc"));
    }
}





























