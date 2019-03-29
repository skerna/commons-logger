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

import kotlin.reflect.KClass


actual object LoggerFactory {

    private var delegateFactory: LogDelegateFactory? = null
    private var loggers = mutableMapOf<String, Logger>()

    init {
        initialise()
    }

    actual fun initialise() {
        delegateFactory = PrintDelegateFactory()

    }

    actual inline fun logger(clazz: KClass<*>): Logger {
        return logger(clazz.simpleName!!)
    }

    actual fun logger(name: String): Logger {
        var logger: Logger? = loggers[name]

        if (logger == null) {
            val delegate = delegateFactory!!.createDelegate(name)

            logger = Logger(delegate)

            val oldLogger = loggers.put(name, logger)

            if (oldLogger != null) {
                logger = oldLogger
            }
        }

        return logger
    }

    actual fun removeLogger(name: String) {
        loggers.remove(name)
    }

    actual fun setLogDelegateFactory(delegateFactory: LogDelegateFactory) {
        LoggerFactory.delegateFactory = delegateFactory
    }

    actual inline fun <reified T> logger(): Logger {
        return logger(T::class)
    }


}