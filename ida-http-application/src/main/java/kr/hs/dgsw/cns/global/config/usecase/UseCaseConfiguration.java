package kr.hs.dgsw.cns.global.config.usecase;

import kr.hs.dgsw.cns.global.annotations.UseCase;
import lombok.extern.slf4j.Slf4j;
import org.reflections.Reflections;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.Configuration;

import java.util.Optional;
import java.util.Set;

@Slf4j
@Configuration
public class UseCaseConfiguration implements BeanFactoryPostProcessor {

    private final Reflections reflections = new Reflections("kr.hs.dgsw.cns");

    /**
     * {@link Class}를 이용해 bean의 이름을 생성합니다.
     * @param clazz bean 이름을 구할 클래스
     * @return IoC Container에 저장될 bean의 명칭을 반환합니다.
     */
    private String getBeanNameOf(Class<?> clazz) {
        String original = clazz.getSimpleName();
        if(original.length() == 1) return original.toLowerCase();

        return String.format("%s%s",
                original.substring(0, 1).toLowerCase(),
                original.substring(1));
    }

    /**
     * 타입에 맞는 의존 Bean의 이름을 가져옵니다.
     * @param beanFactory 알맞은 종속성을 탐색하기 위한 BeanFactory
     * @param qualifiedType 알맞은 종속성의 타입
     * @return 타입에 맞는 의존 Bean의 이름이 반환됩니다.
     */
    private Optional<String> getQualifiedBeanName(DefaultListableBeanFactory beanFactory, Class<?> qualifiedType) {
        for(String beanName: beanFactory.getBeanDefinitionNames()) {
            if(beanFactory.isTypeMatch(beanName, qualifiedType))
                return Optional.of(beanName);
        }

        return Optional.empty();
    }

    /**
     * Class를 인스턴스화 한 뒤 bean으로 등록합니다.
     * 인스턴스화 될 Class는 반드시 모든 의존성을 주입받을 수 있는 단일 생성자만을 가져야 합니다.
     * ({@link lombok.AllArgsConstructor} 사용을 권장합니다)
     * @param beanRegistry bean을 등록하기 위한 레지스트리
     * @param componentClass 인스턴스화 할 타입
     */
    private void registerBean(DefaultListableBeanFactory beanRegistry, Class<?> componentClass) {
        BeanDefinitionBuilder builder = BeanDefinitionBuilder.rootBeanDefinition(componentClass);
        Class<?>[] params = componentClass.getConstructors()[0].getParameterTypes();

        for(Class<?> beanType: params) {
            getQualifiedBeanName(beanRegistry, beanType)
                    .ifPresent(qualifiedBeanName -> {
                        builder.addConstructorArgReference(qualifiedBeanName);
                        builder.addDependsOn(qualifiedBeanName);
                    });
        }

        beanRegistry.registerBeanDefinition(getBeanNameOf(componentClass),
                builder.getBeanDefinition());
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        Set<Class<?>> useCaseClasses = reflections.getTypesAnnotatedWith(UseCase.class);
        DefaultListableBeanFactory beanRegistry = (DefaultListableBeanFactory) beanFactory;

        for (Class<?> componentClass : useCaseClasses)
            registerBean(beanRegistry, componentClass);

        log.info("{} Usecases have successfully registered into Spring Context",
                useCaseClasses.size());
    }
}
