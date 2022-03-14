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
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;

public class OkHttpOAuthRequest implements OAuthRequest {

    OkHttpClient client = new OkHttpClient();

    @Override
    public String post(String url, Map<String, String> params) throws IOException {
        // 将 Map 参数转换为 FormBody
        FormBody.Builder body = new FormBody.Builder();
        params.forEach(body::add);

        // 构建请求
        Request request = new Request.Builder()
                .post(body.build())
                .url(url)
                .build();

        // 发送请求
        return Objects.requireNonNull(client.newCall(request).execute().body()).string();
    }

    @Override
    public String get(String url, Map<String, String> headers) throws IOException {
        Request.Builder request = new Request.Builder().get().url(url);
        headers.forEach(request::addHeader);

        return Objects.requireNonNull(client.newCall(request.build()).execute().body()).string();
    }
}
