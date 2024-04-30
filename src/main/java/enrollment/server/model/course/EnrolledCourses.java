package enrollment.server.model.course;

import enrollment.server.model.course.Course;

import java.util.List;
import java.util.Map;

public class EnrolledCourses {
    private Map<String, List<Course>> enrolledCourses; // 학생이 수강한 총 수업들, key : "2024-1", value :  2024년 1학기에 수강한 수업목록

}
