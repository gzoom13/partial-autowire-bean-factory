package org.springframework.test.context.support;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigUtils;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.test.context.ContextConfigurationAttributes;
import org.springframework.test.context.MergedContextConfiguration;
import org.springframework.util.StringUtils;

import java.util.function.Supplier;

public class PartialAutowireContextLoader extends AbstractContextLoader {

    protected static final Log logger = LogFactory.getLog(PartialAutowireContextLoader.class);

    private final AbstractGenericContextLoader delegate;
    private final Supplier<GenericApplicationContext> contextSupplier;

    public PartialAutowireContextLoader(AbstractGenericContextLoader delegate,
                                        Supplier<GenericApplicationContext> contextSupplier) {
        this.delegate = delegate;
        this.contextSupplier = contextSupplier;
    }

    @Override
    public final ConfigurableApplicationContext loadContext(MergedContextConfiguration mergedConfig) {
        if (logger.isDebugEnabled()) {
            logger.debug(String.format("Loading ApplicationContext for merged context configuration [%s].",
                    mergedConfig));
        }

        delegate.validateMergedContextConfiguration(mergedConfig);

        GenericApplicationContext context = contextSupplier.get();

        ApplicationContext parent = mergedConfig.getParentApplicationContext();
        if (parent != null) {
            context.setParent(parent);
        }
        delegate.prepareContext(context);
        delegate.prepareContext(context, mergedConfig);
        delegate.customizeBeanFactory(context.getDefaultListableBeanFactory());
        delegate.loadBeanDefinitions(context, mergedConfig);
        AnnotationConfigUtils.registerAnnotationConfigProcessors(context);
        delegate.customizeContext(context);
        delegate.customizeContext(context, mergedConfig);
        context.refresh();
        context.registerShutdownHook();
        return context;
    }

    @Override
    public final ConfigurableApplicationContext loadContext(String... locations) {
        if (logger.isDebugEnabled()) {
            logger.debug(String.format("Loading ApplicationContext for locations [%s].",
                    StringUtils.arrayToCommaDelimitedString(locations)));
        }
        GenericApplicationContext context = contextSupplier.get();
        delegate.prepareContext(context);
        delegate.customizeBeanFactory(context.getDefaultListableBeanFactory());
        delegate.createBeanDefinitionReader(context).loadBeanDefinitions(locations);
        AnnotationConfigUtils.registerAnnotationConfigProcessors(context);
        delegate.customizeContext(context);
        context.refresh();
        context.registerShutdownHook();
        return context;
    }

    protected String getResourceSuffix() {
        return delegate.getResourceSuffix();
    }

    @Override
    public void processContextConfiguration(ContextConfigurationAttributes configAttributes) {
        delegate.processContextConfiguration(configAttributes);
    }
}
