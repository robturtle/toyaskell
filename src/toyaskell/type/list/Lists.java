package toyaskell.type.list;

import toyaskell.type.Lambda;

import java.util.Deque;

public class Lists {

    public static final Nil nil = new Nil();

    public static Cons cons(Lambda head, SExp tail) {
        return new Cons(head, tail);
    }

    public static SExp build(Deque<Lambda> elems) {
        SExp list = nil;
        while (!elems.isEmpty()) {
            list = new Cons(elems.pop(), list);
        }
        return list;
    }

    public static Quote quote(Lambda sexp) { return new Quote(sexp); }

}
