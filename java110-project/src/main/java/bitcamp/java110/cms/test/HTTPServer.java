package bitcamp.java110.cms.test;

import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class HTTPServer {
    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(8080);
        try(

                Socket socket=serverSocket.accept();

                PrintStream out = new PrintStream(socket.getOutputStream());
                Scanner in = new Scanner(socket.getInputStream());
                ){
           

            while(true) {
                String str = in.nextLine();
                System.out.println(str);
                
                if(str.length()==0)
                    break;

            }
            out.println("HTTP/1.1 200 OK");
            out.println("Content-Length:6");
            out.println();
            out.println("Hello!");
            
           

        }

    }
}
