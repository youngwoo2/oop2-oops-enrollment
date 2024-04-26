package enrollment.server.model;

import enrollment.server.constants.Major;
import java.util.List;

public class Course {
    // Class -> Course , 수업을 칭할 때 Course 사용이 보편적
    private final int id;
    private int capacity;
    private int credit; // 수업의 학점
    private String name;
    private String professor;
    private List<Integer> prerequisite; // 선행과목 - Course의 id 값들이 저장됨
    private Major major;

}
