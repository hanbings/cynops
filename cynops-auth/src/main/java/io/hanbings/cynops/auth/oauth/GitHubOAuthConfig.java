package io.hanbings.cynops.auth.oauth;

import io.hanbings.cynops.auth.oauth.interfaces.OAuthConfig;
import io.hanbings.cynops.auth.oauth.interfaces.OAuthState;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

public class GitHubOAuthConfig extends OAuthConfig {
    // 在 OAuth 流程中 是否向经过验证的用户提供注册 GitHub 的选项 默认值为 true
    @Getter
    @Setter
    boolean allowSignup;
    // 提供用于登录和授权应用程序的特定账户
    @Getter
    @Setter
    String login;

    @Builder
    public GitHubOAuthConfig(String clientId, String clientSecret, String redirectUri,
                             String scope, OAuthState oAuthState,
                             String login, boolean allowSignup) {
        super(clientId, clientSecret, redirectUri, scope, oAuthState);
        this.login = login;
        this.allowSignup = allowSignup;
    }

}
