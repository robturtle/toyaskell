package toyaskell.type.token;

import toyaskell.system.InterpretException;
import toyaskell.type.primitive.Int;

import java.util.function.Predicate;
import java.util.regex.Pattern;

public class Number extends Token {

    public static final Predicate<String> checker = Pattern.compile("^[0-9]+$").asPredicate();

    public final Int value;

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public Number(String repr) {
        super(repr);
        try {
            int val = java.lang.Integer.parseInt(repr);
            this.value = new Int(val);
        } catch (NumberFormatException e) {
            throw new InterpretException("ERROR: parse Int '" + repr + "' failed");
        }
    }

}
