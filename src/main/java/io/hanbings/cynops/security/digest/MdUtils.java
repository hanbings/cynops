package io.hanbings.cynops.security.digest;

public class MdUtils {
    public enum MdType {
        MD2 {
            @Override
            public String toString() {
                return "md2";
            }
        },
        MD4 {
            @Override
            public String toString() {
                return "md4";
            }
        },
        MD5 {
            @Override
            public String toString() {
                return "md5";
            }
        }
    }
}
