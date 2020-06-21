package net.golikov.springframework.beans.factory;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

public class PartialAutowireApplicationContext extends GenericApplicationContext {

    public PartialAutowireApplicationContext() {
        super(new PartialAutowireBeanFactory());
    }

    public PartialAutowireApplicationContext(ApplicationContext parent) {
        super(parent);
    }

}
