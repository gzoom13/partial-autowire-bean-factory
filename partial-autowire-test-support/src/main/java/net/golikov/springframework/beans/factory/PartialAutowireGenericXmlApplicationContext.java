/*
 * Copyright 2002-2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package net.golikov.springframework.beans.factory;

import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

public class PartialAutowireGenericXmlApplicationContext extends GenericApplicationContext {

    private final XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(this);

    public PartialAutowireGenericXmlApplicationContext() {
        super(new PartialAutowireBeanFactory());
    }

    public PartialAutowireGenericXmlApplicationContext(Resource... resources) {
        super(new PartialAutowireBeanFactory());
        load(resources);
        refresh();
    }

    public PartialAutowireGenericXmlApplicationContext(String... resourceLocations) {
        super(new PartialAutowireBeanFactory());
        load(resourceLocations);
        refresh();
    }

    public PartialAutowireGenericXmlApplicationContext(Class<?> relativeClass, String... resourceNames) {
        super(new PartialAutowireBeanFactory());
        load(relativeClass, resourceNames);
        refresh();
    }

    public final XmlBeanDefinitionReader getReader() {
        return this.reader;
    }


    public void setValidating(boolean validating) {
        this.reader.setValidating(validating);
    }

    @Override
    public void setEnvironment(ConfigurableEnvironment environment) {
        super.setEnvironment(environment);
        this.reader.setEnvironment(getEnvironment());
    }

    public void load(Resource... resources) {
        this.reader.loadBeanDefinitions(resources);
    }

    public void load(String... resourceLocations) {
        this.reader.loadBeanDefinitions(resourceLocations);
    }

    public void load(Class<?> relativeClass, String... resourceNames) {
        Resource[] resources = new Resource[resourceNames.length];
        for (int i = 0; i < resourceNames.length; i++) {
            resources[i] = new ClassPathResource(resourceNames[i], relativeClass);
        }
        this.load(resources);
    }
}
