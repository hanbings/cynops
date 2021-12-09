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
import io.hanbings.cynops.extra.oauth.interfaces.OAuthProxyType;

@SuppressWarnings("unused")
public class GithubOAuth implements OAuth {
    OAuthProxyType proxyType;
    String proxyAddress;
    int proxyPort;
    boolean isProxy = false;

    @Override
    public void proxy(OAuthProxyType proxyType, String proxyAddress, int proxyPort) {
        this.proxyType = proxyType;
        this.proxyAddress = proxyAddress;
        this.proxyPort = proxyPort;
        this.isProxy = true;
    }

    @Override
    public void oauth(String clientId, String clientSecret, String redirectUri) {

    }

    @Override
    public String url(String oauth) {
        return null;
    }

    @Override
    public String data(String token) {
        return null;
    }

}
