package toyaskell.system.lib;

import toyaskell.system.Context;
import toyaskell.type.Function;
import toyaskell.type.Lambda;
import toyaskell.type.token.Symbol;
import toyaskell.type.token.Token;

import static toyaskell.system.lib.Trunk.xArg;
import static toyaskell.type.list.Lists.nil;

public class Assign extends Function {
    public static final Lambda assigner = new Function(xArg, new Assigner());
    public static final Function assign = new Assign();
    private Assign() { super(new Symbol("arg"), assigner); }
}

class Assigner extends Trunk {

    @Override protected Lambda apply(Symbol formalArg, Lambda actualArg, Context outer) {
        Token arg = (Token) outer.lookup(new Symbol("arg"));
        outer.parent.parent.parent.bind(arg, actualArg);
        return nil;
    }

    @Override public String toString() {
        return "()";
    }
}
