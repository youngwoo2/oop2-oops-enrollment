package enrollment.client.controller;

import enrollment.client.network.TCPClient;

import java.util.Scanner;

public class Controller {
    private TCPClient tcpClient; // 서버와 통신을 위한 소켓을 보유한 tcp connection
    private String serverIP; // 서버의 ip
    private int serverPort;
    private String studentID; // 프로그램 사용자 학번 for 재사용
    // 서버의 ip를 입력하세요 : 192.168.22222
    // 학번을 입력해주세요 :
    public void run() {
        Scanner scanner = new Scanner(System.in);
        //1. TCP 커넥션 설정
        //1-1 서버 ip 입력
        System.out.print("서버 IP를 입력해 주세요: ");
        this.serverIP = scanner.nextLine();
        System.out.print("서버 포트번호를 입력해 주세요: ");
        this.serverPort = scanner.nextInt();

        //1-2 TCP connection 객체 생성
        this.tcpClient = new TCPClient(this.serverIP, this.serverPort);
        tcpClient.send("Hello World!\n");
        scanner.nextLine();
        String choice =scanner.nextLine();
        /*
        * 1. listup : 수업 목록 요청
        * 2. apply : 특정 수업 신청
        * 3. cancel : 특정 수업 신청 취소
        * 4. mylist : 지금까지 수강 신청 내역
        * 5. exit
        *
        *
        while(true){
            System.out.println(messageConst.mainMenu);
            choice = scanner.nextLine();
            switch (choice){
                case "LISTUP":
                    break;
                case "APPLY":
                    break;
                case "CALCEL":
                    break;
                case "MYLIST":
                    break;
                case "EXIT":
                    System.out.println("프로그램을 종료합니다.");
                    return;
                default:
                    System.out.println("명령어를 잘못 입력히셨습니다. 다시 입력하십시오.");
            }

        }
        */


    }

}
