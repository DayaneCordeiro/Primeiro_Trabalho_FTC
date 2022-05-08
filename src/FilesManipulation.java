import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FilesManipulation {
    public static List<State> statesReader(String path) throws IOException {
        BufferedReader buffer = new BufferedReader(new FileReader(path));

        String id          = null;
        String name        = null;
        boolean isInitial  = false;
        boolean isFinal    = false;

        String line        = "";
        List<State> states = new ArrayList<State>();

        while (true) {
            if (line != null) {
                // Lê o começo de um estado, coleta o id e o nome
                if (line.contains("<state")) {
                    int idIndex        = line.indexOf("id=");
                    int nameStartIndex = line.indexOf("name=");
                    int nameEndIndex   = line.indexOf(">");

                    id = line.substring((idIndex + 4), (nameStartIndex - 2));
                    name = line.substring((nameStartIndex + 6), (nameEndIndex - 1));
                }

                // Verifica se é o estado inicial
                if (line.contains("<initial/>")) {
                    isInitial = true;
                }

                // Verifica se é o estado final
                if (line.contains("<final/>")) {
                    isFinal = true;
                }

                // Significa que acabou de ler o estado então cria um objeto
                if (line.contains("</state>")) {
                    states.add(new State(
                            id,
                            name,
                            isInitial,
                            isFinal
                    ));

                    // Volta para os valores originais para não gerar erros
                    isInitial = false;
                    isFinal   = false;
                }
            } else {
                break;
            }

            line = buffer.readLine();
        }

        buffer.close();

        return states;
    }

    public static List<Transition> transitionsReader(String path) throws IOException {
        BufferedReader buffer = new BufferedReader(new FileReader(path));

        String fromState = null;
        String toState   = null;
        String valueRead = null;

        String line        = "";
        List<Transition> transitions = new ArrayList<Transition>();

        while (true) {
            if (line != null) {

                // Coleta o from de uma transição
                if (line.contains("<from>")) {
                    int fromIndexStart = line.indexOf("<from>");
                    int fromIndexEnd   = line.indexOf("</from>");

                    fromState = line.substring((fromIndexStart + 6), (fromIndexEnd));
                }

                // Coleta o to de uma transição
                if (line.contains("<to>")) {
                    int toIndexStart = line.indexOf("<to>");
                    int toIndexEnd   = line.indexOf("</to>");

                    toState = line.substring((toIndexStart + 4), (toIndexEnd));
                }

                // Coleta o read de uma transição
                if (line.contains("<read>")) {
                    int readIndexStart = line.indexOf("<read>");
                    int readIndexEnd   = line.indexOf("</read>");

                    valueRead = line.substring((readIndexStart + 6), (readIndexEnd));

                    System.out.println(valueRead);
                }

                // Significa que acabou de ler a transição então cria um objeto
                if (line.contains("</transition>")) {
                    transitions.add(new Transition(
                            fromState,
                            toState,
                            valueRead
                    ));
                }
            } else {
                break;
            }

            line = buffer.readLine();
        }

        buffer.close();

        return transitions;
    }
}
