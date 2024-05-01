package enrollment.server.constants;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class PeriodTest {

    // check method
    public static boolean mockCheck(LocalDateTime localDateTime) {
        LocalDateTime currentDateTime = localDateTime;
        int month = currentDateTime.getMonthValue();
        int date = currentDateTime.getDayOfMonth();
        int hour = currentDateTime.getHour();

        if (!(month >= 2 && month <= 2)) {
            return false;
        }
        if (!(date >= 6 && date <= 12)) {
            return false;
        }
        if (!(hour >= 9 && hour <= 17)) {
            return false;
        }

        return true;
    }

    @DisplayName("수강신청기간 체크")
    @ParameterizedTest
    @CsvSource(value = {"2024,2,6,9,0,0,true", "2023,2,6,9,0,0,true", "2024,2,6,8,59,59,false",
            "2024,2,6,17,59,59,true", "2024,2,6,18,0,0,false", "2024,2,12,9,0,0,true", "2024,2,13,9,0,0,false"})
    void check(int year, int month, int date, int hour, int minute, int second, boolean isPeriod) {
        // given

        // when, then
        assertThat(mockCheck(LocalDateTime.of(year, month, date, hour, minute, second))).isEqualTo(isPeriod);
    }
}