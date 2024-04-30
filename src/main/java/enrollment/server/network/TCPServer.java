package enrollment.server.network;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.io.PrintWriter;
//import java.net.ServerSocket;
//import java.net.Socket;
//
//public class TCPServer {
//    public static void main(String[] args) {
//        int PORT = 9999;
//        try {
//            ServerSocket serverSocket = new ServerSocket(PORT);
//            while (true) { // 여러번의 요청을 처리하기 위해 무한반복
//                // 요청별로 쓰레드를 만들어서 응답처리
//                new Thread(() -> {
//                    System.out.println("[서버 대기중...]");
//
//                    Socket clientSocket = serverSocket.accept(); // 새로운 소켓을 만들어서 반환해줌
//                    System.out.println(clientSocket);
//                    // ServerSocket#accept() 클라이언트 요청이 올때까지 대기
//                    // 요청이 들어오면, 새로운 소켓을 생성해 반환
//                    System.out.println(clientSocket.getInetAddress().getHostAddress() + "로부터 요청...");
//
//                    // 클라이언트로부터 데이터를 읽어들일 BufferedReader 생성
//                    BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
//                    String message;
//                    while ((message = in.readLine()) != null) {
//                        System.out.println("클라이언트로부터 수신한 메시지: " + message);
//                    }
//
//                    clientSocket.close();
//                    serverSocket.close();
//                }).start();
//            }
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
//}

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {
    public static void main(String[] args) {
        final int PORT = 12345; // 포트 번호를 원하는 값으로 변경하세요.

        try {
            ServerSocket serverSocket = new ServerSocket(PORT);
            System.out.println("서버가 시작되었습니다. 클라이언트의 연결을 기다립니다...");

            while (true) {
                // 클라이언트의 연결을 기다림
                Socket clientSocket = serverSocket.accept();
                System.out.println("클라이언트가 연결되었습니다.");

                // 클라이언트를 처리할 별도의 스레드 생성
                ClientHandler clientHandler = new ClientHandler(clientSocket);
                Thread thread = new Thread(clientHandler);
                thread.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class ClientHandler implements Runnable {
    private Socket clientSocket;

    public ClientHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        try {
            // 클라이언트로부터 데이터를 읽어들일 BufferedReader 생성
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            String message;
            while ((message = in.readLine()) != null) {
                System.out.println("클라이언트로부터 수신한 메시지: " + message);
            }

            // 클라이언트 소켓 닫기
            clientSocket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}