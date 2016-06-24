package toyaskell.type.ast;

import org.junit.Test;
import toyaskell.system.InterpretException;
import toyaskell.type.token.Op;
import toyaskell.type.token.Symbol;
import toyaskell.type.token.Token;
import toyaskell.type.token.Tokens;

import java.util.function.Predicate;
import java.util.logging.Logger;

import static org.junit.Assert.*;

public class TokensTest {
    private Logger logger = Logger.getLogger(TokensTest.class.getName());

    @Test
    public void normalNumber() {
        checkNumber("022345");
    }

    @Test
    public void zeroNumber() {
        checkNumber("0");
        checkNumber("00000");
    }

    @Test(expected = InterpretException.class)
    public void invalidNumber() {
        checkNumber("44444444444444444444");
    }

    @Test
    public void normalSymbol() {
        // legal
        check("x", Symbol.class);
        check("_abc", Symbol.class);
        check("a3", Symbol.class);
        check("a_c_", Symbol.class);
        check("__", Symbol.class);

        // illegal
        check("233a", Symbol.class);
        check("", Symbol.class);
        check("a+b", Symbol.class);
    }

    @Test public void normalOp() {
        check("+", Op.class);
        check("*", Op.class);
        check("->", Op.class);
        check("==", Op.class);
    }

    // details

    private void checkNumber(String input) {
        Token token = Tokens.build(input);
        assertTrue(token instanceof toyaskell.type.token.Number);
        int val = Integer.parseInt(token.toString());
        assertEquals(Integer.parseInt(input), val);
    }

    @SuppressWarnings("unchecked")
    private void check(String name, Class<? extends Token> type) {
        Predicate<String> checker = null;
        try {
            checker = (Predicate<String>) type.getField("checker").get(null);
        } catch (IllegalAccessException|NoSuchFieldException e) {
            e.printStackTrace();
        }

        try {
            logger.info("checking '" + name + "'");
            Token token = Tokens.build(name);
        } catch (InterpretException e) {
            assertFalse(checker.test(name));
            return;
        }
        assertTrue(checker.test(name));
    }
}