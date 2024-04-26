package enrollment.server.constants;

public enum College {
    ENGINEERING("공과대학"),
    LIBERAL_ARTS("인문대학"),
    NATURAL_SCIENCES("자연과학대학"),
    ARTS_AND_PHYSICAL_EDUCATION("예체능대학");

    private final String name;

    College(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
