import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GrammarReader {
    String inputFile = "";
    Scanner scanner;

    public GrammarReader(String inputFile) {
        this.inputFile = inputFile;

        try {
            scanner = new Scanner(new FileInputStream(inputFile));
        } catch (Exception e) {
            throw new RuntimeException("Could not read input file \'" + inputFile + "\'!");
        }
    }

    public List<Rule> read() {
        List<Rule> rules = new ArrayList<>();
        int lineNr = 0;

        while (scanner.hasNext()) {
            String nextLine = scanner.nextLine();
            if(!nextLine.contains("->"))
                throw new RuntimeException("Invalid rule on line " + lineNr + "!");

            String[] rule = nextLine.split("\\s*->\\s*");

            rules.add(new ExpansionRule(rule[0], rule.length == 1 ? "" : rule[1]));

            lineNr++;
        }

        return rules;
    }
}
