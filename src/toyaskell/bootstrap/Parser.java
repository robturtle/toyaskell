package toyaskell.bootstrap;

import toyaskell.system.Context;
import toyaskell.type.Function;
import toyaskell.type.Lambda;
import toyaskell.type.list.Cons;
import toyaskell.type.list.SExp;
import toyaskell.type.token.Number;
import toyaskell.type.token.Op;
import toyaskell.type.token.Symbol;

import static toyaskell.system.lib.Assign.assigner;


public class Parser extends Lambda {

    public Parser(Context outer) {
        super(null, null, outer);
    }

    @Override public Lambda call(Lambda lambda) {
        if (!(lambda instanceof SExp) || ((SExp) lambda).isNil()) {
            return lambda;
        }

        Cons list = (Cons) lambda;

        Lambda value = null;
        for (SExp exp = list; !exp.isNil(); exp = ((Cons) exp).tail) {
            Lambda current = ((Cons) exp).head;
            if ((current instanceof Symbol || current instanceof Op) &&
                    !(value != null && assigner.equals(value.returnValue))) {
                current = closure.lookup(current);
            }

            if (current instanceof Function) {
                current = ((Function) current).cloneWithContext(closure);
            } else if (current instanceof Number) {
                current = ((Number) current).value;
            }

            if (value == null) {
                value = current;
            } else {
                value = value.call(current);
                if (value instanceof Function) {
                    value = ((Function) value).cloneWithContext(closure);
                }
            }
        }
        if (value instanceof Cons) { value = call(value); }
        return value;
    }
}
