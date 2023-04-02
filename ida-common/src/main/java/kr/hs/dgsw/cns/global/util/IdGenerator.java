package kr.hs.dgsw.cns.global.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 도메인 혹은 엔티티 클래스에서 식별값에 AUTO_INCREMENT
 * 외에 직접 사용해야하는 경우, 보다 편하게 식별값을 지정해주기
 * 위해 별도의 식별값 생성 유틸 클래스
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class IdGenerator {

    /**
     * 쓰레드의 고유 시드값을 사용하여 Long 형태의 난수를 생성합니다.
     * 식별값에 사용되기 위해 음수보다는 양수가 효율적이므로
     * 최소 100000 부터 최대 1000000 까지의 범위를 가진 난수를 생성합니다.
     * @return 10만부터 100만까지의 무작위 수
     */
    public static long generateUUIDWithLong() {
        return ThreadLocalRandom.current().nextLong(900000) + 100000;
    }

    /**
     * 쓰레드의 고유 시드값과 현재 날짜 데이터를 결합한 무작위 
     * 문자열을 생성합니다. {@link #generateUUIDWithLong()}
     * 를 사용하기에 문자열의 마지막은 10만부터 100만까지의 무작위
     * 수가 들어가게 됩니다.  
     * @return 현재 날짜와 10만부터 100만까지의 무작위 수가 결합된 문자열
     */
    public static String generateUUIDWithString() {
        return String.format("%tY%<tm%<td%<tH-%d", new Date(), generateUUIDWithLong());
    }
}
