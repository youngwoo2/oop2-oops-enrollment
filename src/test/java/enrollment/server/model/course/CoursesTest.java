package enrollment.server.model.course;

import static org.assertj.core.api.Assertions.assertThat;

import enrollment.server.constants.Major;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CoursesTest {
    @DisplayName("과목 id로 Courses에서 과목찾기")
    @Test
    void getCourse() {
        // given
        Courses courses = new Courses(Arrays.asList(new Course(101234, 25, 3, "자바", "김동현",
                        new Prerequisite(List.of()), Major.COMPUTER, 0),
                new Course(101111, 25, 3, "스프링", "김동현",
                        new Prerequisite(List.of(101234)), Major.COMPUTER, 0)));
        // when, then
        assertThat(courses.getCourse(101234)).isEqualTo(courses.getCourses().get(0));
    }

}