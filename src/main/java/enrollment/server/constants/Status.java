package enrollment.server.constants;

public enum Status {
    GRADUATED("졸업"),
    ENROLLED("재학"),
    LEAVE_OF_ABSENCE("휴학"),
    DROPPING_OUT("자퇴"),
    EXPELLED("제적");

    private final String status;

    Status(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
