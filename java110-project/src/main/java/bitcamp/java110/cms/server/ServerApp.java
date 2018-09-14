package bitcamp.java110.cms.server;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import bitcamp.java110.cms.context.RequestMappingHandlerMapping;
import bitcamp.java110.cms.context.RequestMappingHandlerMapping.RequestMappingHandler;

public class ServerApp {

    ClassPathXmlApplicationContext iocContainer;
    RequestMappingHandlerMapping requestHandlerMap;
    public ServerApp() throws Exception{
        createIoCContainer();
        logBeansOfContainer();
        processRquestMappingAnnotation();

    }
    private void createIoCContainer() {
        iocContainer = new ClassPathXmlApplicationContext(
                "bitcamp/java110/cms/conf/application-context.xml");
        
    }

    private void processRquestMappingAnnotation() {
        requestHandlerMap =  new RequestMappingHandlerMapping();
        String[] names = iocContainer.getBeanDefinitionNames();
        for (String name : names) {
            Object obj = iocContainer.getBean(name);
            requestHandlerMap.addMapping(obj);
        }
    }


    private void logBeansOfContainer() {
        System.out.println("------------------------");
        String[] nameList = iocContainer.getBeanDefinitionNames();
        for (String name : nameList) {
            System.out.println(name);
        }
        System.out.println("------------------------");

    }

    public void service() throws Exception{

        //클라이언트 연결을 기다리는 서버 소켓 준비
        ServerSocket serverSocket=new ServerSocket(8888);
        System.out.println("서버 실행 중.....");

        while(true) {
            try(
                    Socket socket=serverSocket.accept();
                    PrintStream out= new PrintStream(
                            new BufferedOutputStream(
                                    socket.getOutputStream()));

                    BufferedReader in= new BufferedReader(
                            new InputStreamReader(
                                    socket.getInputStream()));
                    ){
                System.out.println(in.readLine());
                out.println("ok:김현성"); out.flush();
                while(true) {
                    String requestLine = in.readLine();
                    if(requestLine.equals("EXIT")) {
                        out.println("goodbye");
                        out.println();
                        out.flush();
                        break;
                    }
                    
                    RequestMappingHandler mapping = 
                            requestHandlerMap.getMapping(requestLine);
                    if (mapping == null) {
                        System.out.println("해당 요청을 처리할 수 없습니다.");
                        out.println();
                        out.flush();
                        continue;
                    }
                    
                    try {
                        mapping.getMethod().invoke(mapping.getInstance(), out);
                        out.println();
                    } catch (Exception e) {
                        e.printStackTrace();
                        out.println("요청 처리 중에 오류가발생했습니다");
                        out.println();
                    }
                    out.println();
                    out.flush();

                }
            }
        }
    }


    public static void main(String[] args) throws Exception {
        ServerApp serverApp =new ServerApp();
        serverApp.service();

    }
}




















