package toyaskell.bootstrap;

import org.junit.Test;
import toyaskell.system.InterpretException;
import toyaskell.type.token.*;
import toyaskell.type.token.Number;
import toyaskell.type.primitive.Int;
import toyaskell.type.list.Cons;
import toyaskell.type.list.SExp;

import static org.junit.Assert.*;
import static toyaskell.bootstrap.Tokenizer.tokenize;
import static toyaskell.type.token.Tokens.build;
import static toyaskell.type.list.Lists.cons;
import static toyaskell.type.list.Lists.nil;

// TODO better use property based testing here
public class TokenizerTest {

    @Test public void voidString() {
        assertEquals(nil, tokenize(""));
    }

    @Test public void singleNumber() {
        SExp exp = tokenize("2333");
        assertTrue(exp instanceof Cons);
        Cons list = (Cons) exp;
        assertTrue(list.head instanceof Number);
        Int parsedValue = ((Number) list.head).value;
        assertEquals(new Int(2333), parsedValue);
        assertEquals(nil, list.tail);
    }

    @Test public void multipleTokens() {
        SExp exp = tokenize("nil 2333 + hello <>");
        Cons expected =
                cons(build("nil"),
                cons(build("2333"),
                cons(build("+"),
                cons(build("hello"),
                cons(build("<>"), nil)))));
        assertEquals(expected, exp);
    }

    @Test public void nestedExpression() {
        SExp exp = tokenize("(1 + 3) - (6 *(7 / 8)) ++ ((9 -- 10) ** 11)");

        Cons part1 = cons(new Number("1"), cons(new Op("+"), cons(new Number("3"), nil)));
        Cons part2 =
                cons(new Number("6"),
                cons(new Op("*"),
                cons(cons(new Number("7"),
                     cons(new Op("/"),
                     cons(new Number("8"), nil))), nil)));

        Cons part3 =
                cons(cons(new Number("9"),
                     cons(new Op("--"),
                     cons(new Number("10"), nil))),
                cons(new Op("**"),
                cons(new Number("11"), nil)));

        Cons expected =
                cons(part1,
                cons(new Op("-"),
                cons(part2,
                cons(new Op("++"),
                cons(part3, nil)))));

        assertEquals(expected, exp);
    }

    @Test(expected = InterpretException.class)
    public void additionalLeftBracket() {
        tokenize("(");
    }

    @Test(expected = InterpretException.class)
    public void additionalRightBracket() {
        tokenize("(1 + 3) - (6 * (7 / 8))) ++ ((9 -- 10) ** 11)");
    }

}