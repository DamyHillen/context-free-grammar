public class ExpansionRule extends Rule {
    public ExpansionRule(String nonTerminal, String expansions) {
        checkNonTerminal(nonTerminal);
        this.nonTerminal = nonTerminal;
        this.expansions = expansions.split(" \\| ");
    }
}
