import java.io.IOException;
import java.util.*;

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

        int stateIndexControl = 1;

        int stateIndex = 0;

        while (stateIndex < finalAutomaton.states.size()) {
            String[] labels = State.getAutomatonStateLabels(finalAutomaton.states.get(stateIndex).label);
            transitionsToZero.clear();
            transitionsToOne.clear();

            // tratando as transições com 0
            for (String currentLabel : labels) {
                for (Transition transition : this.transitions) {
                    if (Objects.equals(transition.fromState, currentLabel)
                            && Objects.equals(transition.valueRead, "0")) {

                        if (!transitionsToZero.contains(transition.toState)) {
                            transitionsToZero.add(transition.toState);
                        }

                        System.out.println("=========== inicio");
                        for (int i = 0; i < transitionsToZero.size(); i++) {
                            System.out.println("iteracao: " + i);
                            System.out.println(String.valueOf(transitionsToZero.get(i)));
                        }
                        System.out.println("--------- fim");



                    } else if (Objects.equals(transition.fromState, currentLabel)
                            && Objects.equals(transition.valueRead, "1")) {

                        if (!transitionsToOne.contains(transition.toState)) {
                            transitionsToOne.add(transition.toState);
                        }
                    }
                }
            }

            Collections.sort(transitionsToZero);
            Collections.sort(transitionsToOne);

            for (String s : transitionsToZero) {
                if (labelToZero != null) {
                    labelToZero.insert(0, s + ",");
                } else {
                    labelToZero = new StringBuilder(s);          // se a label ainda estiver vazia, só insere o novo dado
                }
            }

            for (String s : transitionsToOne) {
                if (labelToOne != null) {
                    labelToOne.insert(0, s + ",");
                } else {
                    labelToOne = new StringBuilder(s);          // se a label ainda estiver vazia, só insere o novo dado
                }
            }

            System.out.println("index: " + stateIndex);
            System.out.println("label to zero: " + labelToZero);
            System.out.println("label to one: " + labelToOne);

            // verificando se a label ja existe no automato atual
            if (statesAuxControll.contains(String.valueOf(labelToZero))) {
                String destinyStateId = getAutomatonStateIdByLabel(String.valueOf(labelToZero), finalAutomaton);

                finalAutomaton.transitions.add(new Transition(Integer.toString(stateIndex), destinyStateId, "0"));
            } else {
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
                String destinyStateId = getAutomatonStateIdByLabel(String.valueOf(labelToOne), finalAutomaton);
                finalAutomaton.transitions.add(new Transition(Integer.toString(stateIndex), destinyStateId, "1"));
            } else {
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

            stateIndex++;
            labelToZero = null;
            labelToOne = null;
        }

        // Escrever automato no arquivo
        FilesManipulation.writeAutomatonConvertedFile(finalAutomaton);
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
