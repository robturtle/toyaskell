package toyaskell.type.list;

class Nil extends SExp {
    Nil() {}
    @Override public boolean isNil() { return true; }
    @Override public String toString() {
        return "nil";
    }
}
