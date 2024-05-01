package enrollment.server.model;

import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.Assertions.assertThat;

import enrollment.server.constants.Major;
import enrollment.server.constants.Status;
import enrollment.server.model.course.Course;
import enrollment.server.model.course.Courses;
import enrollment.server.model.course.EnrolledCourses;
import enrollment.server.model.course.Prerequisite;
import enrollment.server.model.student.Student;
import enrollment.server.model.student.Students;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class EnrollmentTest {
    private static Enrollment enrollment;
    @BeforeAll
    static void beforeAll() {
        // given
        enrollment = new Enrollment(new Students(Arrays.asList(new Student(1, 0, "변성일",
                        new EnrolledCourses(new HashMap<>()), Major.COMPUTER, Status.ENROLLED),
                new Student(2, 0, "김홍현", new EnrolledCourses(new HashMap<>()), Major.CHEMISTRY,
                        Status.DROPPING_OUT))),
                new Courses(Arrays.asList(new Course(101234, 25, 3, "자바", "김동현",
                                new Prerequisite(List.of()), Major.COMPUTER, 0),
                        new Course(101111, 25, 3, "스프링", "김동현",
                                new Prerequisite(List.of(101234)), Major.COMPUTER, 0))));
    }

    @DisplayName("수강신청")
    @Test
    void register() {
        // when
        enrollment.register(1, 101234);

        // then
        assertThat(enrollment.getStudents().getStudent(1).getEnrolledCourses().getEnrolledCourses().get("2024-1")
                .getCourse(101234)).isEqualTo(enrollment.getCourses().getCourse(101234));

    }


    // mock 사용불가로 대체
    public String mockGetCurrentSemester(LocalDateTime localDateTime) {
        StringBuilder sb  = new StringBuilder();
        LocalDateTime current = localDateTime;
        sb.append(current.getYear()).append("-").append(current.getMonthValue() / 8 + 1);

        return sb.toString();
    }

    @DisplayName("현재 학기 반환")
    @Test
    void getCurrentSemester() {
        // when, then
        assertThat(enrollment.getCurrentSemester()).isEqualTo("2024-1");
        assertThat(mockGetCurrentSemester(LocalDateTime.of(2023, 7, 1, 1, 1))).isEqualTo("2023-1");
        assertThat(mockGetCurrentSemester(LocalDateTime.of(2023, 8, 1, 1, 1))).isEqualTo("2023-2");
        assertThat(mockGetCurrentSemester(LocalDateTime.of(2023, 12, 1, 1, 1))).isEqualTo("2023-2");
    }
}