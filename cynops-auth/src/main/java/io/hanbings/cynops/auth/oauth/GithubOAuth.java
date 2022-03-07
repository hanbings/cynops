/*
 * Copyright (c) 2021 Hanbings / hanbings Cynops Toolbox.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *           http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.hanbings.cynops.auth.oauth;

import io.hanbings.cynops.auth.oauth.interfaces.OAuth;
import io.hanbings.cynops.auth.oauth.interfaces.OAuthConfig;
import io.hanbings.cynops.auth.oauth.type.GithubApi;

import java.util.Objects;

public class GithubOAuth implements OAuth {
    @Override
    public String authorize(OAuthConfig config) {
        GithubOAuthConfig github = (GithubOAuthConfig) config;
        // 生成请求 url
        String url =Objects.equals(config.getAuthorizationUrl(), "") ? GithubApi.AUTHORIZE : config.getAuthorizationUrl()
                + "?client_id=" + config.getClient().getClientId()
                + "&redirect_uri" + config.getClient().getClientSecret();
        // 其他参数
        // allow signup 默认为 true 如果是 false 就加上参数
        if (!github.isAllowSignup()) {
            url += "&allow_signup=false";
        }
        // 指定一个账户登录
        if (github.getLogin() != null) {
            url += "&login=" + github.getLogin();
        }
        // 权限域 如果为空则为获取全部权限
        if (github.getScope() != null) {
            url += "&scope=" + github.getScope();
        }
        // state 生成
        if (!config.isNotUseState()){
            url += config.getState().state();
        }
        return url;
    }

    @Override
    public String token(OAuthConfig config, String callback) {
        return null;
    }

    @Override
    public String resources(OAuthConfig config, String callback) {
        return null;
    }
}
