package enrollment.server.model.student;

import static org.assertj.core.api.Assertions.assertThat;

import enrollment.server.constants.Major;
import enrollment.server.constants.Status;
import enrollment.server.model.course.EnrolledCourses;
import java.util.Arrays;
import java.util.HashMap;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class StudentsTest {

    @DisplayName("학생id로 Students에서 학생 찾기")
    @Test
    void getStudent() {
        // given
        Students students = new Students(Arrays.asList(new Student(1, 0, "변성일",
                        new EnrolledCourses(new HashMap<>()), Major.COMPUTER, Status.ENROLLED),
                new Student(2, 0, "김홍현", new EnrolledCourses(new HashMap<>()), Major.CHEMISTRY,
                        Status.DROPPING_OUT)));
        // when, then
        assertThat(students.getStudent(1).getId()).isEqualTo(1);
    }
}