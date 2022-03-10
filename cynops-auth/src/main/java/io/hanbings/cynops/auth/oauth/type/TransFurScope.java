package io.hanbings.cynops.auth.oauth.type;

import java.util.ArrayList;
import java.util.List;

public class TransFurScope {
    List<String> scopes = new ArrayList<>();

    public TransFurScope add(TransFurScopeType type) {
        this.scopes.add(type.getScope());
        return this;
    }

    public String getScopes() {
        return String.join("%20", this.scopes);
    }
}
