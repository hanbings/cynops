package io.hanbings.cynops.auth.oauth.type;

public enum GithubScopeType {
    USER("user");

    final String scope;

    GithubScopeType(String scope) {
        this.scope = scope;
    }

    public String getScope() {
        return scope;
    }
}
