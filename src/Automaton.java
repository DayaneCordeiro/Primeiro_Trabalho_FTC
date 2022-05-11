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

        for (int i = 0; i < this.states.size(); i++) {
            for (int j = 0; j < this.transitions.size(); j++) {

                if (Objects.equals(this.transitions.get(j).fromState, this.states.get(i).id)) {
                    // verifica se já existe o valor de transição saindo do estado
                    String transitionValue = this.transitions.get(j).valueRead;

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

    public void convertToAFD() {
        // to do
        // retornar novo automato
    }
}
