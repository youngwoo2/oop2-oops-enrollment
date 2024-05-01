package enrollment.server.model;


import static enrollment.server.constants.Major.COMPUTER;
import static enrollment.server.constants.Status.ENROLLED;
import static org.assertj.core.api.Assertions.assertThat;

import enrollment.server.model.course.EnrolledCourses;
import java.util.ArrayList;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class EnrollmentTest {

    @DisplayName("수강신청기간 체크")
    @Test
    void checkPeriod(){
        // given
        Student student = new Student(1,0,"변성일",new EnrolledCourses(Map.of("2024-1", new ArrayList<>())), COMPUTER, ENROLLED);

        // when, then
        assertThat(checkPeriod()).isEqulTo(true);
    }
}