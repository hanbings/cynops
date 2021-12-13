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
package io.hanbings.cynops.extra.oauth;

import io.hanbings.cynops.extra.oauth.interfaces.OAuth;
import io.hanbings.cynops.extra.oauth.interfaces.OAuthConfig;
import io.hanbings.cynops.extra.oauth.interfaces.OAuthUrl;

@SuppressWarnings("unused")
public class GithubOAuth implements OAuth {
    OAuthConfig config;

    @Override
    public void oauth(OAuthConfig config) {
        this.config = config;
    }

    @Override
    public String authorize() {
        String url = OAuthUtils.addParam(OAuthUrl.GITHUB_AUTHORIZE, "client_id", config.getClientId());
        // 如果有额外配置项
        if (this.config instanceof GithubOAuthConfig) {
            GithubOAuthConfig authConfig = (GithubOAuthConfig) this.config;
            if (OAuthUtils.NotNull(authConfig.getLogin())) {
                url = OAuthUtils.addParam(url, "login", authConfig.getLogin());
            }
            if (OAuthUtils.NotNull(authConfig.getScope())) {
                url = OAuthUtils.addParam(url, "scope", authConfig.getScope());
            }
            if (OAuthUtils.NotNull(authConfig.getState())) {
                url = OAuthUtils.addParam(url, "state", authConfig.getState());
            }
            if (OAuthUtils.NotNull(authConfig.getRedirectUri())) {
                url = OAuthUtils.addParam(url, "redirect_uri", authConfig.getRedirectUri());
            }
            if (OAuthUtils.NotNull(authConfig.getAllowSignup())) {
                url = OAuthUtils.addParam(url, "allow_signup", authConfig.getAllowSignup());
            }
        }
        return url;
    }

    @Override
    public String token(String callback) {
        return null;
    }

    @Override
    public String data(String token) {
        return null;
    }
}
