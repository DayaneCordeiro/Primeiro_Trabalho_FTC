import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        String path = "test_JFlap.jff";

        List<State> states = FilesManipulation.statesReader(path);
        List<Transition> transitions = FilesManipulation.transitionsReader(path);

        for (int i = 0; i < states.size(); i++) {
            System.out.println(states.get(i).id);
            System.out.println(states.get(i).name);
            System.out.println(states.get(i).isInitial);
            System.out.println(states.get(i).isFinal);
            System.out.println("-----");
        }

        for (int i = 0; i < transitions.size(); i++) {
            System.out.println(transitions.get(i).fromState);
            System.out.println(transitions.get(i).toState);
            System.out.println(transitions.get(i).valueRead);
            System.out.println("-----");
        }
    }
}