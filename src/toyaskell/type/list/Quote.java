package toyaskell.type.list;

import toyaskell.type.Lambda;

public class Quote extends SExp {
    public final Lambda value;

    public Quote(Lambda value) { this.value = value; }

    @Override public String toString() {
        return value.toString();
    }

    @Override
    public boolean isNil() {
        return false;
    }
}
