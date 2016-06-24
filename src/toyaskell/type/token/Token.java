package toyaskell.type.token;

import toyaskell.type.Value;

public abstract class Token extends Value {

    final String name;

    Token(String name) {
        this.name = name;
    }

    @Override public String toString() {
        return name;
    }

    @Override public boolean equals(Object other) {
        if (other == null || other.getClass() != getClass()) {
            return false;
        }
        Token that = (Token) other;
        return this.name.equals(that.name);
    }

    @Override public int hashCode() {
        return name.hashCode();
    }

}
