package bitcamp.java110.cms.servlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import bitcamp.java110.cms.dao.impl.ManagerMysqlDao;
import bitcamp.java110.cms.dao.impl.StudentMysqlDao;
import bitcamp.java110.cms.dao.impl.TeacherMysqlDao;
import bitcamp.java110.cms.util.DataSource;


public class InitServlet extends HttpServlet{
    private static final long serialVersionUID = 1L;

    @Override
    public void init() throws ServletException {
        System.out.println("initServlet.init() 실행!");
        ServletContext sc=this.getServletContext();
        //Dao가 사용할 DB커넥션풀 객체준비
        try{
        DataSource dataSource=new DataSource(
                sc.getInitParameter("jdbc.driver"),
                sc.getInitParameter("jdbc.url"),
                sc.getInitParameter("jdbc.username"),
                sc.getInitParameter("jdbc.password"));
        //DAO객체 생성 및 DB커넥션풀 주입하기
        ManagerMysqlDao managerDao=new ManagerMysqlDao();
        managerDao.setDataSource(dataSource);
        
        StudentMysqlDao studentDao=new StudentMysqlDao();
        studentDao.setDataSource(dataSource);
        
        TeacherMysqlDao teacherDao=new TeacherMysqlDao();
        teacherDao.setDataSource(dataSource);
        
        sc.setAttribute("managerDao", managerDao);
        sc.setAttribute("studentDao", studentDao);
        sc.setAttribute("teacherDao", teacherDao);
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
