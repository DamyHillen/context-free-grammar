public class Main {
    public static void main(String[] args) {
        Grammar gr = new Grammar(new GrammarReader("test.txt").read());

//        System.out.println(gr + "\n");

        System.out.println(gr.expand("<start>"));
    }
}
