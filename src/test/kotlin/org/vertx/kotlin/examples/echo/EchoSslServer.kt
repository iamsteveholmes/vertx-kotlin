/*
 * Copyright 2012 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.vertx.kotlin.examples.echo

import org.vertx.java.deploy.Verticle
import org.vertx.java.core.streams.Pump

import org.vertx.kotlin.core.*

public class EchoSslServer() : Verticle() {
    public override fun start() {
        createNetServer {
            setSSL(true)
            setKeyStorePath("./server-keystore.jks")
            setKeyStorePassword("wibble")

            connectHandler{ socket ->
                Pump.createPump(socket, socket)!!.start()
            }
        }
        .listen(1234)
    }
}