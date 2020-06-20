package net.golikov.springframework.beans.factory;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.factory.config.DependencyDescriptor;
import org.springframework.beans.factory.support.AutowireCandidateResolver;
import org.springframework.beans.factory.support.ConstructorResolverPartialAutowire;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.beans.factory.support.Utils;

import java.lang.reflect.Constructor;

public class PartialAutowireBeanFactory extends DefaultListableBeanFactory {

    @Override
    protected BeanWrapper instantiateUsingFactoryMethod(String beanName, RootBeanDefinition mbd, Object[] explicitArgs) {

        return new ConstructorResolverPartialAutowire(this).instantiateUsingFactoryMethod(beanName, mbd, explicitArgs);
    }

    @Override
    protected BeanWrapper autowireConstructor(String beanName, RootBeanDefinition mbd, Constructor<?>[] ctors, Object[] explicitArgs) {

        return new ConstructorResolverPartialAutowire(this).autowireConstructor(beanName, mbd, ctors, explicitArgs);
    }

    @Override
    protected boolean isAutowireCandidate(String beanName, RootBeanDefinition mbd,
                                          DependencyDescriptor descriptor, AutowireCandidateResolver resolver) {
        return Utils.isAutowireCandidate(this, beanName, mbd, descriptor, resolver);
    }

}
