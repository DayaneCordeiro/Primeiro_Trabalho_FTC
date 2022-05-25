public class FileHelper {
    final String OUTPUT_PATH = "output/AFD.jff";

    final String HEADER =
            "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><!--Created with JFLAP 6.4.--><structure>&#13;\n" +
	        "    <type>fa</type>&#13;\n" +
	        "    <automaton>&#13;\n" +
		    "        <!--The list of states.-->&#13;";

    final String COORDINATE_x = "           <x>0.0</x>&#13;";

    final String COORDINATE_Y = "           <y>0.0</y>&#13;";

    final String END_STATE = "      </state>&#13;";

    final String INITIAL_FLAG = "            <initial/>&#13;";

    final String FINAL_TAG = "           <final/>&#13;";

    final String TRANSITIONS_HEADER = "      <!--The list of transitions.-->&#13;";

    final String START_TRANSITION = "        <transition>&#13;";

    final String END_TRANSITION = "         </transition>&#13;";

    final String FOOTER = "    </automaton>&#13;\n" +
                            "</structure>";
}
