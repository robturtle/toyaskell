package toyaskell.system.lib;

import toyaskell.system.Context;
import toyaskell.type.Function;
import toyaskell.type.Lambda;
import toyaskell.type.primitive.Int;
import toyaskell.type.token.Symbol;

public class Succ extends Unary {

    public static final Lambda succ = new Function(new Symbol("x"), new Succ());

    @Override protected Lambda apply(Symbol formalArg, Lambda actualArg, Context outer) {
        actualArg = super.apply(formalArg, actualArg, outer);
        return new Int(((Int) actualArg).val + 1);
    }

}
