package enrollment.server.model;

import enrollment.server.constants.Major;
import enrollment.server.constants.Status;
import enrollment.server.model.course.EnrolledCourses;

public class Student {
    private final int id; // 학번
    private int currentCredits; // 현재 수강한 총 학점을 의미, 학점 제한은 18학점으로 통일 - 검증부분에서 거르기
    private String name;
    private EnrolledCourses enrolledCourses; // 학생이 수강한 총 수업들, key : "2024-1", value :  2024년 1학기에 수강한 수업목록
    private Major major;
    private Status status; // 학적 상태

    public Student(int id, int currentCredits, String name, EnrolledCourses enrolledCourses, Major major, Status status) {
        this.id = id;
        this.currentCredits = currentCredits;
        this.name = name;
        this.enrolledCourses = enrolledCourses;
        this.major = major;
        this.status = status;
    }
}
