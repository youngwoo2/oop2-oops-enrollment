package enrollment.server.model.course;

import java.util.Map;

public class EnrolledCourses {
    private final Map<String, Courses> enrolledCourses; // 학생이 수강한 총 수업들, key : "2024-1", value :  2024년 1학기에 수강한 수업목록

    public EnrolledCourses(Map<String, Courses> enrolledCourses) {
        this.enrolledCourses = enrolledCourses;
    }

    public Map<String, Courses> getEnrolledCourses() {
        return enrolledCourses;
    }
}
