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
 * OAuth 基本配置 <br>
 * 流程 <br>
 * - 有需要的话使用 proxy 方法设置代理 <br>
 * - 使用 oauth 获取生成授权页的 code 这个 code 将被发送到设定的回调地址 <br>
 * - 使用 url 方法生成用户点击的 url <br>
 * - 使用 data 方法获取用户数据
 */
@SuppressWarnings("unused")
public interface OAuth {
    /**
     *
     * @param proxyType 代理类型 可选 http sock5
     * @param proxyAddress 代理服务器地址
     * @param proxyPort 代理服务器端口
     */
    void proxy(OAuthProxyType proxyType, String proxyAddress, int proxyPort);

    /**
     * 请求 OAuth 链路 code
     * @param clientId 客户端 ID
     * @param clientSecret 客户端密钥
     * @param redirectUri 回调 uri code 将会返回到这里
     */
    void oauth(String clientId, String clientSecret, String redirectUri);

    /**
     * 组装用户的授权网页链接
     * @param oauth oauth 方法得到的返回值
     * @return 用户点击授权的页面 url
     */
    String url(String oauth);
    
    /**
     * 使用 url 方法获取到的 token 请求用户信息
     * @param token url 方法获取的 token
     * @return 用户信息
     */
    String data(String token);
}
