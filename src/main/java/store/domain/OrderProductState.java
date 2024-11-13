package store.domain;

public enum OrderProductState {
    SOLVE("SOLVE"),
    GIFT("GIFT"),
    EXCLUSION("EXCLUSION");

    private final String state;

    OrderProductState(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }
}
