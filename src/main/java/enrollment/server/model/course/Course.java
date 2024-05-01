package enrollment.server.model.course;

import enrollment.server.constants.Major;

public class Course {
    // Class -> Course , 수업을 칭할 때 Course 사용이 보편적
    // private final int id;
    private int capacity; // 증원가능성이 있으니 final로 하지 않았음
    private int credit; // 수업의 학점
    private String name;
    private String professor;
    private Prerequisite prerequisite; // 선행과목 - Course의 id 값들이 저장됨
    private Major major;
    private int currentCapacity;
}