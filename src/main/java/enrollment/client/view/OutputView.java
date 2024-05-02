package enrollment.client.view;

public class OutputView {
    public static String makeLISTUPmsg(int studentID){
        return "LISTUP/\n";
    }
    public static String makeAPPLYmsg(int courseCode){
        return "APPLY/"+courseCode+"\n";
    }
    public static String makeCANCELmsg(int coureCode){
        return "CANCEL/"+coureCode+"\n";
    }
    public static String makeMYLISTmsg(){
        return "MYLIST/\n";
    }
}
