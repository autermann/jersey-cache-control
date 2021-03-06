# Jersey Cache Control Annotations [![Build Status](https://travis-ci.org/autermann/jersey-cache-control.png?branch=master)](https://travis-ci.org/autermann/jersey-cache-control)

Small [Jersey][jersey] extension to enable configure `Cache-Control` with annotations:

```java
@GET
@CacheControl(noCache = true, maxAge = 0)
public Response justAnExample() {
    ...
}
```

To use it include this in your `pom.xml` and `com.github.autermann.jersey.cache.CacheControlFilterFactory` to your Jersey configuration:
```xml
<dependency>
    <groupId>com.github.autermann</groupId>
    <artifactId>jersey-cache-control</artifactId>
    <version>1.0.0</version>
</dependency>
```

## License
The project is licensed under the [Apache License, Version 2.0][apache]

```
Copyright 2013 Christian Autermann

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```

[apache]: http://www.apache.org/licenses/LICENSE-2.0 "Apache License, Version 2.0"
[jersey]: https://jersey.java.net/ "Jersey"
