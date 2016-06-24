package toyaskell.type;

import toyaskell.system.Context;
import toyaskell.type.token.Symbol;

public class Function extends Lambda {
    public Function(Symbol formalArg, Lambda returnValue) {
        super(formalArg, returnValue, null);
    }

    public Lambda cloneWithContext(Context context) {
        return new Lambda(formalArg, returnValue, context);
    }

}
