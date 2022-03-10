package io.hanbings.cynops.auth.oauth;

import io.hanbings.cynops.auth.oauth.interfaces.OAuthClient;
import io.hanbings.cynops.auth.oauth.interfaces.OAuthConfig;
import io.hanbings.cynops.auth.oauth.interfaces.OAuthState;
import io.hanbings.cynops.auth.oauth.type.OAuth;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

public class GithubOAuthClient extends OAuthClient {
    @Setter @Getter
    boolean allowSignup;
    @Setter @Getter
    String login;

    @Builder
    public GithubOAuthClient(OAuthConfig client, String scope, OAuthState state, boolean notUseState,
                             String grantType, String authorizationUrl, String tokenUrl, String resourceUrl,
                             boolean allowSignup, String login) {
        super(client, scope, state, notUseState, grantType, authorizationUrl, tokenUrl, resourceUrl);
        this.allowSignup = allowSignup;
        this.login = login;
    }

    @Override
    public String authorize() {
        // 生成请求 url
        String url = Objects.equals(this.getAuthorizationUrl(), "")
                ? OAuth.GitHub.AUTHORIZE : this.getAuthorizationUrl()
                + "?client_id=" + this.getClient().getClientId()
                + "&redirect_uri" + this.getClient().getClientSecret();
        // 其他参数
        // allow signup 默认为 true 如果是 false 就加上参数
        if (!this.isAllowSignup()) {
            url += "&allow_signup=false";
        }
        // 指定一个账户登录
        if (this.getLogin() != null) {
            url += "&login=" + this.getLogin();
        }
        // 权限域 如果为空则为获取全部权限
        if (this.getScope() != null) {
            url += "&scope=" + this.getScope();
        }
        // state 生成
        if (!this.isNotUseState()){
            url += this.getState().state();
        }
        return url;
    }

    @Override
    public String token(String callback) {
        return null;
    }

    @Override
    public String resources(String callback) {
        return null;
    }
}
