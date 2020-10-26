# JsonRpc4J
> A general JSON-RPC 2.0 Wrapper for Java

## Downloads
### Jar
Releases can be downloaded [here](https://github.com/EliasStar/JsonRpc4J/releases).
If you need an "all in one" jar, grab the one with the jar-with-dependencies suffix. I includes the dependencies, therefore you don't need anything else.
If you want to manage your dependencies yourself use the one without a suffix.
"sources" suffix includes the raw, uncompiled sourcecode for reference and the javadoc can be found in the "javadoc" suffix jar.

### Maven
#### pom.xml
Add the following under `project/repositories` tag:
```xml
<repository>
    <id>github</id>
    <name>JsonRpc4J GitHub Maven Packages</name>
    <url>https://maven.pkg.github.com/EliasStar/JsonRpc4J</url>
</repository>
```
Furthermore add the following under `project/dependencies` tag:
```xml
<dependency>
    <groupId>eliasstar</groupId>
    <artifactId>json-rpc</artifactId>
    <version>VERSION</version>
</dependency>
```
Replace `VERSION` with a version found under releases, preferably the latest.

#### settings.xml
Is located under `~/.m2/settings.xml`.
Add the following under `settings/servers` tag:
```xml
<server>
    <id>github</id>
    <username>YOUR_USERNAME</username>
    <password>YOUR_PERSONAL_ACCESS_TOKEN</password>
</server>
```
Be sure to replace `YOUR_USERNAME` and `YOUR_PERSONAL_ACCESS_TOKEN`.

## Usage
### Get a connection
```java
// Simple
var con = JsonRpc.connect("https://example.com/jsonrpc.do");
```
```java
// Customizable
var client = HttpClient.newHttpClient();
var gson = new Gson();

var con = new ConnectionBuilder(client, "https://example.com/jsonrpc.do").setGson(gson).build();
```

### Make a request
```java
// Remote procedure call
var result = con.callRemoteProcedure("exampleMethod");
```
```java
// With parameters
var params = new JsonArray();

params.add("foo");
params.add(42);

var result = con.callRemoteProcedure("exampleMethod", params);
```
```java
// Notification
con.sendNotification("exampleNotification");
```

```java
// Batch request
var results = con.sendBatchRequest(
    new Request(0, "firstMethod"),
    new Request(1, "secondMethod"),
    new Notification("exampleNotification")
);
```


## LICENSE
JsonRpc4J - A general JSON-RPC 2.0 Wrapper for Java <br>
Copyright (C) 2020 Elias*

This program is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program. If not, see https://www.gnu.org/licenses.
