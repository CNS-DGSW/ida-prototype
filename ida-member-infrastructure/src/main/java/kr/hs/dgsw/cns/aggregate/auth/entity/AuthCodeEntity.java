package kr.hs.dgsw.cns.aggregate.auth.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;
import org.springframework.data.redis.core.index.Indexed;

@Getter
@RedisHash
@Builder
@AllArgsConstructor @NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AuthCodeEntity {
    @Id
    private String code;

    @Indexed
    private String phoneNumber;

    @TimeToLive
    private Integer expirationTime;
}
