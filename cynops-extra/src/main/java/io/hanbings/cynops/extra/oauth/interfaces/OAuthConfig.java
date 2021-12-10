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
package io.hanbings.cynops.extra.oauth.interfaces;

/**
 * 不是标准 Builder 哦 <br>
 * 尝试下更好拓展的写法 因为这个类并非最终的实现 <br>
 * clientId OAuth 客户端 ID <br>
 * clientSecret OAuth 客户端密钥 <br>
 * redirectUri 授权后的回调地址 <br>
 * proxy 是否使用代理 <br>
 * proxyType 代理协议类型 可选 http sock5 <br>
 * proxyAddress 代理地址 <br>
 * proxyPort 代理端口 <br>
 * proxyAuth 代理是否需要认证 <br>
 * proxyUsername 代理认证所用的用户名 <br>
 * proxyPassword 代理认证所用的密码
 */
@SuppressWarnings("unused")
public class OAuthConfig {
    String clientId;
    String clientSecret;
    String redirectUri;
    boolean proxy;
    ProxyType proxyType;
    String proxyAddress;
    int proxyPort;
    boolean proxyAuth;
    String proxyUsername;
    String proxyPassword;

    public OAuthConfig clientId(String clientId) {
        this.clientId = clientId;
        return this;
    }
    public OAuthConfig clientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
        return this;
    }
    public OAuthConfig redirectUri(String redirectUri) {
        this.redirectUri = redirectUri;
        return this;
    }
    public OAuthConfig isProxy(boolean proxy) {
        this.proxy = proxy;
        return this;
    }

    public OAuthConfig proxyType(ProxyType proxyType) {
        this.proxyType = proxyType;
        return this;
    }

    public OAuthConfig proxyAddress(String proxyAddress) {
        this.proxyAddress = proxyAddress;
        return this;
    }

    public OAuthConfig proxyPort(int proxyPort) {
        this.proxyPort = proxyPort;
        return this;
    }

    public OAuthConfig isProxyAuth(boolean proxyAuth) {
        this.proxyAuth = proxyAuth;
        return this;
    }

    public OAuthConfig proxyUsername(String proxyUsername) {
        this.proxyUsername = proxyUsername;
        return this;
    }

    public OAuthConfig proxyPassword(String proxyPassword) {
        this.proxyPassword = proxyPassword;
        return this;
    }

    public String getClientId() {
        return clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public String getRedirectUri() {
        return redirectUri;
    }

    public boolean isProxy() {
        return proxy;
    }

    public ProxyType getProxyType() {
        return proxyType;
    }

    public String getProxyAddress() {
        return proxyAddress;
    }

    public int getProxyPort() {
        return proxyPort;
    }

    public boolean isProxyAuth() {
        return proxyAuth;
    }

    public String getProxyUsername() {
        return proxyUsername;
    }

    public String getProxyPassword() {
        return proxyPassword;
    }
}
