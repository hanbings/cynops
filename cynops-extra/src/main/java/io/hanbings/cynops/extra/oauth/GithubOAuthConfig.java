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

import io.hanbings.cynops.extra.oauth.interfaces.OAuthConfig;

/**
 * Github 平台特有参数
 */
@SuppressWarnings("unused")
public class GithubOAuthConfig extends OAuthConfig {
    String login;
    String scope;
    String state;
    String redirectUri;
    String allowSignup;

    public GithubOAuthConfig login(String login) {
        this.login = login;
        return this;
    }

    public GithubOAuthConfig scope(String scope) {
        this.scope = scope;
        return this;
    }

    public GithubOAuthConfig state(String state) {
        this.state = state;
        return this;
    }

    public OAuthConfig redirectUri(String redirectUri) {
        this.redirectUri = redirectUri;
        return this;
    }

    public GithubOAuthConfig allowSignup(String allowSignup) {
        this.allowSignup = allowSignup;
        return this;
    }

    public String getLogin() {
        return login;
    }

    public String getScope() {
        return scope;
    }

    public String getState() {
        return state;
    }

    public String getRedirectUri() {
        return redirectUri;
    }

    public String getAllowSignup() {
        return allowSignup;
    }
}
