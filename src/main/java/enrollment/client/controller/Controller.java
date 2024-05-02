package enrollment.client.controller;

import enrollment.client.network.TCPClient;
import enrollment.client.view.InputView;
import enrollment.client.view.OutputView;

import java.sql.SQLOutput;
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
                //2-1 전체 수업 목록 서버에 요청 및 수신하여 출력
                case "LISTUP":
                    System.out.println(messageConst.ListupMessage);
                    tcpClient.send(OutputView.makeLISTUPmsg());
                    reply = waitForReply(tcpClient);
                    String[] coursesLISTUP = InputView.parseLISTUP(reply);
                    printLISTUPCourses(coursesLISTUP);
                    break;
                //2-2 특정 수업 수강 신청 요청 서버로 전송 및 결과 출력
                case "APPLY":
                    System.out.println(messageConst.ApplyMessage);
                    int courseCode = scanner.nextInt();
                    tcpClient.send(OutputView.makeAPPLYmsg(courseCode));
                    reply = waitForReply(tcpClient);
                    String applyResult = InputView.readAPPLYreply(reply);
                    if(applyResult.equals("ACCEPTED")){
                        System.out.println("[성공] 수강신청 성공");
                    }
                    else if(applyResult.equals("PREREQUISITE")){
                        System.out.println("[실패] 선수과목을 이수하지 않았습니다.");
                    }
                    else if (applyResult.equals("OVERCAPACITY")) {
                        System.out.println("[실패] 수강신청 정원을 초과했습니다.");
                    }
                    else{
                        System.out.println("[오류] 잘못된 응답");
                    }
                    break;
                //2-3 특정 수업 수강 신청 취소 요청 서버로 전송 및 결과 출력
                case "CANCEL":
                    System.out.println(messageConst.CancelMessage);
                    int courseCodeToDelete = scanner.nextInt();
                    tcpClient.send(OutputView.makeCANCELmsg(courseCodeToDelete));
                    reply = waitForReply(tcpClient);
                    String cancelResult = InputView.readCANCELreply(reply);
                    if(cancelResult.equals("ACCEPTED")){
                        System.out.println("[성공] 수강신청 철회 성공");
                    }
                    else if(cancelResult.equals("NOCOURSE")){
                        System.out.println("[실패] 수강신청 내역이 존재하지 않습니다.");
                    }
                    else{
                        System.out.println("[오류] 잘못된 응답");
                    }
                    break;
                //2-4 지금까지 수강신청 내역 서버로 요청 및 결과 출력
                case "MYLIST":
                    System.out.println(messageConst.MylistMessage);
                    tcpClient.send(OutputView.makeMYLISTmsg());
                    reply = waitForReply(tcpClient);
                    String[] coursesMYLIST = InputView.parseMYLIST(reply);
                    System.out.println("--------------------수강신청 내역--------------------");
                    printMYLISTCourses(coursesMYLIST);
                    break;
                //2-5 소켓 할당 해제하고 프로그램 종료
                case "EXIT":
                    tcpClient.terminate();
                    System.out.println("프로그램을 종료합니다.");
                    return;
                default:
                    System.out.println("[오류] 명령어를 잘못 입력히셨습니다. 다시 입력하십시오.");
            }

        }



    }

    // 서버에 요청 전송 후 응답메시지 대기 및 응답메시지 도착시 반환
    private String waitForReply(TCPClient tcpClient){
        return tcpClient.receive();
    }

    // LISTUP 요청에 대한 응답 출력
    private void printLISTUPCourses(String[] courses){
        for(String course : courses){
            String[] fields = course.split(",");
            String courseCode = fields[0];
            String courseName = fields[1];
            String courseCredit = fields[2];
            String professor = fields[3];
            String courseMajor = fields[4];
            System.out.println("수업코드: "+ courseCode + " 수업명: "+courseName+" 학점:" +courseCredit+" 교수: "+professor+
                    " 전공: "+courseMajor);
        }
    }
    // MYLIST 요청에 대한 응답 출력
    private void printMYLISTCourses(String[] courses){
        int appliedCredit =0;
        for(String course : courses){
            String[] fields = course.split(",");
            String courseCode = fields[0];
            String courseName = fields[1];
            String courseCredit = fields[2];
            String professor = fields[3];
            String courseMajor = fields[4];
            appliedCredit += Integer.parseInt(courseCredit);
            System.out.println("수업코드: "+ courseCode + " 수업명: "+courseName+" 학점:" +courseCredit+" 교수: "+professor+
                    " 전공: "+courseMajor);
        }
        System.out.println("현재까지 신청 학점 : "+ appliedCredit);
    }

}
