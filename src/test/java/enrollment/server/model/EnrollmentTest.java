package enrollment.server.model;

import static org.assertj.core.api.Assertions.assertThat;

import enrollment.server.constants.Major;
import enrollment.server.constants.Status;
import enrollment.server.model.course.EnrolledCourses;
import java.util.Arrays;
import java.util.HashMap;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class EnrollmentTest {

    @DisplayName("수강신청")
    @Test
    void register() {
        // given
        Enrollment enrollment = new Enrollment(Arrays.asList(new Student(1, 0, "변성일",
                new EnrolledCourses(new HashMap<>()), Major.COMPUTER, Status.ENROLLED),
                new Student(2, 0, "김홍현", new EnrolledCourses(new HashMap<>()), Major.CHEMISTRY,
                        Status.DROPPING_OUT)));
        // when, then
        assertThat(enrollment.register(courseId)).isTure();
//        assertThat(enrollment.getStudent(1).)

    }
}