package net.golikov.springframework.beans.factory;

import org.springframework.beans.BeanUtils;
import org.springframework.test.context.ContextLoader;
import org.springframework.test.context.SmartContextLoader;
import org.springframework.test.context.support.*;
import org.springframework.util.ClassUtils;

public class PartialAutowireContextBootstrapper extends AbstractTestContextBootstrapper {
    @Override
    protected Class<? extends ContextLoader> getDefaultContextLoaderClass(Class<?> testClass) {
        return PartialAutowireSmartContextLoader.class;
    }

    public static class PartialAutowireSmartContextLoader extends DelegatingSmartContextLoader {

        private static final String GROOVY_XML_CONTEXT_LOADER_CLASS_NAME = "org.springframework.test.context.support.GenericGroovyXmlContextLoader";

        private static final boolean groovyPresent = ClassUtils.isPresent("groovy.lang.Closure",
                DelegatingSmartContextLoader.class.getClassLoader())
                && ClassUtils.isPresent(GROOVY_XML_CONTEXT_LOADER_CLASS_NAME,
                DelegatingSmartContextLoader.class.getClassLoader());

        private final SmartContextLoader xmlLoader;
        private final SmartContextLoader annotationConfigLoader;

        public PartialAutowireSmartContextLoader() {
            if (groovyPresent) {
                try {
                    Class<?> loaderClass = ClassUtils.forName(GROOVY_XML_CONTEXT_LOADER_CLASS_NAME,
                            PartialAutowireContextBootstrapper.class.getClassLoader());
                    this.xmlLoader = new PartialAutowireContextLoader((GenericGroovyXmlContextLoader) BeanUtils.instantiateClass(loaderClass));
                } catch (Throwable ex) {
                    throw new IllegalStateException("Failed to enable support for Groovy scripts; "
                            + "could not load class: " + GROOVY_XML_CONTEXT_LOADER_CLASS_NAME, ex);
                }
            } else {
                this.xmlLoader = new PartialAutowireContextLoader(new GenericXmlContextLoader());
            }
            annotationConfigLoader = new PartialAutowireContextLoader(new AnnotationConfigContextLoader());
        }

        @Override
        protected SmartContextLoader getXmlLoader() {
            return xmlLoader;
        }

        @Override
        protected SmartContextLoader getAnnotationConfigLoader() {
            return annotationConfigLoader;
        }
    }

}
