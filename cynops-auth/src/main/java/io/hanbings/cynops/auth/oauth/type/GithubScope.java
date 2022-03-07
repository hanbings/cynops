package io.hanbings.cynops.auth.oauth.type;

import java.util.ArrayList;
import java.util.List;

public class GithubScope {
    List<String> scopes = new ArrayList<>();

    public GithubScope add(GithubScopeType type) {
        this.scopes.add(type.getScope());
        return this;
    }

    public String getScopes() {
        return String.join("%20", this.scopes);
    }
}
