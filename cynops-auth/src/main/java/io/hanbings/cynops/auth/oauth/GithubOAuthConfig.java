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

import io.hanbings.cynops.auth.oauth.interfaces.OAuthClient;
import io.hanbings.cynops.auth.oauth.interfaces.OAuthConfig;
import io.hanbings.cynops.auth.oauth.interfaces.OAuthState;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

public class GithubOAuthConfig extends OAuthConfig {
    // 是否允许登录的过程注册
    @Setter @Getter
    boolean allowSignup;
    // 指定登录的用户
    @Setter @Getter
    String login;

    @Builder
    public GithubOAuthConfig(OAuthClient client, String scope, OAuthState state, boolean isState,
                             String grantType, String authorizationUrl, String tokenUrl, String resourceUrl,
                             boolean allowSignup, String login) {
        super(client, scope, state, isState, grantType, authorizationUrl, tokenUrl, resourceUrl);
        this.allowSignup = allowSignup;
        this.login = login;
    }

    public static void main(String[] args) {
        new GithubOAuth().authorize(GithubOAuthConfig.builder()
                .client(new OAuthClient("xx", "xx", "xx"))
                .build()
        );
    }
}
