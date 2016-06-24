package toyaskell.system.lib;

import toyaskell.system.Context;
import toyaskell.type.Function;
import toyaskell.type.Lambda;
import toyaskell.type.list.SExp;
import toyaskell.type.token.Symbol;

import static toyaskell.system.lib.ConsTail.consTail;
import static toyaskell.system.lib.Trunk.xArg;
import static toyaskell.type.list.Lists.cons;
import static toyaskell.type.list.Lists.quote;

public class Cons extends Function {
    public static final Lambda cons = new Cons();
    private Cons() { super(xArg, consTail); }
}

class ConsTail extends Trunk {
    static final Lambda consTail = new Function(xsArg, new ConsTail());

    @Override
    protected Lambda apply(Symbol formalArg, Lambda tail, Context outer) {
        tail = super.apply(formalArg, tail, outer);
        Lambda head = outer.lookup(xArg);
        return quote(cons(head, (SExp) tail));
    }

    @Override public String toString() {
        return xsArg.toString();
    }
}
