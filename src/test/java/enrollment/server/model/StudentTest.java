package enrollment.server.model;

import static org.assertj.core.api.Assertions.assertThat;

import enrollment.server.constants.Major;
import enrollment.server.constants.Status;
import enrollment.server.model.course.EnrolledCourses;
import java.util.HashMap;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class StudentTest {
    @DisplayName("수강 가능한 학점 체크")
    @ParameterizedTest
    @CsvSource(value = {"0,3,true","15,3,true","16,3,false","17,2,false"})
    void checkCurrentCredits(int currentCredits, int courseCredit, boolean isPossible) {
        // given
        Student student = new Student(1, currentCredits, "변성일", new EnrolledCourses(new HashMap<>()), Major.COMPUTER, Status.ENROLLED);

        // when, then
        assertThat(checkCurrentCredits(courseCredit)).isEqulTo(isPossible);
    }
}
