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

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public abstract class OAuthClient {
    // 密钥与回调地址
    OAuthConfig client;
    // 需要申请的权限
    String scope;
    // state 保护密钥 用于预防中间人攻击 建议使用
    OAuthState state;
    // state 保护密钥开关 默认为 false 即使用 state
    boolean notUseState;
    // 授权类型
    String grantType;
    // 授权地址
    String authorizationUrl;
    // 使用授权码获取 token 的地址
    String tokenUrl;
    // 使用 token 获取资源的地址
    String resourceUrl;

    public abstract String authorize();
    public abstract String token(String callback);
    public abstract String resources(String callback);
}
