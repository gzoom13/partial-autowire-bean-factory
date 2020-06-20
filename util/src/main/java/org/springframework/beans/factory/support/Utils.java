package org.springframework.beans.factory.support;

import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.config.DependencyDescriptor;

public class Utils {
    public static boolean isAutowireCandidate(DefaultListableBeanFactory beanFactory, String beanName,
                                              RootBeanDefinition mbd, DependencyDescriptor descriptor, AutowireCandidateResolver resolver) {
        String beanDefinitionName = BeanFactoryUtils.transformedBeanName(beanName);
        beanFactory.resolveBeanClass(mbd, beanDefinitionName);
        if (mbd.isFactoryMethodUnique && mbd.factoryMethodToIntrospect == null) {
            new ConstructorResolverPartialAutowire(beanFactory).resolveFactoryMethodIfPossible(mbd);
        }
        return resolver.isAutowireCandidate(
                new BeanDefinitionHolder(mbd, beanName, beanFactory.getAliases(beanDefinitionName)), descriptor);
    }
}
