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

import io.hanbings.cynops.auth.oauth.interfaces.OAuthRequest;
import io.vertx.core.MultiMap;
import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.ext.web.client.HttpResponse;
import io.vertx.ext.web.client.WebClient;

import java.util.Map;

public class VertxOAuthRequest implements OAuthRequest {

    static WebClient client = WebClient.create(Vertx.vertx());

    @Override
    public String post(String url, Map<String, String> params) {
        // 转换为 MultiMap
        MultiMap form = MultiMap.caseInsensitiveMultiMap();
        params.forEach(form::add);

        // 发起请求 返回结果
        HttpResponse<Buffer> response = client.post(url).sendForm(form).result();
        return response.body().toString();
    }
}
