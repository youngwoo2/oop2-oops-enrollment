package enrollment.server.model.course;

import static org.assertj.core.api.Assertions.assertThat;

import enrollment.server.constants.Major;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CourseTest {
    @DisplayName("정원체크")
    @Test
    void checkCapacity() {
        // given
        Course java = new Course(101234, 25, 3, "자바", "김동현",
                new Prerequisite(List.of()), Major.COMPUTER, 0);
        Course jpa = new Course(101111, 25, 3, "스프링", "김동현",
                new Prerequisite(List.of(101234)), Major.COMPUTER, 25);

        // when, then
        assertThat(java.checkCapacity()).isTrue();
        assertThat(jpa.checkCapacity()).isFalse();
    }
}
