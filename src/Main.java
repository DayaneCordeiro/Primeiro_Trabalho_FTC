import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        FileHelper fileHelper = new FileHelper();

        List<State> states = FilesManipulation.statesReader(fileHelper.INPUT_PATH);
        List<Transition> transitions = FilesManipulation.transitionsReader(fileHelper.INPUT_PATH);

        Automaton automaton = new Automaton((ArrayList<State>) states, (ArrayList<Transition>) transitions);

        // Se já for AFD, retorna uma cópia do arquivo na saída
        if (automaton.verifyIfAlreadyAFD()) {
            FilesManipulation.copySameFileToOutput(fileHelper.INPUT_PATH);
        }

        // Se for AFN converte e escreve no arquivo de saída
        automaton.convertToAFD();
    }
}