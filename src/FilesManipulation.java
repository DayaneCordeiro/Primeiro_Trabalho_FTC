import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FilesManipulation {
    public static List<State> reader(String path) throws IOException {
        BufferedReader buffer = new BufferedReader(new FileReader(path));

        String id          = null;
        String name        = null;
        boolean isInitial  = false;
        boolean isFinal    = false;

        String line        = "";
        List<State> states = new ArrayList<State>();

        while (true) {
            if (line != null) {
                if (line.contains("<state")) {
                    int idIndex        = line.indexOf("id=");
                    int nameStartIndex = line.indexOf("name=");
                    int nameEndIndex   = line.indexOf(">");

                    id = line.substring((idIndex + 4), (nameStartIndex - 2));
                    name = line.substring((nameStartIndex + 6), (nameEndIndex - 1));
                }

                if (line.contains("<initial/>")) {
                    isInitial = true;
                }

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
                }
            } else {
                break;
            }

            line = buffer.readLine();
        }

        buffer.close();

        return states;
    }
}
