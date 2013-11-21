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

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;

import javax.ws.rs.core.CacheControl;
import javax.ws.rs.core.HttpHeaders;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;

import com.sun.jersey.api.client.ClientResponse;

/**
 * TODO JavaDoc
 *
 * @author Christian Autermann
 */
public class CacheControlFilterFactoryTest {

    @Rule
    public final ErrorCollector errors = new ErrorCollector();

    @Rule
    public final Jersey jersey = new Jersey(Resource.class);

    @Test
    public void maxAge() {
        check(Resource.MAX_AGE, "max-age=1000");
    }

    @Test
    public void mustRevalidate() {
        check(Resource.MUST_REVALIDATE, "must-revalidate");
    }

    @Test
    public void _private() {
        check(Resource.PRIVATE, "private");
    }

    @Test
    public void noCache() {
        check(Resource.NO_CACHE, "no-cache");
    }

    @Test
    public void noStore() {
        check(Resource.NO_STORE, "no-store");
    }

    @Test
    public void noTransform() {
        check(Resource.NO_TRANSFORM, "no-transform");
    }

    @Test
    public void proxyRevalidate() {
        check(Resource.PROXY_REVALIDATE, "proxy-revalidate");
    }

    @Test
    public void sMaxAge() {
        check(Resource.S_MAX_AGE, "s-maxage=1000");
    }

    @Test
    public void noCacheFields() {
        check(Resource.NO_CACHE_FIELDS, "no-cache=\"a,b\"");
    }

    @Test
    public void privateFields() {
        check(Resource.PRIVATE_FIELDS, "private=\"a,b\"");
    }

    @Test
    public void extensions() {
        check(Resource.EXTENSIONS, "key1=value1,key2=value2");
    }

    @Test
    public void all() {
        check(Resource.ALL, "max-age=1000,must-revalidate,private=\"a,b\",no-cache=\"a,b\",no-store,no-transform,proxy-revalidate,s-maxage=1000,key1=value1,key2=value2");
    }

    @Test
    public void empty() {
        ClientResponse response = jersey.resource().path(Resource.EMPTY)
                .get(ClientResponse.class);
        errors.checkThat(response.getEntity(String.class), is(Resource.EMPTY));
        errors.checkThat(response.getHeaders()
                .getFirst(HttpHeaders.CACHE_CONTROL), is(nullValue()));
    }

    @Test
    public void none() {
        ClientResponse response = jersey.resource().path(Resource.NONE)
                .get(ClientResponse.class);
        errors.checkThat(response.getEntity(String.class), is(Resource.NONE));
        errors.checkThat(response.getHeaders()
                .getFirst(HttpHeaders.CACHE_CONTROL), is(nullValue()));
    }

    private void check(String path, String value) {
        ClientResponse response = jersey.resource().path(path)
                .get(ClientResponse.class);
        errors.checkThat(response.getEntity(String.class), is(path));
        String header = response.getHeaders()
                .getFirst(HttpHeaders.CACHE_CONTROL);
        errors.checkThat(header, is(notNullValue()));
        errors.checkThat(CacheControl.valueOf(header),
                         is(CacheControl.valueOf(value)));
    }

}
