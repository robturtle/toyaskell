package toyaskell.system;

import toyaskell.type.Function;
import toyaskell.type.Lambda;
import toyaskell.type.token.Symbol;

public class PatternMatcher extends Function {
    private final PatternMatcher next;

    public PatternMatcher(Symbol formalArg, Lambda returnValue, PatternMatcher next) {
        super(formalArg, returnValue);
        this.next = next;
    }
}
