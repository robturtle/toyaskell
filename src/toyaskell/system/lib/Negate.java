package toyaskell.system.lib;

import toyaskell.system.Context;
import toyaskell.type.Function;
import toyaskell.type.Lambda;
import toyaskell.type.primitive.Int;
import toyaskell.type.token.Symbol;

public class Negate extends Unary {
    public static final Lambda negate = new Function(new Symbol("x"), new Negate());

    @Override protected Lambda apply(Symbol formalArg, Lambda actualArg, Context outer) {
        actualArg = super.apply(formalArg, actualArg, outer);
        return new Int(-((Int)actualArg).val);
    }
}
