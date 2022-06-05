public class FileHelper {
    final String OUTPUT_PATH = "output/AFD.jff";
    final String INPUT_PATH = "test_JFlap_AFN.jff";

    final String HEADER =
            "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><!--Created with JFLAP 6.4.--><structure>&#13;\n" +
	        "    <type>fa</type>&#13;\n" +
	        "    <automaton>&#13;\n" +
		    "        <!--The list of states.-->&#13;";

    final String COORDINATE_x = "\n            <x>0.0</x>&#13;";

    final String COORDINATE_Y = "\n            <y>0.0</y>&#13;";

    final String END_STATE = "\n        </state>&#13;";

    final String INITIAL_FLAG = "\n            <initial/>&#13;";

    final String FINAL_FLAG = "\n            <final/>&#13;";

    final String TRANSITIONS_HEADER = "\n        <!--The list of transitions.-->&#13;";

    final String START_TRANSITION = "\n        <transition>&#13;";

    final String END_TRANSITION = "\n        </transition>&#13;";

    final String FOOTER = "\n    </automaton>&#13;\n" +
                            "</structure>";
}
