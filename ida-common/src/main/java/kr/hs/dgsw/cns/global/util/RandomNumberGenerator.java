package kr.hs.dgsw.cns.global.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.security.SecureRandom;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class RandomNumberGenerator {

    public static String generateRandomNumberWithString(int bound) {
        SecureRandom secureRandom = new SecureRandom();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bound; i++) {
            sb.append(secureRandom.nextLong());
        }
        return sb.toString();
    }
}
