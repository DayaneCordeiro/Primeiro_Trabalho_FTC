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

    /**
     * Se existir mais de uma transição com o mesmo valor saindo do estado ele reconhece como AFN, caso contrário, a entrada já é AFD
     * @return boolean
     */
    public boolean verifyIfAlreadyAFD() {
        List<String> readValues = new ArrayList<String>();

        for (State state : this.states) {
            for (Transition transition : this.transitions) {

                if (Objects.equals(transition.fromState, state.id)) {
                    // verifica se já existe o valor de transição saindo do estado
                    String transitionValue = transition.valueRead;

                    // Se o valor read estiver duplicado é AFN
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

    /**
     * Converte o automato AFN para AFD e escreve no arquivo de saída
     * @throws IOException
     */
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

            // Percorre cada label do estado verificando onde elas chegam com transição 0 e transição 1
            for (String currentLabel : labels) {
                for (Transition transition : this.transitions) {
                    if (Objects.equals(transition.fromState, currentLabel)
                            && Objects.equals(transition.valueRead, "0")) {

                        // Se a transição ainda não esxistir para 0, escreve no array de controle de transições
                        if (!transitionsToZero.contains(transition.toState)) {
                            transitionsToZero.add(transition.toState);
                        }
                    } else if (Objects.equals(transition.fromState, currentLabel)
                            && Objects.equals(transition.valueRead, "1")) {

                        // Se a transição ainda não esxistir para 1, escreve no array de controle de transições
                        if (!transitionsToOne.contains(transition.toState)) {
                            transitionsToOne.add(transition.toState);
                        }
                    }
                }
            }

            // Ordena o array de controle de transições de zero e um para padronizar e não duplicar labels
            Collections.sort(transitionsToZero);
            Collections.sort(transitionsToOne);

            // Escreve os valores na label das transições com 0
            for (String s : transitionsToZero) {
                if (labelToZero != null) {
                    labelToZero.insert(0, s + ",");
                } else {
                    labelToZero = new StringBuilder(s);
                }
            }

            // Escreve os valores na label das transições com 1
            for (String s : transitionsToOne) {
                if (labelToOne != null) {
                    labelToOne.insert(0, s + ",");
                } else {
                    labelToOne = new StringBuilder(s);
                }
            }

            // Manipulando os dados que foram coletados das transições com o zero
            // Verifica se algum estado do automato final já possui essa label para que não existam estados duplicados
            if (statesAuxControll.contains(String.valueOf(labelToZero))) {
                String destinyStateId = getAutomatonStateIdByLabel(String.valueOf(labelToZero), finalAutomaton);

                // Se a label já existir no automato final, apenas cria uma nova transição para esse estado com o valor 0
                finalAutomaton.transitions.add(new Transition(Integer.toString(stateIndex), destinyStateId, "0"));
            } else {
                boolean isFinal = verifyIfIsFinalState(transitionsToZero);

                // Caso o automato final não tenha nenhum estado com a label do zero, cria-se um novo estado
                finalAutomaton.states.add(new State(Integer.toString(stateIndexControl),
                        "q" + stateIndexControl,
                        false,
                        isFinal
                ));

                // Insere a label do zero no estado que foi criado
                finalAutomaton.states.get(stateIndexControl).setLabel(String.valueOf(labelToZero));

                // Cria a transição para o novo estado com o valor 0
                finalAutomaton.transitions.add(new Transition(Integer.toString(stateIndex), Integer.toString(stateIndexControl), "0"));

                stateIndexControl++;
                statesAuxControll.add(String.valueOf(labelToZero));
            }

            // Manipulando os dados que foram coletados das transições com o um
            // Verifica se algum estado do automato final já possui essa label para que não existam estados duplicados
            if (statesAuxControll.contains(String.valueOf(labelToOne))) {
                String destinyStateId = getAutomatonStateIdByLabel(String.valueOf(labelToOne), finalAutomaton);

                // Se a label já existir no automato final, apenas cria uma nova transição para esse estado com o valor 1
                finalAutomaton.transitions.add(new Transition(Integer.toString(stateIndex), destinyStateId, "1"));
            } else {
                boolean isFinal = verifyIfIsFinalState(transitionsToOne);

                // Caso o automato final não tenha nenhum estado com a label do um, cria-se um novo estado
                finalAutomaton.states.add(new State(Integer.toString(stateIndexControl),
                        "q" + stateIndexControl,
                        false,
                        isFinal
                ));

                // Insere a label do um no estado que foi criado
                finalAutomaton.states.get(stateIndexControl).setLabel(String.valueOf(labelToOne));

                // Cria a transição para o novo estado com o valor 1
                finalAutomaton.transitions.add(new Transition(Integer.toString(stateIndex), Integer.toString(stateIndexControl), "1"));

                stateIndexControl++;
                statesAuxControll.add(String.valueOf(labelToOne));
            }

            stateIndex++;
            labelToZero = null;
            labelToOne = null;
        }

        // Escreve o automato final no arquivo de saída
        FilesManipulation.writeAutomatonConvertedFile(finalAutomaton);
    }

    /**
     * Verifica se o estado atual está entre os estados finais do automato original
     * @param stateIds
     * @return boolean
     */
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

    /**
     * Retorna o id do estado do automato final fazendo uma busca pela sua label
     * @param label
     * @param automaton
     * @return stateID
     */
    public String getAutomatonStateIdByLabel(String label, Automaton automaton) {
        for (State state : automaton.states) {
            if (Objects.equals(state.label, label)) {
                return state.id;
            }
        }

        return null;
    }
}
