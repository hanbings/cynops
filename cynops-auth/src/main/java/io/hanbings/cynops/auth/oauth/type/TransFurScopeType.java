package io.hanbings.cynops.auth.oauth.type;

public enum TransFurScopeType {
    USER("get-user-info");

    final String scope;

    TransFurScopeType(String scope) {
        this.scope = scope;
    }

    public String getScope() {
        return scope;
    }
}
