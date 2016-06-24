package toyaskell.type.primitive;

import toyaskell.type.Value;

public class Int extends Value {
    public final int val;

    public Int(int val) { this.val = val; }

    @Override public String toString() {
        return Integer.toString(val);
    }

    @Override public boolean equals(Object other) {
        if (other == null || other.getClass() != getClass()) {
            return false;
        }
        Int that = (Int) other;
        return this.val == that.val;
    }

    @Override public int hashCode() {
        return val;
    }
}
