package io.hanbings.cynops.auth.oauth;

import io.hanbings.cynops.auth.oauth.interfaces.OAuthClient;
import io.hanbings.cynops.auth.oauth.interfaces.OAuthConfig;
import io.hanbings.cynops.auth.oauth.interfaces.OAuthRequest;
import io.hanbings.cynops.auth.oauth.interfaces.OAuthState;
import io.hanbings.cynops.auth.oauth.type.OAuth;
import lombok.Builder;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class TransFurOAuthClient extends OAuthClient {

    @Builder
    public TransFurOAuthClient(OAuthConfig client, List<String> scope, OAuthState state, boolean notUseState,
                               String grantType, String authorizationUrl, String tokenUrl, String resourceUrl
            , OAuthRequest request) {
        super(client, scope, state, notUseState, grantType, authorizationUrl, tokenUrl, resourceUrl, request);
    }

    @Override
    public String authorize() {
        // 生成请求 url
        String url = (Objects.equals(this.getAuthorizationUrl(), null)
                ? OAuth.TransFur.AUTHORIZE : this.getAuthorizationUrl())
                + "?client_id=" + this.getClient().getClientId()
                + "&redirect_uri=" + this.getClient().getRedirectUri()
                + "&response_type=" + "code";

        // 权限域
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
                put("grant_type", getGrantType());
                put("code", code);
            }
        };

        // 请求
        return this.getRequest().post(Objects.equals(this.getTokenUrl(), null)
                ? OAuth.TransFur.ACCESS_TOKEN : this.getTokenUrl(), params);
    }

    @Override
    public String resource(String token) throws IOException {
        HashMap<String, String> header = new HashMap<>() {
            {
                put("Authorization", "Bearer " + token);
                put("Accept", "application/json");
            }
        };

        return this.getRequest().get(Objects.equals(this.getTokenUrl(), null)
                ? OAuth.TransFur.USER_DATA : this.getTokenUrl(), header);
    }

    @Override
    public String resource(String token, String url) throws IOException {
        HashMap<String, String> header = new HashMap<>() {
            {
                put("Authorization", "Bearer " + token);
                put("Accept", "application/json");
            }
        };

        return this.getRequest().get(Objects.equals(url, null)
                ? OAuth.TransFur.USER_DATA : url, header);
    }
}
