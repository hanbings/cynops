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
package io.hanbings.cynops.auth.oauth.interfaces;

public interface OAuth {
    /**
     * 生成授权地址 仅生成 url
     *
     * @param config OAuthConfig
     * @return 授权地址 uri
     */
    String authorize(OAuthConfig config);

    /**
     * 生成 token 用户凭据获取 url
     *
     * @param config   OAuthConfig
     * @param callback authorize 生成的 URL 为用户重定向后返回的数据 无需解析 直接传入 仅生成 url
     * @return 获取到用户的访问凭据
     */
    String token(OAuthConfig config, String callback);

    /**
     * 使用 token 方法中获取到的 token 用户凭据访问用户数据 仅生成 url
     *
     * @param config OAuthConfig
     * @param token  有效访问凭据
     * @return 获取到的用户数据
     */
    String data(OAuthConfig config, String token);
}
