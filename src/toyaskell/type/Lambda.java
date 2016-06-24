package toyaskell.type;

import toyaskell.system.Context;
import toyaskell.type.token.Symbol;

public class Lambda {

    protected final Symbol formalArg;
    public final Lambda returnValue;
    protected final Context closure;

    public Lambda(Symbol formalArg, Lambda returnValue, Context outer) {
        this.formalArg = formalArg;
        this.closure = new Context(outer);
        this.returnValue = returnValue;
    }

    public Lambda call(Lambda actualArg) {
        return returnValue.apply(formalArg, actualArg, closure);
    }

    protected Lambda apply(Symbol formalArg, Lambda actualArg, Context outer) {
        Context context = closure.cloneWithParent(outer);
        context.bind(formalArg, actualArg);
        return new Lambda(this.formalArg, returnValue, context);
    }

    @Override public String toString() {
        return formalArg + " -> " + returnValue;
    }

}
