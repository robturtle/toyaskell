package toyaskell.type.token;

import toyaskell.system.InterpretException;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Tokens {

    private static class Factory {
        final Class<? extends Token> type;
        final Predicate<String> checker;
        final Constructor<? extends Token> constructor;

        @SuppressWarnings("unchecked")
        Factory(Class<? extends Token> type) {
            this.type = type;
            try {
                constructor = type.getConstructor(String.class);
            } catch (NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
            try {
                Field checkerFiled = type.getField("checker");
                checker = (Predicate<String>) checkerFiled.get(null);
            } catch (NoSuchFieldException|IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }

    }

    private static final List<Factory> factories =
            Arrays
                    .asList(toyaskell.type.token.Number.class, Symbol.class, Op.class)
                    .stream()
                    .map(Factory::new)
                    .collect(Collectors.toList());

    public static Token build(String name) {
        for (Factory factory : factories) {
            if (factory.checker.test(name)) {
                try {
                    return factory.constructor.newInstance(name);
                } catch (InstantiationException|IllegalAccessException|InvocationTargetException e) {
                    throw new InterpretException(
                            "ERROR: '" + name + "' is not a valid " + factory.type.getTypeName()
                    );
                }
            }
        }
        throw new InterpretException(name + " is not a valid token");
    }

}
