package net.golikov.springframework.beans.factory;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class PartialAutowireAnnotationConfigApplicationContext extends AnnotationConfigApplicationContext {

    public PartialAutowireAnnotationConfigApplicationContext() {
        super(new PartialAutowireBeanFactory());
    }

    public PartialAutowireAnnotationConfigApplicationContext(Class<?>... componentClasses) {
        this();
        register(componentClasses);
        refresh();
    }

    public PartialAutowireAnnotationConfigApplicationContext(String... basePackages) {
        this();
        scan(basePackages);
        refresh();
    }
}
