package toyaskell.system.lib;

import toyaskell.system.Context;

import static toyaskell.system.lib.Assign.assign;
import static toyaskell.system.lib.Cons.cons;
import static toyaskell.system.lib.Negate.negate;
import static toyaskell.system.lib.Quote.quote;
import static toyaskell.system.lib.Succ.succ;
import static toyaskell.type.list.Lists.nil;
import static toyaskell.type.primitive.TrueType.trueType;
import static toyaskell.type.token.Tokens.build;

public class SystemContext {
    public static final Context systemContext = new Context();
    static {
        systemContext.bind(build("t"), trueType);
        systemContext.bind(build("nil"), nil);
        systemContext.bind(build("++"), succ);
        systemContext.bind(build("neg"), negate);
        systemContext.bind(build("cons"), cons);
        systemContext.bind(build("let"), assign);
        systemContext.bind(build("'"), quote);
    }
}
