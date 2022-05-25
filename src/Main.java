import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {
        String path = "test_JFlap_AFN.jff";

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

//        Automaton automaton = new Automaton((ArrayList<State>) states, (ArrayList<Transition>) transitions);
//
//        // Se já for AFD, retorna uma cópia do arquivo na saída
//        if (automaton.verifyIfAlreadyAFD()) {
//            FilesManipulation.copySameFileToOutput(path);
//        }
//
//        // Se for AFN converter
//        automaton.convertToAFD();

        String state1 = "q0";
        String state2 = "q1";

        String transition1 = "0";
        String transition2 = "1";
        String transition3 = "0";
        String transition4 = "1";

        String toState1 = "q0";
        String toState2 = "q0";
        String toState3 = "q1";


        Map map = new HashMap();
        Map transitionsMap = new HashMap();

//        ArrayList<String> testeArray = new ArrayList<String>();
//        testeArray.add(transition1);
//        testeArray.add(transition2);
//
//        ArrayList<String> testeArray2 = new ArrayList<String>();
//
//        testeArray.add(0, toState1);
//        testeArray.add(0, toState2);

        ArrayList<ArrayList<String>> matriz = new ArrayList<>();



        map.put(state1, testeMaluco);
        map.put(state2, transition3);

        System.out.println(map);


    }
}