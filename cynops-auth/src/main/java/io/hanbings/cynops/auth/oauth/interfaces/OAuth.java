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

/**
 * OAuth 基本配置 <br>
 * 流程 <br>
 * - 每一个 oauth 实现都应有对应的 OAuthConfig
 * 使用对应的 OAuthConfig 配置 oauth
 * 如果没有 可以使用默认的 OAuthConfig <br>
 * - 使用 oauth 方法设置 OAuthConfig 参数 <br>
 * - 使用 authorize 方法获得用户授权 uri 用户授权后 返回到回调地址 <br>
 * - authorize 方法返回 code 授权码 使用 token 方法获得 token 访问凭据 <br>
 * - data 方法使用  token 访问凭据获得用户数据
 */
@SuppressWarnings("unused")
public interface OAuth {
    /**
     * 设置基本参数
     *
     * @param config 对应的 OAuthConfig
     */
    void oauth(OAuthConfig config);

    /**
     * 生成授权地址
     *
     * @return 授权地址 uri
     */
    String authorize();

    /**
     * 生成 token 用户凭据获取 url
     *
     * @param callback authorize 方法中返回的 code 参数 只需要 code 参数 不需要完整反馈
     * @return 获取到用户的访问凭据
     */
    String token(String callback);

    /**
     * 使用 token 方法中获取到的 token 用户凭据访问用户数据 仅生成 url
     *
     * @param token 有效访问凭据
     * @return 获取到的用户数据
     */
    String data(String token);
}
