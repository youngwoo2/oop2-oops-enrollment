package enrollment.server.constants;

public enum Enrollment {
    MAX_CREDITS(18);
    private final int value;

    Enrollment(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
