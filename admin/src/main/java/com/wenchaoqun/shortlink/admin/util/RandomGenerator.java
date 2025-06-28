package com.wenchaoqun.shortlink.admin.util;

import java.security.SecureRandom;

/**
 * 分组ID随机生成器
 */
public class RandomGenerator {

    private static final String CHARACTERS = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static final SecureRandom RANDOM = new SecureRandom();

    private RandomGenerator() {
        throw new UnsupportedOperationException("Utility class");
    }

    public static String generateRandomId() {
        return generateRandomId(6);
    }

    public static String generateRandomId(int length) {
        if (length <= 0) {
            throw new IllegalArgumentException("Length must be positive");
        }

        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append(CHARACTERS.charAt(RANDOM.nextInt(CHARACTERS.length())));
        }
        return sb.toString();
    }

    public static String generateRandomCode() {
        return generateRandomId(6);
    }
}
