package labs;

import JavaTeacherLib.MyLang;
import JavaTeacherLib.Node;

import java.util.*;
import java.util.stream.Stream;

public class lab1 {
    private static final String LANG_PATH = "Examples/All_Algorithm/C0_GR.txt";
    private static final int LLK = 1;

    public static void main(String[] args) {
        MyLang lang = new MyLang(LANG_PATH, LLK);

        if (lang.isCreate()) {
            int[] epsilon = createEpsilonNonterminals(lang);
            int[] internal_epsilon = lang.createEpsilonNonterminals();

            System.out.println("My epsilon-nonterminals length: " + epsilon.length);
            System.out.println("Internal: " + internal_epsilon.length);

//            lang.setEpsilonNonterminals (epsilon);
//            lang.printEpsilonNonterminals();
        }
    }

    static int[] createEpsilonNonterminals(MyLang lang) {

        LinkedList<Node> language = lang.getLanguarge();

        // get all epsilon-rules
        int[] S = language.parallelStream()
                .filter(rule -> rule.getRoole().length == 1)
                .mapToInt(rule -> rule.getRoole()[0])
                .distinct()
                .toArray();

        int old_length;

        do {
            final int[] old_S = S.clone();
            old_length = old_S.length;
            S = language.parallelStream()
                    .filter(rule -> rightPartConsistsOf(rule.getRoole(), old_S))
                    .mapToInt(rule -> rule.getRoole()[0])
                    .distinct()
                    .toArray();

        } while (S.length != old_length);

        return S;
    }

    static boolean rightPartConsistsOf(int[] rooles, int[] alphabet) {
        if (rooles.length == 1)
            return true;

        for (int i = 1; i < rooles.length; i++) {
            boolean in_alphabet = false;
            for (int a : alphabet) {
                if (a == rooles[i])
                    in_alphabet = true;
            }
            if (!in_alphabet)
                return false;
        }

        return true;
    }
}

