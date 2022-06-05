import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FilesManipulation {
    public static List<State> statesReader(String path) throws IOException {
        BufferedReader buffer = new BufferedReader(new FileReader(path));

        String id = null;
        String name = null;
        boolean isInitial = false;
        boolean isFinal = false;

        String line = "";
        List<State> states = new ArrayList<State>();

        while (true) {
            if (line != null) {
                // Lê o começo de um estado, coleta o id e o nome
                if (line.contains("<state")) {
                    int idIndex = line.indexOf("id=");
                    int nameStartIndex = line.indexOf("name=");
                    int nameEndIndex = line.indexOf(">");

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
                    isFinal = false;
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
        String toState = null;
        String valueRead = null;

        String line = "";
        List<Transition> transitions = new ArrayList<Transition>();

        while (true) {
            if (line != null) {

                // Coleta o from de uma transição
                if (line.contains("<from>")) {
                    int fromIndexStart = line.indexOf("<from>");
                    int fromIndexEnd = line.indexOf("</from>");

                    fromState = line.substring((fromIndexStart + 6), (fromIndexEnd));
                }

                // Coleta o to de uma transição
                if (line.contains("<to>")) {
                    int toIndexStart = line.indexOf("<to>");
                    int toIndexEnd = line.indexOf("</to>");

                    toState = line.substring((toIndexStart + 4), (toIndexEnd));
                }

                // Coleta o read de uma transição
                if (line.contains("<read>")) {
                    int readIndexStart = line.indexOf("<read>");
                    int readIndexEnd = line.indexOf("</read>");

                    valueRead = line.substring((readIndexStart + 6), (readIndexEnd));
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

    public static void copySameFileToOutput(String path) throws IOException {
        File originFile = new File(path);

        FileReader file = new FileReader(originFile);

        BufferedReader bufferedReader = new BufferedReader(file);

        StringBuilder buffer = new StringBuilder();

        String line = "";

        while ((line = bufferedReader.readLine()) != null) {
            buffer.append(line).append("\n");
        }

        file.close();
        bufferedReader.close();

        File destinyFile = new File("output/AFD.jff");

        FileWriter writer = new FileWriter(destinyFile);

        writer.write(buffer.toString());
        writer.flush();
        writer.close();
    }

    public static void writeAutomatonConvertedFile(Automaton automaton) throws IOException {
        FileHelper fileHelper = new FileHelper();

        File destinyFile = new File(fileHelper.OUTPUT_PATH);

        FileWriter writer = new FileWriter(destinyFile);

        // Escrevendo dados no arquivo de saída
        writer.write(fileHelper.HEADER);

        for (State state : automaton.states) {
            writer.write("\n        <state id=\"" + state.id + "\" name=\"" + state.name + "\">&#13;");
            writer.write(fileHelper.COORDINATE_x);
            writer.write(fileHelper.COORDINATE_Y);
            writer.write("\n            <label>" + state.label + "</label>&#13;");
            writer.write((state.isInitial) ? fileHelper.INITIAL_FLAG : "");
            writer.write((state.isFinal) ? fileHelper.FINAL_FLAG : "");
            writer.write(fileHelper.END_STATE);
        }

        writer.write(fileHelper.TRANSITIONS_HEADER);

        for (Transition transition : automaton.transitions) {
            writer.write(fileHelper.START_TRANSITION);
            writer.write("\n            <from>" + transition.fromState + "</from>&#13;");
            writer.write("\n            <to>" + transition.toState + "</to>&#13;");
            writer.write("\n            <read>" + transition.valueRead + "</read>&#13;");
            writer.write(fileHelper.END_TRANSITION);
        }

        writer.write(fileHelper.FOOTER);

        writer.flush();
        writer.close();
    }
}

