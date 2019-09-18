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

import io.skerna.commons.sansi.yellow
import java.util.concurrent.ConcurrentHashMap
import kotlin.reflect.KClass

actual object LoggerFactory {

    @Volatile
    private var delegateFactory: LogDelegateFactory? = null

    private val loggers = ConcurrentHashMap<String, Logger>()

    init {
        initialise()
    }

    @Synchronized
    actual fun initialise() {
        val delegateFactory: LogDelegateFactory

        // If a system property is specified then this overrides any delegate factory which is set
        // programmatically - this is primarily of use so we can configure the logger delegate on the client side.
        // call to System.getProperty is wrapped in a try block as it will fail if the client runs in a secured
        // environment
        var className: String? = ALogDelegte::class.java.name
        try {
            className = System.getProperty(LOGGER_DELEGATE_FACTORY_CLASS_NAME)
        } catch (e: Exception) {
            println("Warning: se trato de acceder a la propiedad $LOGGER_DELEGATE_FACTORY_CLASS_NAME  ${e.message}".yellow())
        }
        if (className != null) {
            val loader = Thread.currentThread().contextClassLoader
            //val loader = this.javaClass.classLoader
            try {
                val clz = loader.loadClass(className)
                delegateFactory = clz.getConstructor().newInstance() as LogDelegateFactory
            } catch (e: Exception) {
                throw IllegalArgumentException("Error instantiating transformer class \"$className\"", e)
            }

        } else {
            delegateFactory = ALogDelegateFactory()
        }
        configureTargetLogger(delegateFactory)
        LoggerFactory.delegateFactory = delegateFactory
    }

    /**
     * permite confugurar el delegate Log system para vertx
     */
    private fun configureTargetLogger(delegateFactory: LogDelegateFactory) {
        this.delegateFactory = delegateFactory
    }

    @JvmStatic
    actual inline fun logger(clazz: KClass<*>): Logger {
        val javaClass = clazz.java
        return logger(javaClass)
    }

    @JvmStatic
    fun logger(clazz: Class<*>): Logger {
        val name = if (javaClass.isMemberClass)
            javaClass.enclosingClass!!.canonicalName
        else
            clazz.canonicalName
        return logger(name!!)
    }

    @JvmStatic
    actual fun logger(name: String): Logger {
        var logger: Logger? = loggers[name]

        if (logger == null) {
            val delegate = delegateFactory!!.createDelegate(name)

            logger = Logger(delegate)

            val oldLogger = (loggers).putIfAbsent(name, logger)

            if (oldLogger != null) {
                logger = oldLogger
            }
        }
        return logger
    }

    @JvmStatic
    actual fun removeLogger(name: String) {
        loggers.remove(name)
    }



    @JvmStatic
    actual fun setLogDelegateFactory(delegateFactory: LogDelegateFactory) {
        //  configureTargetLogger(delegateFactory)
        System.setProperty(LOGGER_DELEGATE_FACTORY_CLASS_NAME,delegateFactory.javaClass.canonicalName)
        initialise()
    }

    actual inline fun <reified T> logger(): Logger {
        return logger(T::class)
    }


}
