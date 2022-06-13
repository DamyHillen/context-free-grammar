import java.util.List;
import java.util.ArrayList;
import java.util.Random;

public class Grammar {
    private List<Rule> rules = new ArrayList<Rule>();
    private boolean verbose = false;

    public Grammar() {
        rules.add(new Lambda());
    }

    public Grammar(List<Rule> rules) {
        this.rules = rules;
        rules.add(new Lambda());
    }

    public void setVerbose(boolean verbose) {
        this.verbose = verbose;
    }

    public String toString() {
        String str = "Grammar:\n";

        for (Rule r : rules)
            str += "\t" + r + "\n";

        return str.substring(0, str.length() - 1);
    }

    public Grammar newRule(String terminal, String expansions) {
        this.rules.add(new ExpansionRule(terminal, expansions));
        return this;
    }

    public Grammar newRule(ExpansionRule expansionRule) {
        this.rules.add(expansionRule);
        return this;
    }

    public String expand(String nonTerminal) {
        if (verbose)
            System.out.println("expanding \"" + nonTerminal + "\"");
        int i = getRule(nonTerminal);
        if (i == -1)
            return nonTerminal;

        String expansion = getRandom(rules.get(i).getExpansions());
        String[] words = expansion.split(" ");
        String str = "";

        for (String word : words) {
            String[] subs;
            if ((subs = parseWord(word)).length > 1) {
                for (String sub : subs)
                    str += expand(sub);
                str += " ";
            } else
                str += expand(word) + " ";
        }

        return str.substring(0, str.length() - 1);
    }

    public int getRule(String nonTerminal) {
        int i;
        for (i = 0; i < rules.size(); i++)
            if (rules.get(i).nonTerminal.equals(nonTerminal))
                return i;
        return -1;
    }

    private static String getRandom(String[] array) {
        return array[new Random().nextInt(array.length)];
    }

    private String[] parseWord(String word) {
        List<String> subs = new ArrayList<>();
        int prev = 0;
        for (int i = 0; i < word.length(); i++)
            if (word.charAt(i) == '<')
                for (int j = i + 1; j < word.length(); j++)
                    if (word.charAt(j) == '>') {
                        subs.add(word.substring(prev, i));
                        subs.add(word.substring(i, j + 1));
                        prev = j + 1;
                        j = word.length();
                    }
        subs.add(word.substring(prev));
        subs.remove("");

        if (verbose)
            System.out.println("parsed    \"" + word + "\"");

        String[] ret = new String[subs.size()];
        for (int i = 0; i < subs.size(); i++)
            ret[i] = subs.get(i);

        return ret;
    }
}
