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

/**
 * Annotation for {@code Cache-Control} header extensions.
 *
 * @see javax.ws.rs.core.CacheControl#getCacheExtension()
 * @see com.github.autermann.jersey.cache.CacheControl
 * @author Christian Autermann
 */
public @interface CacheControlExtension {

    /**
     * Corresponds to the key of an extension cache control directive.
     *
     * @return the key of the cache control extension directive
     */
    String key();

    /**
     * Corresponds to the value of an extension cache control directive.
     *
     * @return the value of the cache control extension directive
     */
    String value();

}
