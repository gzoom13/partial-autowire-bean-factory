package net.golikov;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.assertj.core.api.Assertions.assertThat;

@ContextConfiguration(classes = LookupInjectionTest.TestConfiguration.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class LookupInjectionTest {

    @Autowired
    private Factory factory;

    @Autowired
    private Singleton singleton;

    @Test
    public void lookupPrototypeWithRuntimeParameter() {
        String parameter = "value";
        PrototypeWithRuntimeParameter bean = factory.prototypeWithRuntimeParameter(parameter);
        assertThat(bean.runtimeParameter).isSameAs(parameter);
    }

    @Test
    public void lookupPrototypeWithSingletonParameter() {
        PrototypeWithSingletonParameter bean = factory.prototypeWithSingletonParameter();
        assertThat(bean.singletonParameter).isSameAs(singleton);
    }

    // this test fails with existing Spring version
    @Test
    public void lookupPrototypeWithRuntimeAndSingletonParameters() {
        String runtimeParameter = "value";
        PrototypeWithRuntimeAndSingletonParameters bean = factory.prototypeWithRuntimeAndSingletonParameters(runtimeParameter);
        assertThat(bean.runtimeParameter).isSameAs(runtimeParameter);
        assertThat(bean.singletonParameter).isSameAs(singleton);
    }

    @ComponentScan
    public static class TestConfiguration {
    }

    @Component
    public static abstract class Factory {
        @Lookup
        public abstract PrototypeWithRuntimeParameter prototypeWithRuntimeParameter(String runtimeParameter);

        @Lookup
        public abstract PrototypeWithSingletonParameter prototypeWithSingletonParameter();

        @Lookup
        public abstract PrototypeWithRuntimeAndSingletonParameters prototypeWithRuntimeAndSingletonParameters(String runtimeParameter);
    }

    @Component
    @Scope(BeanDefinition.SCOPE_PROTOTYPE)
    public static class PrototypeWithRuntimeAndSingletonParameters {
        private final String runtimeParameter;
        private final Singleton singletonParameter;

        public PrototypeWithRuntimeAndSingletonParameters(String runtimeParameter, Singleton singletonParameter) {
            this.runtimeParameter = runtimeParameter;
            this.singletonParameter = singletonParameter;
        }
    }

    @Component
    @Scope(BeanDefinition.SCOPE_PROTOTYPE)
    public static class PrototypeWithRuntimeParameter {
        private final String runtimeParameter;

        public PrototypeWithRuntimeParameter(String runtimeParameter) {
            this.runtimeParameter = runtimeParameter;
        }
    }

    @Component
    @Scope(BeanDefinition.SCOPE_PROTOTYPE)
    public static class PrototypeWithSingletonParameter {
        private final Singleton singletonParameter;

        public PrototypeWithSingletonParameter(Singleton singletonParameter) {
            this.singletonParameter = singletonParameter;
        }
    }

    @Component
    public static class Singleton {
    }
}
