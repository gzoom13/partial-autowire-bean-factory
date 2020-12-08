package net.golikov.springframework.beans.factory;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.factory.BeanFactory;
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

    /**
     * Create a new DefaultListableBeanFactory.
     */
    public PartialAutowireBeanFactory() {
    }

    /**
     * Create a new DefaultListableBeanFactory with the given parent.
     *
     * @param parentBeanFactory the parent BeanFactory
     */
    public PartialAutowireBeanFactory(BeanFactory parentBeanFactory) {
        super(parentBeanFactory);
    }

    @Override
    protected BeanWrapper autowireConstructor(String beanName, RootBeanDefinition mbd, Constructor<?>[] ctors, Object[] explicitArgs) {
        return new ConstructorResolverPartialAutowire(this).autowireConstructor(beanName, mbd, ctors, explicitArgs);
    }

}
