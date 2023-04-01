package kr.hs.dgsw.cns.global.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.concurrent.ThreadLocalRandom;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class IdGenerator {

    public static long generateUUIDWithLong() {
        return ThreadLocalRandom.current().nextLong(900000) + 100000;
    }
}
