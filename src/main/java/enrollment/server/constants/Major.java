package enrollment.server.constants;

import static enrollment.server.constants.College.*;

public enum Major {
    COMPUTER(ENGINEERING, "컴퓨터공학과"), // 11
    MECHANICAL(ENGINEERING, "기계공학과"), // 12

    ENGLISH(LIBERAL_ARTS, "영어학과"), // 21
    HISTORY(LIBERAL_ARTS, "역사학과"), // 22

    PHYSICS(NATURAL_SCIENCES, "물리학과"), // 31
    CHEMISTRY(NATURAL_SCIENCES, "화학과"), // 32

    FINE_ARTS(ARTS_AND_PHYSICAL_EDUCATION, "미술학과"), // 41
    PHYSICAL_EDUCATION(ARTS_AND_PHYSICAL_EDUCATION, "체육학과"); //42

    private final College college;
    private final String name;

    Major(College college, String name) {
        this.college = college;
        this.name = name;
    }

    public College getCollege() {
        return college;
    }

    public String getName() {
        return name;
    }
}
