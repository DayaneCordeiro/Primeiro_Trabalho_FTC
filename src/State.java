public class State {
    public String id;
    public String name;
    public boolean isInitial;
    public boolean isFinal;

    public State(String id, String name, boolean isInitial, boolean isFinal) {
        this.id = id;
        this.name = name;
        this.isInitial = isInitial;
        this.isFinal = isFinal;
    }
}
