package io.hanbings.cynops.security;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AesUtilsTest {

    @Test()
    void encrypt() throws Exception {
        assertEquals("U2FsdGVkX191/S9qEGWQ6w5ybJmFl9bJ3CSZLuvfaFw="
                , AesUtils.encrypt("cynops", "cynops"));
    }

    @Test
    void decrypt() throws Exception {
        assertEquals("cynops"
                , AesUtils.decrypt("U2FsdGVkX191/S9qEGWQ6w5ybJmFl9bJ3CSZLuvfaFw=", "cynops"));
    }
}