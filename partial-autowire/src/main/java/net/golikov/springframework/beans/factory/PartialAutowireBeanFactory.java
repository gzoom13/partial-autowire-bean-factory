package net.golikov.springframework.beans.factory;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.factory.support.ConstructorResolverPartialAutowire;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.RootBeanDefinition;

import java.lang.reflect.Constructor;

/**
 * This is {@link DefaultListableBeanFactory} that is capable of
 * injecting singleton from context into constructor, even if they
 * were not provided explicitly.
 */
public class PartialAutowireBeanFactory extends DefaultListableBeanFactory {

    @Override
    protected BeanWrapper autowireConstructor(String beanName, RootBeanDefinition mbd, Constructor<?>[] ctors, Object[] explicitArgs) {
        return new ConstructorResolverPartialAutowire(this).autowireConstructor(beanName, mbd, ctors, explicitArgs);
    }

}
