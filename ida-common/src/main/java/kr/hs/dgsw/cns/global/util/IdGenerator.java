package kr.hs.dgsw.cns.global.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class IdGenerator {

    public static long generateUUIDWithLong() {
        return System.currentTimeMillis();
    }
}
