package io.hanbings.cynops.auth.oauth;

import io.hanbings.cynops.auth.oauth.interfaces.OAuthClient;
import io.hanbings.cynops.auth.oauth.interfaces.OAuthConfig;
import io.hanbings.cynops.auth.oauth.interfaces.OAuthRequest;
import io.hanbings.cynops.auth.oauth.interfaces.OAuthState;
import io.hanbings.cynops.auth.oauth.type.OAuth;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class GithubOAuthClient extends OAuthClient {
    @Setter
    @Getter
    boolean allowSignup;
    @Setter
    @Getter
    String login;

    @Builder
    public GithubOAuthClient(OAuthConfig client, List<String> scope, OAuthState state, boolean notUseState,
                             String grantType, String authorizationUrl, String tokenUrl, String resourceUrl,
                             OAuthRequest request, boolean allowSignup, String login) {
        super(client, scope, state, notUseState, grantType, authorizationUrl, tokenUrl, resourceUrl, request);
        this.allowSignup = allowSignup;
        this.login = login;
    }


    @Override
    public String authorize() {
        // 生成请求 url
        String url = (Objects.equals(this.getAuthorizationUrl(), null)
                ? OAuth.GitHub.AUTHORIZE : this.getAuthorizationUrl())
                + "?client_id=" + this.getClient().getClientId()
                + "&redirect_uri=" + this.getClient().getRedirectUri();

        // 其他参数
        // allow signup 默认为 true 如果是 false 就加上参数
        if (!this.isAllowSignup()) url += "&allow_signup=false";
        // 指定一个账户登录
        if (this.getLogin() != null) url += "&login=" + this.getLogin();
        // 权限域 如果为空则为获取全部权限
        if (this.getScope() != null) url += "&scope=" + String.join("%20", this.getScope());
        // state 生成
        if (!this.isNotUseState()) url += "&state=" + this.getState().state();

        return url;
    }

    @Override
    public String token(String code) throws IOException {
        // 检测请求器
        if (Objects.equals(this.getRequest(), null)) {
            this.setRequest(new OkHttpOAuthRequest());
        }

        // 生成请求参数
        Map<String, String> params = new HashMap<>() {
            {
                put("client_id", getClient().getClientId());
                put("client_secret", getClient().getClientSecret());
                put("redirect_uri", getClient().getRedirectUri());
                put("code", code);
            }
        };

        // 请求
        return this.getRequest().post(Objects.equals(this.getTokenUrl(), null)
                ? OAuth.GitHub.ACCESS_TOKEN : this.getTokenUrl(), params);
    }

    @Override
    public String resource(String token) throws IOException {
        HashMap<String, String> header = new HashMap<>() {{
            put("Authorization", "token " + token);
        }};

        return this.getRequest().get(Objects.equals(this.getTokenUrl(), null)
                ? OAuth.GitHub.USER_DATA : this.getTokenUrl(), header);
    }

    @Override
    public String resource(String token, String url) throws IOException {
        HashMap<String, String> header = new HashMap<>() {{
            put("Authorization", "token " + token);
        }};

        return this.getRequest().get(Objects.equals(this.getTokenUrl(), null)
                ? OAuth.GitHub.USER_DATA : this.getTokenUrl(), header);
    }
}
