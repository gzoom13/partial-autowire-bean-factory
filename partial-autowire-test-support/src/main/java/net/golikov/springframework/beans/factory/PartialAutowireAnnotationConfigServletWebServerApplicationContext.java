package net.golikov.springframework.beans.factory;

import org.springframework.boot.web.servlet.context.AnnotationConfigServletWebServerApplicationContext;

public class PartialAutowireAnnotationConfigServletWebServerApplicationContext extends AnnotationConfigServletWebServerApplicationContext {
    public PartialAutowireAnnotationConfigServletWebServerApplicationContext() {
        super(new PartialAutowireBeanFactory());
    }

    public PartialAutowireAnnotationConfigServletWebServerApplicationContext(Class<?>... annotatedClasses) {
        this();
        register(annotatedClasses);
        refresh();
    }

    public PartialAutowireAnnotationConfigServletWebServerApplicationContext(String... basePackages) {
        this();
        scan(basePackages);
        refresh();
    }
}
