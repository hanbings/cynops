package io.hanbings.cynops.auth.oauth;

import io.hanbings.cynops.auth.oauth.interfaces.OAuthState;

import java.util.UUID;

public class PublicOAuthState implements OAuthState {
    @Override
    public String state() {
        return UUID.randomUUID().toString();
    }
}
