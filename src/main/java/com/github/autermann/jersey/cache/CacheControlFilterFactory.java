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
package com.github.autermann.jersey.cache;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.ws.rs.core.CacheControl;
import javax.ws.rs.core.HttpHeaders;

import com.sun.jersey.api.model.AbstractMethod;
import com.sun.jersey.spi.container.ContainerRequest;
import com.sun.jersey.spi.container.ContainerRequestFilter;
import com.sun.jersey.spi.container.ContainerResponse;
import com.sun.jersey.spi.container.ContainerResponseFilter;
import com.sun.jersey.spi.container.ResourceFilter;
import com.sun.jersey.spi.container.ResourceFilterFactory;

/**
 * {@code ResourceFilterFactory} to apply {@code Cache-Control} directives to
 * responses.
 *
 * @see javax.ws.rs.core.CacheControl
 * @see com.github.autermann.jersey.cache.CacheControl
 *
 * @author Christian Autermann
 */
public class CacheControlFilterFactory implements ResourceFilterFactory {

    @Override
    public List<ResourceFilter> create(AbstractMethod am) {
        if (am.isAnnotationPresent(
                com.github.autermann.jersey.cache.CacheControl.class)) {
            CacheControl cc = createCacheControl(am.getAnnotation(
                    com.github.autermann.jersey.cache.CacheControl.class));
            String value = cc.toString();
            if (!value.isEmpty()) {
                return Collections.<ResourceFilter>singletonList(
                        new CacheControlResourceFilter(value));
            }
        }
        return Collections.emptyList();
    }

    /**
     * Converts a {@code Cache-Control} annotation to a {@code Cache-Control}
     * Jersey API object.
     *
     * @param annotation the annotation
     *
     * @return the Jersey API object
     *
     * @see javax.ws.rs.core.CacheControl
     * @see com.github.autermann.jersey.cache.CacheControl
     */
    private CacheControl createCacheControl(
            com.github.autermann.jersey.cache.CacheControl annotation) {
        CacheControl cacheControl = new CacheControl();
        cacheControl.setPrivate(annotation._private() ||
                                annotation.privateFields().length > 0);
        cacheControl.getPrivateFields()
                .addAll(Arrays.asList(annotation.privateFields()));
        cacheControl.setMustRevalidate(annotation.mustRevalidate());
        cacheControl.setNoCache(annotation.noCache() ||
                                annotation.noCacheFields().length > 0);
        cacheControl.getNoCacheFields()
                .addAll(Arrays.asList(annotation.noCacheFields()));
        cacheControl.setNoStore(annotation.noStore());
        cacheControl.setNoTransform(annotation.noTransform());
        cacheControl.setProxyRevalidate(annotation.proxyRevalidate());
        cacheControl.setMaxAge(annotation.maxAge());
        cacheControl.setSMaxAge(annotation.sMaxAge());
        for (CacheControlExtension e : annotation.extensions()) {
            cacheControl.getCacheExtension().put(e.key(), e.value());
        }
        return cacheControl;
    }

    /**
     * Filter that applies a {@code Cache-Control} header value to all
     * responses.
     */
    private class CacheControlResourceFilter implements ResourceFilter,
                                                        ContainerResponseFilter {
        /**
         * The {@code Cache-Control} header value.
         */
        private final String value;

        /**
         * Creates a new filter with the specified {@code Cache-Control} value.
         *
         * @param value the {@code Cache-Control} header value
         */
        CacheControlResourceFilter(String value) {
            this.value = value;
        }

        @Override
        public ContainerRequestFilter getRequestFilter() {
            return null;
        }

        @Override
        public ContainerResponseFilter getResponseFilter() {
            return this;
        }

        @Override
        public ContainerResponse filter(ContainerRequest request,
                                        ContainerResponse response) {
            response.getHttpHeaders()
                    .putSingle(HttpHeaders.CACHE_CONTROL, this.value);
            return response;
        }
    }
}
