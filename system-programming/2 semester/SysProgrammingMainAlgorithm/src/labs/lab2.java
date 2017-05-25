package labs;

import JavaTeacherLib.LlkContext;
import JavaTeacherLib.MyLang;
import JavaTeacherLib.Node;

import java.util.*;
import java.util.stream.Collectors;

public class lab2 {
    private static final String LANG_PATH = "Examples/All_Algorithm/LL0_GR.txt";
    private static final int LLK = 1;

    public static void main(String[] args){
        MyLang lang = new MyLang(LANG_PATH, LLK);

        if(lang.isCreate()) {
            boolean isLL1 = checkLL1(lang);
            if(isLL1)
                System.out.println("LL1");
            else
                System.out.println("Not LL1");
        }
    }

    private static boolean checkLL1(MyLang lang){
        LinkedList<Node> language = lang.getLanguarge();

        LlkContext[] firstK = lang.firstK();
        List<Integer> nonterminals = Arrays.stream(lang.getNonTerminals()).boxed().collect(Collectors.toList());

        // sort by left nonterminal
        Map<Integer, List<Node>> language_groups = language.stream()
                .collect(Collectors.groupingBy(l -> l.getRoole()[0]));

        boolean has_collision = false;

        outer:
        for(Map.Entry<Integer, List<Node>> entry: language_groups.entrySet()){
            List<Node> rules = entry.getValue();

            List<LlkContext> contexts = new LinkedList<>();

            for(Node rule: rules){
                if (rule.getRoole().length == 1) {
                    LlkContext emptyContext = new LlkContext();
                    emptyContext.addWord(new int[0]);
                    contexts.add(emptyContext);
                } else if (rule.getRoole()[1] > 0) {
                    LlkContext oneTerminalContext = new LlkContext();
                    oneTerminalContext.addWord(new int[]{rule.getRoole()[1]});
                    contexts.add(oneTerminalContext);
                } else contexts.add(firstK[nonterminals.indexOf(rule.getRoole()[1])]);
            }

            LlkContext globalContext = new LlkContext();
            for(LlkContext context: contexts){
                for(int i = 0; i< context.calcWords(); i++){
                    int[] word = context.getWord(i);
                    if(globalContext.wordInContext(word)){
                        has_collision = true;
                        break outer;
                    } else
                        globalContext.addWord(word);
                }
            }
        }

        return !has_collision;
    }
}
