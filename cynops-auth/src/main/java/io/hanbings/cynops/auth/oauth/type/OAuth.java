package io.hanbings.cynops.auth.oauth.type;

public class OAuth {
    public static class GitHub {
        public static final String AUTHORIZE = "https://github.com/login/oauth/authorize";
        public static final String ACCESS_TOKEN = "https://github.com/login/oauth/access_token";
        public static final String USER_DATA = "https://api.github.com/user";
    }

    public static class TransFur {
        public static final String AUTHORIZE = "https://id.transfur.cn/oauth/authorize";
        public static final String ACCESS_TOKEN = "https://id.transfur.cn/oauth/token";
        public static final String USER_DATA = "https://id.transfur.cn/api/get_user_info";
    }
}
