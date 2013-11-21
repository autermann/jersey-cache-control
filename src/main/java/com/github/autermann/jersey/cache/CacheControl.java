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

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation to apply a {@code Cache-Control} header to the annotated method.
 * This requires the {@link CacheControlFilterFactory} to be configured.
 *
 * @see javax.ws.rs.core.CacheControl
 *
 * @author Christian Autermann
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface CacheControl {
    /**
     * Corresponds to the private cache control directive.
     *
     * @return true if the private cache control directive will be included in
     *         the
     *         response, false otherwise.
     *
     * @see #privateFields()
     */
    boolean _private() default false;

    /**
     * Corresponds to the value of the private cache control directive.
     *
     * @return a mutable list of field-names that will form the value of the
     *         private cache control directive. An empty list results in a bare
     *         no-cache directive.
     *
     * @see #_private()
     */
    String[] privateFields() default {};

    /**
     * Corresponds to the no-cache cache control directive.
     *
     * @return true if the no-cache cache control directive will be included in
     *         the response, false otherwise.
     *
     * @see #noCacheFields()
     */
    boolean noCache() default false;

    /**
     * Corresponds to the value of the no-cache cache control directive.
     *
     * @return a mutable list of field-names that will form the value of the
     *         no-cache cache control directive. An empty list results in a bare
     *         no-cache directive.
     *
     * @see #noCache() 
     */
    String[] noCacheFields() default {};

    /**
     * Corresponds to the no-store cache control directive.
     *
     * @return true if the no-store cache control directive will be included in
     *         the response, false otherwise.
     *
     */
    boolean noStore() default false;

    /**
     * Corresponds to the no-transform cache control directive.
     *
     * @return true if the no-transform cache control directive will be included
     *         in the response, false otherwise.
     *
     */
    boolean noTransform() default false;

    /**
     * Corresponds to the must-revalidate cache control directive.
     *
     * @return true if the must-revalidate cache control directive will be
     *         included in the response, false otherwise.
     *
     */
    boolean mustRevalidate() default false;

    /**
     * Corresponds to the proxy-revalidate cache control directive.
     *
     * @return true if the proxy-revalidate cache control directive will be
     *         included in the response, false otherwise.
     *
     */
    boolean proxyRevalidate() default false;

    /**
     * Corresponds to the max-age cache control directive.
     *
     * @return the value of the max-age cache control directive, -1 if the
     *         directive is disabled.
     *
     */
    int maxAge() default -1;

    /**
     * Corresponds to the s-maxage cache control directive.
     *
     * @return the value of the s-maxage cache control directive, -1 if the
     *         directive is disabled.
     */
    int sMaxAge() default -1;

    /**
     * Corresponds to a set of extension cache control directives.
     *
     * @return a mutable map of cache control extension names and their values.
     *         If a key has a null value, it will appear as a bare directive. If
     *         a key has a value that contains no whitespace then the directive
     *         will appear as a simple name=value pair. If a key has a value
     *         that contains whitespace then the directive will appear as a
     *         quoted name="value" pair.
     */
    CacheControlExtension[] extensions() default {};
}
