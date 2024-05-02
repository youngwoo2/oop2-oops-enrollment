package enrollment.client.view;

public class InputView {
    //parseLISTUP : 서버로부터 받은 전체 수업 목록을 읽고 String 배열에 담아 반환한다.
    // String 배열의 각 원소는 수업의 필드가 합쳐진 하나의 String이다.
    public static String[] parseLISTUP(String input){}
    // readAPPLYreply : APPLY를 보낸뒤 서버로부터 받은 결과 메시지를 읽어 반환한다.
    public static String readAPPLYreply(String input){}
    // readCANCELreply : CANCEL을 보낸뒤 서버로부터 받은 결과 메시지를 읽어 반환한다.
    public static String readCANCELreply(String input){}
    // parseMYLIST : MYLIST를 보낸뒤 서버로부터 받은 지금까지 수강신청 내역을 String 배열에 담아 반환한다.
    public static String[] parseMYLIST(String input){}
}
