package enrollment.client.controller;

import enrollment.client.network.TCPClient;

import java.util.Scanner;

public class Controller {
    private TCPClient tcpClient; // 서버와 통신을 위한 소켓을 보유한 tcp connection
    private String serverIP; // 서버의 ip
    private int serverPort;
    private int studentID; // 프로그램 사용자 학번 for 재사용
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
        //1-3 학번 입력
        System.out.print("학번을(YYYY-XXXXX) 입력해 주세요: ");
        this.studentID = scanner.nextInt();
        tcpClient.send(this.studentID+"\n"); // 학번 정보 바로 서버로 전송

        //2. 메인 메뉴 분기
        String choice;
        while(true){
            System.out.println(messageConst.mainMenu);
            choice = scanner.nextLine();
            String reply;
            switch (choice){
                case "LISTUP":
                    System.out.println(messageConst.ListupMessage);
                    String allCourseRequest = "LISTUP/\n";
                    tcpClient.send(allCourseRequest);
                    reply = waitForReply(tcpClient);
                    System.out.println(reply); //[TODO] 전체 수업 목록 형식 맞춰서 출력 하도록 변경
                    break;
                case "APPLY":
                    System.out.println(messageConst.ApplyMessage);
                    int courseCode = scanner.nextInt();
                    tcpClient.send("APPLY/"+courseCode);
                    reply = waitForReply(tcpClient);
                    break;
                case "CANCEL":
                    System.out.println(messageConst.CancelMessage);
                    int courseCodeToDelete = scanner.nextInt();

                    break;
                case "MYLIST":
                    System.out.println(messageConst.MylistMessage);
                    break;
                case "EXIT":
                    tcpClient.terminate();
                    System.out.println("프로그램을 종료합니다.");
                    return;
                default:
                    System.out.println("명령어를 잘못 입력히셨습니다. 다시 입력하십시오.");
            }

        }



    }
    private String waitForReply(TCPClient tcpClient){
        StringBuffer receivedMsg= new StringBuffer();
        while(receivedMsg.isEmpty()){
            receivedMsg.append(tcpClient.receive());
        }
        return receivedMsg.toString();
    }

}
