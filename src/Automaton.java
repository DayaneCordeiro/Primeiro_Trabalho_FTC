import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class Automaton {
    public ArrayList<State> states = new ArrayList<State>();
    public ArrayList<Transition> transitions = new ArrayList<Transition>();

    public Automaton(ArrayList<State> states, ArrayList<Transition> transitions) {
        this.states = states;
        this.transitions = transitions;
    }

    public Automaton() {
    }

    public boolean verifyIfAlreadyAFD() {
        // Algoritmo para identificar se já é um AFD
        // Conhecer os estados
        // Conhecer as transições
        // Verificar se existem mais de uma transição saindo de um estado com um mesmo valor
        // Se não houver é AFD -> retorna o mesmo arquivo recebido

        List<String> readValues = new ArrayList<String>();

        for (State state : this.states) {
            for (Transition transition : this.transitions) {

                if (Objects.equals(transition.fromState, state.id)) {
                    // verifica se já existe o valor de transição saindo do estado
                    String transitionValue = transition.valueRead;

                    if (readValues.contains(transitionValue)) {
                        return false;
                    }

                    readValues.add(transitionValue);
                }
            }

            readValues.clear();
        }

        return true;
    }

    public void convertToAFD() throws IOException {
        Automaton finalAutomaton = new Automaton();

        ArrayList<String> statesAuxControll = new ArrayList<String>();

        finalAutomaton.states.add(this.states.get(0));
        finalAutomaton.states.get(0).label = "0";

        statesAuxControll.add("0");

        ArrayList<String> transitionsToZero = new ArrayList<String>();
        ArrayList<String> transitionsToOne = new ArrayList<String>();

        StringBuilder labelToZero = null;
        StringBuilder labelToOne = null;

        String zeroDestinyState;
        String oneDestinyState;

        int stateIndexControl = 1;

        Iterator<State> currentState = finalAutomaton.states.iterator();
        int stateIndex = 0;

        while (currentState.hasNext()) {
            System.out.println(stateIndex);

            // tratando as transições com 0
            for (Transition transition : this.transitions) {
                if (Objects.equals(transition.fromState, finalAutomaton.states.get(stateIndex).id)
                        && Objects.equals(transition.valueRead, "0")) {
                    System.out.println("primeiro if");
                    transitionsToZero.add(transition.toState);

                    if (labelToZero != null) {
                        labelToZero.insert(0, transition.toState + ",");
                    } else {
                        labelToZero = new StringBuilder(transition.toState);          // se a label ainda estiver vazia, só insere o novo dado
                    }
                } else if (Objects.equals(transition.fromState, finalAutomaton.states.get(stateIndex).id)
                        && Objects.equals(transition.valueRead, "1")) {
                    System.out.println("primeiro else if");
                    transitionsToOne.add(transition.toState);

                    if (labelToOne != null) {
                        labelToOne.insert(0, transition.toState + ",");
                    } else {
                        labelToOne = new StringBuilder(transition.toState);          // se a label ainda estiver vazia, só insere o novo dado
                    }
                }
            }

            System.out.println("label to zero: " + String.valueOf(labelToZero));
            System.out.println("label to um: " + String.valueOf(labelToOne));

            // verificando se a label ja existe no automato atual
            if (statesAuxControll.contains(String.valueOf(labelToZero))) {
                System.out.println("if do zero");
                String destinyStateId = getAutomatonStateIdByLabel(String.valueOf(labelToZero), finalAutomaton);

                System.out.println("destinyStateId do zero: " + destinyStateId);

                finalAutomaton.transitions.add(new Transition(Integer.toString(stateIndex), destinyStateId, "0"));
            } else {
                System.out.println("else do zero");
                boolean isFinal = verifyIfIsFinalState(transitionsToZero);

                // cria o novo estado
                finalAutomaton.states.add(new State(Integer.toString(stateIndexControl),
                        "q" + stateIndexControl,
                        false,
                        isFinal
                ));

                finalAutomaton.states.get(stateIndexControl).setLabel(String.valueOf(labelToZero));

                // cria a nova transição
                finalAutomaton.transitions.add(new Transition(Integer.toString(stateIndex), Integer.toString(stateIndexControl), "0"));

                stateIndexControl++;
                statesAuxControll.add(String.valueOf(labelToZero));
            }

            if (statesAuxControll.contains(String.valueOf(labelToOne))) {
                System.out.println("if do um");
                String destinyStateId = getAutomatonStateIdByLabel(String.valueOf(labelToOne), finalAutomaton);
                System.out.println("destinyStateId do um: " + destinyStateId);

                finalAutomaton.transitions.add(new Transition(Integer.toString(stateIndex), destinyStateId, "1"));
            } else {
                System.out.println("else do um");
                boolean isFinal = verifyIfIsFinalState(transitionsToOne);

                // cria o novo estado
                finalAutomaton.states.add(new State(Integer.toString(stateIndexControl),
                        "q" + stateIndexControl,
                        false,
                        isFinal
                ));

                finalAutomaton.states.get(stateIndexControl).setLabel(String.valueOf(labelToOne));

                // cria a nova transição
                finalAutomaton.transitions.add(new Transition(Integer.toString(stateIndex), Integer.toString(stateIndexControl), "1"));

                stateIndexControl++;
                statesAuxControll.add(String.valueOf(labelToOne));
            }

            System.out.println("estados ================");

            System.out.println("id: " + finalAutomaton.states.get(0).id);
            System.out.println("label: " + finalAutomaton.states.get(0).label);
            System.out.println("isInitial: " + finalAutomaton.states.get(0).isInitial);
            System.out.println("isFinal: " + finalAutomaton.states.get(0).isFinal);
            System.out.println("name: " + finalAutomaton.states.get(0).name);

            if (stateIndex > 0) {
                System.out.println("id: " + finalAutomaton.states.get(1).id);
                System.out.println("label: " + finalAutomaton.states.get(1).label);
                System.out.println("isInitial: " + finalAutomaton.states.get(1).isInitial);
                System.out.println("isFinal: " + finalAutomaton.states.get(1).isFinal);
                System.out.println("name: " + finalAutomaton.states.get(1).name);
            }

            System.out.println("fim estados ================");

            System.out.println("transições ================");

            System.out.println("fromState: " + finalAutomaton.transitions.get(0).fromState);
            System.out.println("toState: " + finalAutomaton.transitions.get(0).toState);
            System.out.println("valueRead: " + finalAutomaton.transitions.get(0).valueRead);
            System.out.println("================");
            if (stateIndex > 0) {
                System.out.println("fromState: " + finalAutomaton.transitions.get(1).fromState);
                System.out.println("toState: " + finalAutomaton.transitions.get(1).toState);
                System.out.println("valueRead: " + finalAutomaton.transitions.get(1).valueRead);
                System.out.println("================");
                System.out.println("fromState: " + finalAutomaton.transitions.get(2).fromState);
                System.out.println("toState: " + finalAutomaton.transitions.get(2).toState);
                System.out.println("valueRead: " + finalAutomaton.transitions.get(2).valueRead);
            }

            System.out.println("fim das transições ================");

            System.out.println("==================== fim do loop ====================");

            stateIndex++;
            labelToZero = null;
            labelToOne = null;
        }
    }

    public boolean verifyIfIsFinalState(ArrayList<String> stateIds) {
        for (String currentState : stateIds) {
            for (State state : this.states) {
                if (Objects.equals(currentState, state.id)) {
                    if (state.isFinal) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    public String getAutomatonStateIdByLabel(String label, Automaton automaton) {
        for (State state : automaton.states) {
            if (Objects.equals(state.label, label)) {
                return state.id;
            }
        }

        return null;
    }
}
