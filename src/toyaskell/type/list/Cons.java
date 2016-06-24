package toyaskell.type.list;

import toyaskell.type.Lambda;

public class Cons extends SExp {
    public final Lambda head;
    public final SExp tail;

    Cons(Lambda head, SExp tail) {
        this.head = head;
        this.tail = tail;
    }

    @Override public boolean isNil() { return false; }

    @Override public String toString() {
        return "( " + head + " " + tail + " )";
    }

    @Override public boolean equals(Object other) {
        if (other == null || other.getClass() != getClass()) {
            return false;
        }
        Cons that = (Cons) other;
        return this.head.equals(that.head) &&
                this.tail.equals(that.tail);
    }

    @Override public int hashCode() {
        int hash = this.head.hashCode();
        final int prime = 37;
        hash = hash * prime + this.tail.hashCode();
        return hash;
    }

}
