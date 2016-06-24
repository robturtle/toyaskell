package toyaskell.type.token;

import java.util.function.Predicate;
import java.util.regex.Pattern;

public class Symbol extends Token {

    public static final Predicate<String> checker = Pattern
            .compile("^[a-zA-Z_][a-zA-Z0-9_]*$")
            .asPredicate();

    public Symbol(String name) {
        super(name);
    }

}
