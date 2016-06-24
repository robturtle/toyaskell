package toyaskell.type.token;

import java.util.function.Predicate;
import java.util.regex.Pattern;

public class Op extends Token {

    public static final Predicate<String> checker = Pattern.compile("^[^a-zA-Z0-9_]+$").asPredicate();

    public Op(String name) {
        super(name);
    }

}
