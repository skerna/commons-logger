/*
 * Copyright (c)  2019  SKERNA
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package io.skerna.commons.logger

import java.io.IOException
import java.util.logging.LogManager

/**
 * A [LogDelegateFactory] which creates [JULLogDelegate] instances.
 *
 */
class JULLogDelegateFactory : LogDelegateFactory {

    override fun createDelegate(name: String): LogDelegate {
        return JULLogDelegate(name)
    }

    companion object {

        fun loadConfig() {
            try {
                JULLogDelegateFactory::class.java.classLoader.getResourceAsStream("vertx-default-jul-logging.properties")?.use { `is` ->
                    LogManager.getLogManager().readConfiguration(`is`)
                }
            } catch (ignore: IOException) {
            }

        }

        init {
            // Try and load vert.x JUL default logging config from classpath
            if (System.getProperty("java.util.logging.config.file") == null) {
                loadConfig()
            }
        }
    }
}
