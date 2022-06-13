public class Rule {
    protected String nonTerminal;
    protected String[] expansions;

    public String toString() {
        String str = nonTerminal + " -> ";

        if(expansions.length == 1 && expansions[0].equals(""))
            return str += "∅";

        for (String exp : expansions)
            str += exp + " | ";

        return str.substring(0, str.length() - 3);
    }

    public String[] getExpansions() {
        String[] exp = new String[expansions.length];
        for (int i = 0; i < expansions.length; i++)
            exp[i] = expansions[i];
        return exp;
    }

    public void checkNonTerminal(String nt) {
        if (nt.charAt(0) != '<' | nt.charAt(nt.length() - 1) != '>')
            throw new RuntimeException("Non-terminal \'" + nt + "\' should follow format <name>!");
    }
}
