package enrollment.server.model.student;

import static org.assertj.core.api.Assertions.assertThat;

import enrollment.server.constants.Major;
import enrollment.server.constants.Status;
import enrollment.server.model.course.Course;
import enrollment.server.model.course.Courses;
import enrollment.server.model.course.EnrolledCourses;
import enrollment.server.model.course.Prerequisite;
import enrollment.server.model.student.Student;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class StudentTest {
    @DisplayName("수강 가능한 학점 체크")
    @ParameterizedTest
    @CsvSource(value = {"0,3,true", "15,3,true", "16,3,false", "17,2,false"})
    void checkCurrentCredits(int currentCredits, int courseCredit, boolean isPossible) {
        // given
        Student student = new Student(1, currentCredits, "변성일", new EnrolledCourses(new HashMap<>()), Major.COMPUTER,
                Status.ENROLLED);
        Course course = new Course(101234, 25, courseCredit, "자바", "김동현",
                new Prerequisite(List.of()), Major.COMPUTER, 0);

        // when, then
        assertThat(student.checkCurrentCredits(course)).isEqualTo(isPossible);
    }

    @DisplayName("선수과목 체크")
    @Test
    void checkPrerequisite() {
        // given
        Course java = new Course(101234, 25, 3, "자바", "김동현",
                new Prerequisite(List.of()), Major.COMPUTER, 0);
        Course jpa = new Course(101111, 25, 3, "스프링", "김동현",
                new Prerequisite(List.of(101234)), Major.COMPUTER, 0);
        Course spring = new Course(102222, 25, 3, "스프링", "김동현",
                new Prerequisite(List.of(101111)), Major.COMPUTER, 0);

        Student student = new Student(1, 0, "변성일",
                new EnrolledCourses(Map.of("2023-1", new Courses(List.of(java)))), Major.COMPUTER,
                Status.ENROLLED);

        // when, then
        assertThat(student.checkPrerequisite(jpa)).isTrue();
        assertThat(student.checkPrerequisite(spring)).isFalse();
    }

    @DisplayName("전공 체크")
    @Test
    void checkMajor() {
        // given
        Student student = new Student(1, 0, "변성일",
                new EnrolledCourses(new HashMap<>()), Major.COMPUTER,
                Status.ENROLLED);
        Course java = new Course(101234, 25, 3, "자바", "김동현",
                new Prerequisite(List.of()), Major.COMPUTER, 0);
        Course english = new Course(201234, 25, 3, "영어", "김민수",
                new Prerequisite(List.of()), Major.ENGLISH, 0);
        // when, then
        assertThat(student.checkMajor(java)).isTrue();
        assertThat(student.checkMajor(english)).isFalse();

    }
}
