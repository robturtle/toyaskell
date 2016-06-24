package toyaskell.system;

import toyaskell.type.Lambda;

import java.util.HashMap;
import java.util.Map;

public class Context {
    private final Map<Lambda, Lambda> mappings = new HashMap<>();
    public final Context parent;

    public Context(Context parent) { this.parent = parent; }
    public Context() { this(null); }

    public Context cloneWithParent(Context parent) {
        Context context = new Context(parent);
        context.mappings.putAll(mappings);
        return context;
    }

    public void bind(Lambda token, Lambda value) {
        mappings.put(token, value);
    }

    public Lambda lookup(Lambda token) {
        if (!mappings.containsKey(token)) {
            if (parent == null) {
                throw new LookupError("ERROR: '" + token + "' not found in scope");
            }
            return parent.lookup(token);
        }
        return mappings.get(token);
    }
}
