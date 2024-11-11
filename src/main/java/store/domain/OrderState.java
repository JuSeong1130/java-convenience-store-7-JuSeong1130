package store.domain;

public enum OrderState {
    SUCCESS("SUCCESS"),
    PENDING("PENDING");

    private final String state;

    OrderState(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }
}
