package toyaskell.system.lib;

import toyaskell.bootstrap.Parser;
import toyaskell.system.Context;
import toyaskell.type.Lambda;
import toyaskell.type.Value;
import toyaskell.type.list.Cons;
import toyaskell.type.token.Symbol;

class Trunk extends Value {
    public static final Symbol aArg = new Symbol("a");
    public static final Symbol bArg = new Symbol("b");
    public static final Symbol xArg = new Symbol("x");
    public static final Symbol xsArg = new Symbol("xs");

    @Override protected Lambda apply(Symbol formalArg, Lambda actualArg, Context outer) {
        // this is where lazy evaluation happened
        if (actualArg instanceof Cons) {
            actualArg = new Parser(outer).call(actualArg);
        }
        return actualArg;
    }
}
