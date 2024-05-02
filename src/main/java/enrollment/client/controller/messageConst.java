package enrollment.client.controller;

public class messageConst {
    static final String mainMenu = """
            - LISTUP : 수업 목록 요청
            - APPLY : 특정 수업 신청
            - CANCEL : 특정 수업 신청 취소
            - MYLIST : 지금까지 수강 신청 내역
            - EIXT : 프로그램 종료
            """;

    static final String ListupMessage = "전체 수업 목록";
    static final String ApplyMessage = "수업코드를 입력해 주십시오: ";
    static final String CancelMessage = "수강 신청 취소할 수업의 코드를 입력해 주십시오: ";
    static final String MylistMessage = "지금까지의 수강 신청 내역";

}
