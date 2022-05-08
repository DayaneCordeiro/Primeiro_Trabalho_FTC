public class Transition {
    public String fromState;
    public String toState;
    public String valueRead;

    public Transition(String fromState, String toState, String valueRead) {
        this.fromState = fromState;
        this.toState = toState;
        this.valueRead = valueRead;
    }
}
