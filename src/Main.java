import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        String path = "test_JFlap_AFD.jff";

        List<State> states = FilesManipulation.statesReader(path);
        List<Transition> transitions = FilesManipulation.transitionsReader(path);

//        for (int i = 0; i < states.size(); i++) {
//            System.out.println(states.get(i).id);
//            System.out.println(states.get(i).name);
//            System.out.println(states.get(i).isInitial);
//            System.out.println(states.get(i).isFinal);
//            System.out.println("-----");
//        }
//
//        for (int i = 0; i < transitions.size(); i++) {
//            System.out.println(transitions.get(i).fromState);
//            System.out.println(transitions.get(i).toState);
//            System.out.println(transitions.get(i).valueRead);
//            System.out.println("-----");
//        }

        Automaton automaton = new Automaton((ArrayList<State>) states, (ArrayList<Transition>) transitions);

        // Se já for AFD, retorna uma cópia do arquivo na saída
        if (automaton.verifyIfAlreadyAFD()) {
            FilesManipulation.copySameFileToOutput(path);
        }

        // Se for AFN converter
        automaton.convertToAFD();
    }
}