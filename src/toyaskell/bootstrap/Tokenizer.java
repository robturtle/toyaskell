package toyaskell.bootstrap;

import toyaskell.system.InterpretException;
import toyaskell.type.Lambda;
import toyaskell.type.token.Tokens;
import toyaskell.type.list.Lists;
import toyaskell.type.list.SExp;

import java.util.ArrayDeque;
import java.util.Deque;

public class Tokenizer {

    private static StringBuilder wordCache;
    private static Deque<Deque<Lambda>> tokenStack;

    public static SExp tokenize(String repr) {
        repr = '(' + repr + ')';

        wordCache = new StringBuilder();
        tokenStack = new ArrayDeque<>();
        tokenStack.push(new ArrayDeque<>());

        repr.chars().forEach(Tokenizer::processChar);

        Deque<Lambda> exps = tokenStack.pop();
        Lambda exp = exps.pop();
        if (!tokenStack.isEmpty() || !exps.isEmpty()) {
            throw new InterpretException("ERROR: additional left bracket");
        }
        return (SExp) exp;
    }

    private static void processChar(int c) {
        if (c == '(' || c == ')' || Character.isSpaceChar(c)) {
            pushToken(tokenStack);
        } else {
            wordCache.append((char) c);
        }

        if (c == '(') {
            tokenStack.push(new ArrayDeque<>());
        } else if (c == ')') {
            Deque<Lambda> tokens = tokenStack.pop();
            if (tokenStack.isEmpty()) {
                throw new InterpretException("ERROR: additional right bracket");
            }
            SExp list = Lists.build(tokens);
            tokenStack.peek().push((Lambda) list);
        }
    }

    private static void pushToken(Deque<Deque<Lambda>> tokenStack) {
        String name = wordCache.toString();
        if (name.isEmpty()) { return; }
        tokenStack.peek().push(Tokens.build(name));
        wordCache = new StringBuilder();
    }

}
