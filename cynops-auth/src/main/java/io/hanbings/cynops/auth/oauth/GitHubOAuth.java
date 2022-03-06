package io.hanbings.cynops.auth.oauth;

import io.hanbings.cynops.auth.oauth.content.GitHubInterface;
import io.hanbings.cynops.auth.oauth.interfaces.OAuth;
import io.hanbings.cynops.auth.oauth.interfaces.OAuthConfig;

public class GitHubOAuth implements OAuth {
    String state;

    @Override
    public String authorize(OAuthConfig config) {
        GitHubOAuthConfig github = (GitHubOAuthConfig) config;
        // 设置 State
        if (config.getOAuthState() == null ) {
            config.setOAuthState(new PublicOAuthState());
        }
        state = config.getOAuthState().state();
        // 生成 url
        return GitHubInterface.GITHUB_AUTHORIZE
                + "?client_id=" + config.getClientId()
                + "&scope=" + config.getScope()
                + "&redirect_uri=" + config.getRedirectUri()
                + "&login=" + ((GitHubOAuthConfig) config).getLogin()
                + "&allow_signup=" + ((GitHubOAuthConfig) config).isAllowSignup()
                + "&state=" + state;
    }

    @Override
    public String token(OAuthConfig config, String callback) {
        return null;
    }

    @Override
    public String data(OAuthConfig config, String token) {
        return null;
    }
}
