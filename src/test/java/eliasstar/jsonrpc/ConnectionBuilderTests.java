/*
 * Copyright (C) 2020-2021 Elias*
 *
 * This file is part of JsonRpc4J.
 *
 * JsonRpc4J is free software: you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or any later version.
 *
 * JsonRpc4J is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR
 * A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with JsonRpc4J. If not, see <https://www.gnu.org/licenses/>.
 */

package eliasstar.jsonrpc;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;

import org.junit.jupiter.api.Test;

public final class ConnectionBuilderTests {

    @Test
    public void testBuildWithNoUri() {
        var builder = new ConnectionBuilder(HttpClient.newHttpClient());
        var reqBuilder = HttpRequest.newBuilder();

        assertAll(
                () -> assertThrows(IllegalStateException.class, () -> builder.build()),
                () -> assertThrows(IllegalStateException.class, () -> builder.setRequestBuilder(reqBuilder).build()));
    }
}