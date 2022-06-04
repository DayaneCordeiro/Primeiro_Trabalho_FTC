import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Automaton {
    public ArrayList<State> states = new ArrayList<State>();
    public ArrayList<Transition> transitions = new ArrayList<Transition>();

    public Automaton(ArrayList<State> states, ArrayList<Transition> transitions) {
        this.states = states;
        this.transitions = transitions;
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
        /**
         * Para o automato que eu tenho usado de exemplo:
         * - LÊ O ESTADO A
         * - A com 0 vai pra onde?
         *      - Vai pra A mesmo.
         *          ==> [A] já existe? se não, cria um estado [A] se sim só insere a transição que vai ser criada
         *          ==> Cria uma transição em [A] com 0 indo para [A]
         *
         * - A com 1 vai pra onde?
         *      - Vai pra A e pra B.
         *          ==> [AB] já existe? se não, cria um estado [AB] se sim só insere a transição que vai ser criada
         *          ==> Cria uma transição com 1 de [A] para [AB]
         *
         * - LÊ O ESTADO [AB]
         * - [AB] vai pra onde com 0?
         *      - A com 0 vai pra onde?
         *          - Vai para [A].
         *      - B com 0 vai pra onde?
         *          - Vai para [C].
         *      ==> [AC] já existe? se não, cria um estado [AC] se sim só insere a transição que vai ser criada
         *      ==> cria uma transição em [AB] com 0 indo para [AC]
         *
         *      - A com 1 vai pra onde?
         *          - Vai para [AB].
         *      - B com 1 vai pra onde?
         *          - Vai para [C].
         *      ==> cria um estado [ABC]
         *      ==> cria uma transição em [AB] com 1 indo para [ABC]
         *
         * - LÊ O ESTADO [ABC]
         * - [ABC] com 0 vai pra onde?
         *      - A com 0 vai pra onde?
         *          - Vai para [A].
         *      - B com 0 vai pra onde?
         *          - Vai para [C].
         *      - C com zero vai pra onde?
         *          - Vai para [D].
         *      ==> [ACD] já existe? se não, cria um estado [ACD] se sim só insere a transição que vai ser criada
         *      ==> cria uma transição em [ABC] com 0 indo para [ACD]
         *
         * - [ABC] com 1 vai pra onde?
         *      - A com 1 vai pra onde?
         *          - Vai para [AB].
         *      - B com 1 vai pra onde?
         *          - Vai para [C].
         *      - C com 1 vai pra onde?
         *          - Vai pra [D].
         *      ==> [ABCD] já existe? se não, cria um estado [ABCD] se sim só insere a transição que vai ser criada
         *      ==> cria uma transição em [ABC] com 1 indo para [ABCD]
         *
         *      ... só seguir a mesma lógica
         */

        // Pega os estados incial e finais
        String automatonInitialState = getAutomatonInitialState(this);
        ArrayList<String> automatonFinalStates = getAutomatonFinalStates(this);

        // criação da estrutura que irá conter os estados do automato não deterministico
        ArrayList<String> currentStatesControl = new ArrayList<String>();
        ArrayList<String> nextStatesControl = new ArrayList<String>();
        ArrayList<String> labelsControl = new ArrayList<String>();
        ArrayList<Transition> currentTransitionsControl = new ArrayList<Transition>();

        ArrayList<State> finalStates = new ArrayList<State>();
        ArrayList<Transition> finalTransitions = new ArrayList<Transition>();

        StringBuilder label = null;
        int newStateIndex = 1;

        boolean isInitialState = false;
        boolean isFinalState = false;

        // Pega o primeiro estado do automato e insere na variável de controle
        currentStatesControl.add(this.states.get(0).id);

        while (!currentStatesControl.isEmpty()) {
            // percorrer todos os estados atuais ([A] - tem só um estado [ABC] - tem mais de um estado pra avaliar)

            // faz primeiro para o 0 <- depois é melhor jogar pra uma função
            for (String currentState : currentStatesControl) {
                // percorrer as transições procurando todas em que o fromState é esse e ver se é 0
                for (Transition transition : this.transitions) {                        // percorre as transições do automato lido
                    if (transition.fromState == currentState) {                         // se o estado de origem do automato for igual ao estado atual
                        if (transition.valueRead == "0") {                              // se o valor de transição for o 0
                            nextStatesControl.add(transition.toState);                  // adiciona o estado para onde está indo na variável de controle

                            if (label != null) {                                        // se a label ja tiver conteúdo, concatena no início dela
                                label.insert(0, transition.toState + ",");
                            } else {
                                label = new StringBuilder(transition.toState);          // se a label ainda estiver vazia, só insere o novo dado
                            }

                            if (automatonInitialState == currentState) {
                                isInitialState = true;
                            }

                            if (automatonFinalStates.contains(currentState)) {
                                isFinalState = true;
                            }

                        }
                    }
                }
            }

            // cria o estado de origem se não existir (vai acontecer apenas na primeira iteração)
            if (finalStates.isEmpty()) {
                State state = new State("0",
                        "q0",
                        this.states.get(0).isInitial,
                        this.states.get(0).isFinal
                );

                state.setLabel(this.states.get(0).id);

                finalStates.add(state);
            }

            // insere o novo estado quando não existe
            if (!labelsControl.contains(String.valueOf(label))) {
                State state = new State(String.valueOf(newStateIndex),
                        "q" + newStateIndex,
                        isInitialState,
                        isFinalState
                );

                state.setLabel(String.valueOf(label));

                finalStates.add(state);

                labelsControl.add(String.valueOf(label));
                newStateIndex++;
            }



            isInitialState = false;
            isFinalState = false;


        }









//        ArrayList<State> automatonFinalStates = new ArrayList<State>();
//        ArrayList<String> labels = new ArrayList<String>();
//
//        int index = 0;
//
//        currentStatesControl = this.states;
//        currentTransitionsControl = this.transitions;
//
//        while (currentStatesControl.size() > 0) {
//            StringBuilder labelToValueZero = new StringBuilder();
//            StringBuilder labelToValueOne = new StringBuilder();
//
//            // testa onde ele pode ir com o 0
//            for (Transition transition : currentTransitionsControl) {
//                if (Objects.equals(transition.fromState, currentStatesControl.get(0).id)) {
//                    if (Objects.equals(transition.valueRead, "0")) {
//                        if (!labelToValueZero.isEmpty())
//                            labelToValueZero.append(","); // concatenar no inicio pra evitar sobrar vírgula no final
//
//                        labelToValueZero.append(transition.toState); // concatenar no inicio pra evitar sobrar vírgula no final
//
//                        State newState = new State(
//                                Integer.toString(this.states.size() + 1),
//                                "q" + this.states.size() + 1,
//                                false,
//                                false
//                        );
//
//                        newState.setLabel(String.valueOf(labelToValueZero));
//
//                        currentStatesControl.add(newState);
//                    } else if (Objects.equals(transition.valueRead, "1")) {
//                        if (!labelToValueOne.isEmpty())
//                            labelToValueOne.append(",");
//
//                        labelToValueOne.append(transition.toState);
//
//                        State newState = new State(
//                                Integer.toString(this.states.size() + 1),
//                                "q" + this.states.size() + 1,
//                                false,
//                                false
//                        );
//
//                        newState.setLabel(String.valueOf(labelToValueOne));
//
//                        currentStatesControl.add(newState);
//                    }
//                }
//            }
//
//            if (!labelToValueZero.isEmpty()) {
//                if (!labels.contains(String.valueOf(labelToValueZero)))
//                    labels.add(String.valueOf(labelToValueZero));

//                public String id;
//                public String name;
//                public boolean isInitial;
//                public boolean isFinal;
//                public String label;

//                currentStatesControl.add(new State(
//                    this.states.size() + 1,
//                        "q" + this.states.size() + 1,
//                        false,
//                        false,
//                        label
//
//                ));
            }
//
//            if (!labelToValueOne.isEmpty()) {
//                if (!labels.contains(String.valueOf(labelToValueZero)))
//                    labels.add(String.valueOf(labelToValueOne));
//            }
//
//            currentStatesControl.remove(0);
//            index++;
//        }
//
//        for (String label : labels) {
//            System.out.println(label);
//        }
//
//        FilesManipulation filesManipulation = new FilesManipulation();
//        filesManipulation.writeAutomatonConvertedFile();
//    }

    /**
     *
     * @param automaton
     * @return
     */
    public String getAutomatonInitialState(Automaton automaton) {
        for (State state : automaton.states) {
            if (state.isInitial) {
                return state.id;
            }
        }

        return null;
    }

    /**
     *
     * @param automaton
     * @return
     */
    public ArrayList<String> getAutomatonFinalStates(Automaton automaton) {
        ArrayList<String> finalStates = new ArrayList<>();

        for (State state : automaton.states) {
            if (state.isFinal) {
                finalStates.add(state.id);
            }
        }

        return finalStates;
    }
}
