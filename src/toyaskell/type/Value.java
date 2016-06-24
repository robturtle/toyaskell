package toyaskell.type;

import toyaskell.system.Context;
import toyaskell.system.InterpretException;
import toyaskell.type.token.Symbol;

public class Value extends Lambda {
    public Value() { super(null, null, null); }

    @Override public Lambda call(Lambda actualArg) {
        throw new InterpretException("ERROR: '" + this + "' is not callable");
    }

    @Override protected Lambda apply(Symbol formalArg, Lambda actualArg, Context outer) {
        throw new InterpretException("ERROR: Can't bind variable to a Value '" + this + "'");
    }

}
