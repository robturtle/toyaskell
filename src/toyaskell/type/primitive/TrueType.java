package toyaskell.type.primitive;

import toyaskell.type.Value;

public class TrueType extends Value {
    private TrueType() {}
    public static final TrueType trueType = new TrueType();
    @Override public String toString() {
        return "t";
    }
}
