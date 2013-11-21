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

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.github.autermann.jersey.cache.CacheControl;
import com.github.autermann.jersey.cache.CacheControlExtension;

/**
 * TODO JavaDoc
 *
 * @author Christian Autermann
 */
@Path("/")
public class Resource {
    public static final String NO_CACHE = "noCache";
    public static final String NO_STORE = "noStore";
    public static final String PRIVATE = "private";
    public static final String NO_TRANSFORM = "noTransform";
    public static final String PROXY_REVALIDATE = "proxyRevalidate";
    public static final String S_MAX_AGE = "sMaxAge";
    public static final String MAX_AGE = "maxAge";
    public static final String NO_CACHE_FIELDS = "noCacheFields";
    public static final String PRIVATE_FIELDS = "privateFields";
    public static final String MUST_REVALIDATE = "mustRevalidate";
    public static final String EXTENSIONS = "extensions";
    public static final String ALL = "all";
    public static final String EMPTY = "empty";
    public static final String NONE = "none";

    @GET
    @Path(NO_CACHE)
    @Produces(MediaType.TEXT_PLAIN)
    @CacheControl(noCache = true)
    public String noCache() {
        return NO_CACHE;
    }

    @GET
    @Path(NO_STORE)
    @Produces(MediaType.TEXT_PLAIN)
    @CacheControl(noStore = true)
    public String noStore() {
        return NO_STORE;
    }

    @GET
    @Path(PRIVATE)
    @Produces(MediaType.TEXT_PLAIN)
    @CacheControl(_private = true)
    public String _private() {
        return PRIVATE;
    }

    @GET
    @Path(NO_TRANSFORM)
    @Produces(MediaType.TEXT_PLAIN)
    @CacheControl(noTransform = true)
    public String noTransform() {
        return NO_TRANSFORM;
    }

    @GET
    @Path(PROXY_REVALIDATE)
    @Produces(MediaType.TEXT_PLAIN)
    @CacheControl(proxyRevalidate = true)
    public String proxyRevalidate() {
        return PROXY_REVALIDATE;
    }

    @GET
    @Path(S_MAX_AGE)
    @Produces(MediaType.TEXT_PLAIN)
    @CacheControl(sMaxAge = 1000)
    public String sMaxAge() {
        return S_MAX_AGE;
    }

    @GET
    @Path(MAX_AGE)
    @Produces(MediaType.TEXT_PLAIN)
    @CacheControl(maxAge = 1000)
    public String maxAge() {
        return MAX_AGE;
    }

    @GET
    @Path(NO_CACHE_FIELDS)
    @Produces(MediaType.TEXT_PLAIN)
    @CacheControl(noCacheFields
            = { "a", "b"})
    public String noCacheFields() {
        return NO_CACHE_FIELDS;
    }

    @GET
    @Path(PRIVATE_FIELDS)
    @Produces(MediaType.TEXT_PLAIN)
    @CacheControl(privateFields = { "a", "b"})
    public String privateFields() {
        return PRIVATE_FIELDS;
    }

    @GET
    @Path(MUST_REVALIDATE)
    @Produces(MediaType.TEXT_PLAIN)
    @CacheControl(mustRevalidate = true)
    public String mustRevalidate() {
        return MUST_REVALIDATE;
    }

    @GET
    @Path(EXTENSIONS)
    @Produces(MediaType.TEXT_PLAIN)
    @CacheControl(extensions = {
        @CacheControlExtension(key
                = "key1", value
                = "value1"),
        @CacheControlExtension(key
                = "key2", value
                = "value2") })
    public String extensions() {
        return EXTENSIONS;
    }

    @GET
    @Path(EMPTY)
    @Produces(MediaType.TEXT_PLAIN)
    @CacheControl
    public String empty() {
        return EMPTY;
    }

    @GET
    @Path(NONE)
    @Produces(MediaType.TEXT_PLAIN)
    @CacheControl
    public String none() {
        return NONE;
    }

    @GET
    @Path(ALL)
    @Produces(MediaType.TEXT_PLAIN)
    @CacheControl(
            _private = true,
            mustRevalidate = true,
            noCache = true,
            noStore = true,
            noTransform = true,
            proxyRevalidate = true,
            maxAge = 1000,
            sMaxAge = 1000,
            noCacheFields = { "a", "b"},
            privateFields = { "a", "b"},
            extensions = {
                @CacheControlExtension(
                        key = "key1",
                        value = "value1"),
                @CacheControlExtension(
                        key = "key2",
                        value = "value2")
            }
    )
    public String all() {
        return ALL;
    }

}
