package enrollment.server.model;

import enrollment.server.constants.Period;
import enrollment.server.model.course.Course;
import enrollment.server.model.course.Courses;
import enrollment.server.model.student.Student;
import enrollment.server.model.student.Students;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Enrollment {
    private Students students; // 컬렉션 변경 가능
    private Courses courses;

    public Enrollment(Students students, Courses courses) {
        this.students = students;
        this.courses = courses;
    }

    public void register(int studentId, int courseId) {
        Student student = students.getStudent(studentId);
        Course course =  courses.getCourse(courseId);

        if (!Period.check()) {
            throw new IllegalArgumentException("수강신청기간이 아닙니다.");
        }
        if (!student.checkCurrentCredits(course)) {
            throw new IllegalArgumentException("수강 가능한 학점을 초과했습니다.");
        }
        if (!student.checkMajor(course)) {
            throw new IllegalArgumentException("해당 전공이 아닙니다.");
        }
        if (!student.checkPrerequisite(course)){
            throw new IllegalArgumentException("선수과목이 존재합니다.");
        }
        if (!course.checkCapacity()) {
            throw new IllegalArgumentException("정원을 초과했습니다.");
        }

        student.getEnrolledCourses().getEnrolledCourses().putIfAbsent(getCurrentSemester(), new Courses(new ArrayList<>()));
        student.getEnrolledCourses().getEnrolledCourses().get(getCurrentSemester()).getCourses().add(course);
    }

    public String getCurrentSemester() {
        StringBuilder sb  = new StringBuilder();
        LocalDateTime current = LocalDateTime.now();
        sb.append(current.getYear()).append("-").append(current.getMonthValue() / 8 + 1);

        return sb.toString();
    }

    public Students getStudents() {
        return students;
    }

    public Courses getCourses() {
        return courses;
    }
}
