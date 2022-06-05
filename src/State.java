import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class State {
    public String id;
    public String name;
    public boolean isInitial;
    public boolean isFinal;
    public String label;

    public State(String id, String name, boolean isInitial, boolean isFinal) {
        this.id = id;
        this.name = name;
        this.isInitial = isInitial;
        this.isFinal = isFinal;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public static String[] getAutomatonStateLabels(@NotNull String label) {
        String[] labels = label.split(",");

        Arrays.sort(labels);

        return labels;
    }
}
