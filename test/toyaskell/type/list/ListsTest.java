package toyaskell.type.list;

import org.junit.Test;
import toyaskell.type.Lambda;
import toyaskell.type.token.Tokens;
import toyaskell.type.primitive.Int;

import java.util.ArrayDeque;
import java.util.Deque;

import static org.junit.Assert.*;
import static toyaskell.type.list.Lists.cons;
import static toyaskell.type.list.Lists.nil;

public class ListsTest {

    @Test
    public void buildList() {
        Deque<Lambda> values = new ArrayDeque<>();
        values.push(new Int(2333));
        values.push(nil);
        values.push(Tokens.build("+"));
        values.push(Tokens.build("hello"));
        values.push(Tokens.build("+"));
        values.push(Tokens.build("world"));

        Cons expected = cons(new Int(2333),
                cons(nil,
                        cons(Tokens.build("+"),
                                cons(Tokens.build("hello"),
                                        cons(Tokens.build("+"),
                                                cons(Tokens.build("world"), nil))))));

        assertEquals(expected, Lists.build(values));
    }

}