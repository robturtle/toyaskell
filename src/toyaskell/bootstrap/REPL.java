package toyaskell.bootstrap;

import toyaskell.system.InterpretException;
import toyaskell.type.list.SExp;

import java.util.Scanner;

import static toyaskell.bootstrap.Tokenizer.tokenize;
import static toyaskell.system.lib.SystemContext.systemContext;

public class REPL {
    public static void main(String[] args) {
        Parser parser = new Parser(systemContext);
        Scanner stdin = new Scanner(System.in);

        do {

            System.out.print("> ");
            System.out.flush();
            String input = stdin.nextLine();
            if (input.trim().equals("quit")) { break; }
            try {
                SExp exp = tokenize(input);
                System.out.println(parser.call(exp));
            } catch (InterpretException e) {
                System.out.println(e.getMessage());
            } catch (ClassCastException e) {
                e.printStackTrace();
            }

        } while (true);
    }
}
