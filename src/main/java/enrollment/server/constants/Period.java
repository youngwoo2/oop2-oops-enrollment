package enrollment.server.constants;

import java.time.LocalDateTime;

public enum Period {
//    ENROLLMENT("02-06~02-10/09~18", 2, 2, 6, 12, 9, 18-1);
    ENROLLMENT("05-01~05-10/09~24", 5, 5, 1, 10, 9, 24-1);

    private final String period;

    private final int startMonth;
    private final int endMonth;

    private final int startDate;
    private final int endDate;

    private final int startHour;
    private final int endHour;


    Period(String period, int startMonth, int endMonth, int startDate, int endDate, int startHour, int endHour) {
        this.period = period;
        this.startMonth = startMonth;
        this.endMonth = endMonth;
        this.startDate = startDate;
        this.endDate = endDate;
        this.startHour = startHour;
        this.endHour = endHour;
    }

    public static boolean check() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        int month = currentDateTime.getMonthValue();
        int date = currentDateTime.getDayOfMonth();
        int hour = currentDateTime.getHour();

        if (!(month >= ENROLLMENT.startMonth && month <= ENROLLMENT.endMonth)) {
            return false;
        }
        if (!(date >= ENROLLMENT.startDate && date <= ENROLLMENT.endDate)) {
            return false;
        }
        if (!(hour >= ENROLLMENT.startHour && hour <= ENROLLMENT.endHour)) {
            return false;
        }

        return true;
    }
}
