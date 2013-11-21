/*
 * Copyright 2013 Christian Autermann
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.autermann.jersey;

import com.github.autermann.jersey.cache.CacheControlFilterFactory;
import org.junit.rules.ExternalResource;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.core.ClassNamesResourceConfig;
import com.sun.jersey.api.core.ResourceConfig;
import com.sun.jersey.spi.container.servlet.WebComponent;
import com.sun.jersey.test.framework.JerseyTest;
import com.sun.jersey.test.framework.WebAppDescriptor;
import com.sun.jersey.test.framework.spi.container.TestContainerFactory;
import com.sun.jersey.test.framework.spi.container.grizzly.web.GrizzlyWebTestContainerFactory;

/**
 * TODO JavaDoc
 *
 * @author Christian Autermann
 */
public class Jersey extends ExternalResource {

    private final JerseyTest jersey;

    public Jersey(Class<?> resource) {
        this.jersey = new JerseyTestImpl(resource);
    }

    @Override
    protected void after() {
        try {
            this.jersey.tearDown();
        } catch (Exception ex) {
            throw new AssertionError(ex);
        }
    }

    @Override
    protected void before() throws Exception {
        this.jersey.setUp();
    }

    public WebResource resource() {
        return this.jersey.resource();
    }

    public Client client() {
        return this.jersey.client();
    }

    private class JerseyTestImpl extends JerseyTest {
        public JerseyTestImpl(Class<?> resource) {
            super(new WebAppDescriptor.Builder()
                    .initParam(WebComponent.RESOURCE_CONFIG_CLASS,
                               ClassNamesResourceConfig.class.getName())
                    .initParam(ClassNamesResourceConfig.PROPERTY_CLASSNAMES,
                               resource.getName())
                    .initParam(ResourceConfig.PROPERTY_RESOURCE_FILTER_FACTORIES,
                               CacheControlFilterFactory.class.getName())
                    .build());
        }

        @Override
        protected TestContainerFactory getTestContainerFactory() {
            return new GrizzlyWebTestContainerFactory();
        }
    }

}
