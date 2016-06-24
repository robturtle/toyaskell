package toyaskell.system.lib;

import toyaskell.system.Context;
import toyaskell.type.Function;
import toyaskell.type.Lambda;
import toyaskell.type.token.Symbol;

public class Quote extends Unary {
    public static final Function quote = new Function(xsArg, new Quote());

    @Override
    protected Lambda apply(Symbol formalArg, Lambda actualArg, Context outer) {
        actualArg = super.apply(formalArg, actualArg, outer);
        return new toyaskell.type.list.Quote(actualArg);
    }

    @Override public String toString() {
        return xsArg.toString();
    }
}
