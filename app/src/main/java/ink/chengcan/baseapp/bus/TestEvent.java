package ink.chengcan.baseapp.bus;

public class TestEvent {

    private String message;

    public TestEvent(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
