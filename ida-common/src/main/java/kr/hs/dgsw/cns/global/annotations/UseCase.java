package kr.hs.dgsw.cns.global.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Spring 의존성이 없는 Pure-Java 환경에서 특정 클래스를
 * Spring 환경에서 사용하게 될 때 자동으로 싱글톤-빈으로 등록
 * 시키고자 할 때 사용되는 어노테이션입니다.
 * 빈에 등록될 클래스에 해당 어노테이션이 있는 경우, BeanCreationException 예외가 발생할 수 있습니다.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface UseCase {
    boolean registeredOnBean() default true;
}
